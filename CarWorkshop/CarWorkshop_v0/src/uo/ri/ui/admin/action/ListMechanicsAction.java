package uo.ri.ui.admin.action;

import java.util.Map;

import uo.ri.business.admin.FindAllMechanics;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		for (Map<String, Object> mechanic : new FindAllMechanics().execute()) {
			Console.printf("\t%d %s %s\n",
					mechanic.get("id"),
					mechanic.get("name"),
					mechanic.get("surname"));
		}
	}
}
