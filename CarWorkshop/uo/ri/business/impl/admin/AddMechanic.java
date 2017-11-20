package uo.ri.business.impl.admin;

import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;

public class AddMechanic implements Action {
	private String name, surname;

	public AddMechanic(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	@Override
	public void execute() throws BusinessException {
		PersistenceFactory.getMechanicsGateway().addMechanic(name, surname);
	}

}
