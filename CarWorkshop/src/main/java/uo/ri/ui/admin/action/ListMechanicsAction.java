package uo.ri.ui.admin.action;

import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * 
 * ListMechanicsAction.java
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201812
 * @since 201711201812
 */
public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		for (Map<String, Object> mechanic : ServicesFactory.getAdminService()
				.findAllMechanics()) {
			Console.printf("\t%d %s %s\n", mechanic.get("id"),
					mechanic.get("name"), mechanic.get("surname"));
		}
	}
}
