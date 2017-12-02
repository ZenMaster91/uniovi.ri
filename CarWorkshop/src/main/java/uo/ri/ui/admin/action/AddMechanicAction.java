package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

/**
 * 
 * AddMechanicAction.java
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201811
 * @since 201711201811
 */
public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		ServicesFactory.getAdminService().newMechanic(
				Console.readString("Nombre"), Console.readString("Apellidos"));
		Console.println("Nuevo mecánico añadido");
	}

}
