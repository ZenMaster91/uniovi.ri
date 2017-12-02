package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.MechanicsGateway;

/**
 * 
 * Implementation for the gateway of the mechanic.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711231134
 * @since 201711201808
 */
public class MechanicGatewayImpl implements MechanicsGateway {

	@Override
	public void addMechanic(String name, String surname) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_INSERT_MECHANIC"));
			pst.setString(1, name);
			pst.setString(2, surname);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

	@Override
	public void deleteMechanic(Long id) {
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

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		List<Map<String, Object>> mechanics = new ArrayList<Map<String, Object>>();
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			System.out.println(Conf.get("SQL_FIND_ALL_MECHANICS"));
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

	@Override
	public void updateMechanic(Long id, String name, String surname) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_UPDATE_MECHANIC"));
			pst.setString(1, name);
			pst.setString(2, surname);
			pst.setLong(3, id);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
