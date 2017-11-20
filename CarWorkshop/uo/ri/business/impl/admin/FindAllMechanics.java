package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;

/**
 * 
 * Finds all the mechanics in the database.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201745
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
	public List<Map<String, Object>> execute() throws BusinessException {
		List<Map<String, Object>> mechanics = new ArrayList<Map<String, Object>>();
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_FIND_ALL_MECHANICS"));

			rs = pst.executeQuery();
			Map<String, Object> mechanic;
			while (rs.next()) {
				mechanic = new HashMap<String, Object>();
				mechanic.put("id", rs.getLong(1));
				mechanic.put("name", rs.getString(2));
				mechanic.put("surname", rs.getString(3));
				mechanics.add(mechanic);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return mechanics;
	}

}
