package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class UpdateMechanic implements Action {

	private long mechanicId;
	private String name, surname;

	private static String SQL = "update TMecanicos "
			+ "set nombre = ?, apellidos = ? " + "where id = ?";

	public UpdateMechanic(long mechanicId, String name, String surname) {
		this.mechanicId = mechanicId;
		this.name = name;
		this.surname = surname;
	}

	@Override
	public void execute() throws BusinessException {
		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, this.name);
			pst.setString(2, this.surname);
			pst.setLong(3, this.mechanicId);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
