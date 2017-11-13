package uo.ri.ui.admin.action;

import uo.ri.business.admin.UpdateMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action {
	@Override
	public void execute() throws BusinessException {
		new UpdateMechanic(Console.readLong("Id del mecánico"),
							Console.readString("Nombre"),
							Console.readString("Apellidos"));

		Console.println("Mecánico actualizado");
	}

}
