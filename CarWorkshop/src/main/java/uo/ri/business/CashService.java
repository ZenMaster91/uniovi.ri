package uo.ri.business;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashService {
	
	/**
	 * Creates an invoice for the given faults.
	 * 
	 * @param ids if the faults fixed.
	 * @return the invoice as a Map<String,Object> with its attributes.
	 * @throws BusinessException
	 */
	public Map<String, Object> createInvoice(List<Long> ids) throws BusinessException;
	
	public void addMetalic(int idCliente);
	
	public void addCreditCard(int idCliente, int creditCardNumber, Date expirationDate);
	
	public void addBono(int idCliente, String descripcion, double disponible);

}
