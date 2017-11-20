package uo.ri.persistence;

/**
 * 
 * Interface of the Mechanic Gateway.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201810
 * @since 201711201810
 */
public interface MechanicsGateway {
	
	/**
	 * Adds a mechanic to the database.
	 * 
	 * @param name of the mechanic.
	 * @param surname of the mechanic.
	 */
	public void addMechanic(String name, String surname);

}
