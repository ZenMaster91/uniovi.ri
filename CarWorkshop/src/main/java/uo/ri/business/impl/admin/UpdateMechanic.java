package uo.ri.business.impl.admin;

import alb.util.menu.Action;
import uo.ri.conf.PersistenceFactory;

/**
 * Updates the data of a given mechanic. The mechanic is given through its id.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711231134
 * @since 201711201749
 */
public class UpdateMechanic implements Action {

	// id of the mechanic to update.
	private long id;

	// name and surname to set.
	private String name, surname;

	/**
	 * Loads the needed data to update the mechanic whose id id the same as the
	 * one provided.
	 * 
	 * @param id of the mechanic to update.
	 * @param name is the new name for the mechanic.
	 * @param surname is the new surname for the mechanic.
	 */
	public UpdateMechanic(long id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	@Override
	public void execute() {
		PersistenceFactory.getMechanicsGateway().updateMechanic(this.id, this.name, this.surname);
	}

}
