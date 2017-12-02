package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;

final class VerificarAveriasTerminadas implements Action {

	private List<Long> idsAveria;

	protected VerificarAveriasTerminadas(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	@Override
	public void execute() throws Exception {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(Conf.get("SQL_VERIFICAR_ESTADO_AVERIA"));

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException(
							"No existe la averia " + idAveria);
				}

				String status = rs.getString(1);
				if (!"TERMINADA".equalsIgnoreCase(status)) {
					throw new BusinessException(
							"No está terminada la avería " + idAveria);
				}

				rs.close();
			}
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
