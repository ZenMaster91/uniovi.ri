package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public class ServicesFactory {

	/**
	 * Returns an admin service implementation.
	 * 
	 * @return the admin service implementation.
	 */
	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	/**
	 * Returns an cash service implementation.
	 * 
	 * @return the cash service implementation.
	 */
	public static CashService getCashService() {
		return new CashServiceImpl();
	}

}
