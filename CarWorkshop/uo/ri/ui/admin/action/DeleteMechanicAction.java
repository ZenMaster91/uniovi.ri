package uo.ri.ui.admin.action;

import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * 
 * DeleteMechanicAction.java
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201812
 * @since 201711201812
 */
public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		ServicesFactory.getAdminService()
				.deleteMechanic(Console.readLong("Id de mecánico"));
		Console.println("Se ha eliminado el mecánico");
	}

}
