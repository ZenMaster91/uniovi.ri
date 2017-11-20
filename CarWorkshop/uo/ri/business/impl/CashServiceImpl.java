package uo.ri.business.impl;

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

}
