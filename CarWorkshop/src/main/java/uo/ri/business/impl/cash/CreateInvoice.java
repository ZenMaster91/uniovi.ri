package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;

/**
 * Creates and invoice for the given failures that have been fixed.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201754
 * @since 201711201754
 */
public class CreateInvoice {
	// Database connection.
	private Connection connection;

	// List of id of each fault.
	private List<Long> faults;

	/**
	 * @param faults to create the invoice for.
	 */
	public CreateInvoice(List<Long> faults) {
		this.faults = faults;
	}

	/**
	 * Creates the invoice in the database.
	 * 
	 * @return a Map<String, Object> with all the invoice's attributes.
	 * @throws BusinessException if there's any error during the transaction.
	 */
	public Map<String, Object> execute() throws BusinessException {
		Map<String, Object> invoice = new HashMap<String, Object>();

		try {
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);

			verificarAveriasTerminadas(this.faults);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			double totalFactura = calcularImportesAverias(this.faults);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva / 100);
			importe = Round.twoCents(importe);

			long idFactura = crearFactura(numeroFactura, fechaFactura, iva,
					importe);
			vincularAveriasConFactura(idFactura, this.faults);
			cambiarEstadoAverias(this.faults, "FACTURADA");

			invoice.put("invoiceNumber", numeroFactura);
			invoice.put("invoiceDate", fechaFactura);
			invoice.put("invoiceTotal", totalFactura);
			invoice.put("invoiceTAX", iva);
			invoice.put("invoiceImport", importe);

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(connection);
		}

		return invoice;
	}

	private void verificarAveriasTerminadas(List<Long> idsAveria)
			throws BusinessException {
		PersistenceFactory.getFaultGateway()
				.verificarAveriasTerminadas(idsAveria);

	}

	private Long generarNuevoNumeroFactura() throws BusinessException {
		return PersistenceFactory.getInvoiceGateway()
				.generarNuevoNumeroFactura();
	}

	protected double calcularImportesAverias(List<Long> idsAveria)
			throws BusinessException, SQLException {

		double totalFactura = 0.0;
		for (Long idAveria : idsAveria) {
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;

			actualizarImporteAveria(idAveria, totalAveria);

			totalFactura += totalAveria;
		}
		return totalFactura;
	}

	private double consultaImporteManoObra(Long idAveria)
			throws BusinessException {
		return PersistenceFactory.getFaultGateway()
				.consultaImporteManoObra(idAveria);
	}

	private double consultaImporteRepuestos(Long idAveria)
			throws BusinessException {
		return PersistenceFactory.getFaultGateway()
				.consultaImporteRepuestos(idAveria);
	}

	private void actualizarImporteAveria(Long idAveria, double totalAveria)
			throws BusinessException {
		PersistenceFactory.getFaultGateway().actualizarImporteAveria(idAveria,
				totalAveria);
	}

	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0
				: 18.0;
	}

	private long crearFactura(long numeroFactura, Date fechaFactura, double iva,
			double totalConIva) throws BusinessException {

		return PersistenceFactory.getInvoiceGateway()
				.crearFactura(numeroFactura, fechaFactura, iva, totalConIva);
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria)
			throws BusinessException {
		PersistenceFactory.getInvoiceGateway()
				.vincularAveriasConFactura(idFactura, idsAveria);
	}

	private void cambiarEstadoAverias(List<Long> idsAveria, String status)
			throws BusinessException {
		PersistenceFactory.getFaultGateway().cambiarEstadoAverias(idsAveria,
				status);
	}
}
