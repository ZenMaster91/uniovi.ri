package uo.ri.business.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class DeleteMechanic implements Action {
	
	private static String SQL = "delete from TMecanicos where id = ?";
	private long idMechanic;
	
	public DeleteMechanic(long idMechanic) {
		this.idMechanic = idMechanic;
	}

	@Override
	public void execute() throws BusinessException {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}

}
