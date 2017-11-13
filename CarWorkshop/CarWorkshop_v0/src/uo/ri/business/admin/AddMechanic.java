package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class AddMechanic implements Action {

	private static String SQL = "insert into TMecanicos(nombre, apellidos) values (?, ?)";
	private String name, surname;

	public AddMechanic(String name, String surname) {
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
			pst.setString(1, name);
			pst.setString(2, surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
