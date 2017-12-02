package uo.ri.persistence;

import java.util.List;
import java.util.Map;

/**
 * 
 * Interface of the Mechanic Gateway.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711231134
 * @since 201711201810
 */
public interface MechanicsGateway {

	/**
	 * Adds a mechanic to the database. For that the method will receive as
	 * parameter the name and surname of the mechanic we want to add and
	 * automatically will create a new mechanic in the database and create an id
	 * for it.
	 * 
	 * @param name of the mechanic.
	 * @param surname of the mechanic.
	 */
	public void addMechanic(String name, String surname);

	/**
	 * Removes a mechanic from the database. For that the method receives an id
	 * of type long that will be the id of the mechanic we want to remove from
	 * the database.
	 * 
	 * @param id of the mechanic that we want to delete from the database.
	 */
	public void deleteMechanic(Long id);

	/**
	 * Returns a list with all the mechanics in the database. The list returned
	 * is a java.util.List Type containing Map<String, Object> values.
	 * 
	 * @return a list containing all the mechanics in the database. The map for
	 *         each mechanic it is composed by the key [id, name, surname] and
	 *         the value.
	 */
	public List<Map<String, Object>> findAllMechanics();

	/**
	 * Updates the mechanic whose id matches the id given as a parameter.
	 * Updates the name and surname values by looking for the mechanic with the
	 * id provided and updating only those values.
	 * 
	 * @param id of the mechanic to update.
	 * @param name new name or same as before.
	 * @param surname new name or same as before.
	 */
	public void updateMechanic(Long id, String name, String surname);

}
