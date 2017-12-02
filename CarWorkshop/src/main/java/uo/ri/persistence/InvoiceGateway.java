package uo.ri.persistence;

import java.util.Date;
import java.util.List;

import uo.ri.common.BusinessException;

public interface InvoiceGateway {

	/**
	 * 
	 * @param idFactura
	 * @param idsAveria
	 * @throws BusinessException
	 */
	void vincularAveriasConFactura(long idFactura, List<Long> idsAveria)
			throws BusinessException;
	
	/**
	 * 
	 * @param numeroFactura
	 * @return
	 * @throws BusinessException
	 */
	long getGeneratedKey(long numeroFactura) throws BusinessException;

	/**
	 * 
	 * @param numeroFactura
	 * @param fechaFactura
	 * @param iva
	 * @param totalConIva
	 * @return
	 * @throws BusinessException
	 */
	long crearFactura(long numeroFactura, Date fechaFactura, double iva,
			double totalConIva) throws BusinessException;
	
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	Long generarNuevoNumeroFactura() throws BusinessException;
}
