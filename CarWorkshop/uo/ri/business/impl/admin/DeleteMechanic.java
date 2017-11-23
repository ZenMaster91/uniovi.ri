package uo.ri.business.impl.admin;


import alb.util.menu.Action;
import uo.ri.conf.PersistenceFactory;

/**
 * 
 * Deletes a mechanic from the database from its own given id.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711231134
 * @since 201711201741
 */
public class DeleteMechanic implements Action {
	
	// Id of the mechanic to delete.
	private long id;

	/**
	 * @param id of the mechanic to remove from database.
	 */
	public DeleteMechanic(long id) {
		this.id = id;
	}

	@Override
	public void execute() {
		PersistenceFactory.getMechanicsGateway().deleteMechanic(this.id);
	}

}
