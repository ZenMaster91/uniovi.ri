package uo.ri.business.impl.admin;

import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;

/**
 * Adds a given mechanic to the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201739
 * @since 201711201739
 */
public class AddMechanic implements Action {
	private String name, surname;

	/**
	 * @param name of the mechanic.
	 * @param surname of the mechanic.
	 */
	public AddMechanic(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	@Override
	public void execute() throws BusinessException {
		PersistenceFactory.getMechanicsGateway().addMechanic(name, surname);
	}

}
