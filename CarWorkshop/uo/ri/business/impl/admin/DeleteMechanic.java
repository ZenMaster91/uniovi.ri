package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;

/**
 * 
 * Deletes a mechanic from the database from its own given id.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201741
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
	public void execute() throws BusinessException {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_DELETE_MECHANIC"));
			pst.setLong(1, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
