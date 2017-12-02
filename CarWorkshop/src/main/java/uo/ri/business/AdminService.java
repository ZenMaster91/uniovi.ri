package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface AdminService {

	/**
	 * Creates a new mechanic for the given name and surname.
	 * 
	 * @param name of the mechanic.
	 * @param surname of the mechanic.
	 * @throws BusinessException
	 */
	public void newMechanic(String name, String surname)
			throws BusinessException;

	/**
	 * Deletes the mechanic with the given id.
	 * 
	 * @param id of the mechanic to delete.
	 * @throws BusinessException
	 */
	public void deleteMechanic(Long id) throws BusinessException;

	/**
	 * Updates a mechanic from the given id. It will update the name or the
	 * surname.
	 * 
	 * @param id of the mechanic to update.
	 * @param name is the new name that the mechanic with the given id will
	 *            have.
	 * @param surname is the new surname that the mechanic with the given id
	 *            will have.
	 * @throws BusinessException
	 */
	public void updateMechanic(Long id, String name, String surname)
			throws BusinessException;

	/**
	 * Returns a list containing all the mechanic in the database.
	 * 
	 * @return a list containing all the mechanics in the database. It uses a
	 *         Map<String, Object> to store the mechanic attributes.
	 * @throws BusinessException
	 */
	public List<Map<String, Object>> findAllMechanics()
			throws BusinessException;

}
