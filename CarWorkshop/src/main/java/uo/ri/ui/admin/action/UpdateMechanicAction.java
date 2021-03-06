package uo.ri.ui.admin.action;

import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * 
 * UpdateMechanicAction.java
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201812
 * @since 201711201812
 * @formatter Oviedo Computing Community
 */
public class UpdateMechanicAction implements Action {
	@Override
	public void execute() throws BusinessException {
		ServicesFactory.getAdminService().updateMechanic(
				Console.readLong("Id del mecánico"),
				Console.readString("Nombre"), Console.readString("Apellidos"));
		Console.println("Mecánico actualizado");
	}

}
