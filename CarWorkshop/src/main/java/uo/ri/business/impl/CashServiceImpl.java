package uo.ri.business.impl;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoice;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService {

	@Override
	public Map<String, Object> createInvoice(List<Long> ids) throws BusinessException {
		return new CreateInvoice(ids).execute();
	}

	@Override
	public void addMetalic(int idCliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCreditCard(int idCliente, int creditCardNumber,
			Date expirationDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBono(int idCliente, String descripcion, double disponible) {
		// TODO Auto-generated method stub
		
	}

}
