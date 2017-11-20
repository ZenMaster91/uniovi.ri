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
 * Updates the data of a given mechanic. The mechanic is given through its id.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201749
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
	public void execute() throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
			pst.setString(1, this.name);
			pst.setString(2, this.surname);
			pst.setLong(3, this.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
