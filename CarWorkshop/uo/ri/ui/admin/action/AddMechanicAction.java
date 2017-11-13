package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.admin.AddMechanic;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		new AddMechanic(Console.readString("Nombre"), Console.readString("Apellidos")).execute();
		Console.println("Nuevo mecánico añadido");
	}

}
