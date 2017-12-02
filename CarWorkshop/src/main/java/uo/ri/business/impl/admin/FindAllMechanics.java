package uo.ri.business.impl.admin;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;

/**
 * 
 * Finds all the mechanics in the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711231134
 * @since 201711201745
 */
public class FindAllMechanics {

	/**
	 * Returns the list of all the mechanics in the database.
	 * 
	 * @return all the mechanics as a list of a map with String as the key and
	 *         the object as the value.
	 * @throws BusinessException if there is any error while loading the list of mechanics
	 */
	public List<Map<String, Object>> execute() {
		return PersistenceFactory.getMechanicsGateway().findAllMechanics();
	}

}
