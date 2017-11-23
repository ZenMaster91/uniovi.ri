package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;

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

	@SuppressWarnings("resource")
	private void verificarAveriasTerminadas(List<Long> idsAveria)
			throws SQLException, BusinessException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_VERIFICAR_ESTADO_AVERIA"));

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException(
							"No existe la averia " + idAveria);
				}

				String status = rs.getString(1);
				if (!"TERMINADA".equalsIgnoreCase(status)) {
					throw new BusinessException(
							"No está terminada la avería " + idAveria);
				}

				rs.close();
			}
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private Long generarNuevoNumeroFactura() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, el siguiente
			} else { // todavía no hay ninguna
				return 1L;
			}
		} finally {
			Jdbc.close(rs, pst);
		}
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
			throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException(
						"La averia no existe o no se puede facturar");
			}

			return rs.getDouble(1);

		} catch (BusinessException e) {
			throw e;
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}

			return rs.getDouble(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private void actualizarImporteAveria(Long idAveria, double totalAveria)
			throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		} finally {
			Jdbc.close(pst);
		}
	}

	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0
				: 18.0;
	}

	private long crearFactura(long numeroFactura, Date fechaFactura, double iva,
			double totalConIva) throws SQLException {

		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(Conf.get("SQL_INSERTAR_FACTURA"));
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();

			return getGeneratedKey(numeroFactura); // Id de la nueva factura
													// generada

		} finally {
			Jdbc.close(pst);
		}
	}

	private long getGeneratedKey(long numeroFactura) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_RECUPERAR_CLAVE_GENERADA"));
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria)
			throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_VINCULAR_AVERIA_FACTURA"));

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idFactura);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private void cambiarEstadoAverias(List<Long> idsAveria, String status)
			throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection
					.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));

			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}
}