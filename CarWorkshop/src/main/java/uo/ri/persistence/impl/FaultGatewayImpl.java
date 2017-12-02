package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.FaultGateway;

public class FaultGatewayImpl implements FaultGateway {

	@Override
	public void verificarAveriasTerminadas(List<Long> idsAveria)
			throws BusinessException {
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
		} catch (SQLException e) {
			throw new BusinessException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void cambiarEstadoAverias(List<Long> idsAveria, String status)
			throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));

			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new BusinessException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void actualizarImporteAveria(Long idAveria, double totalAveria)
			throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public double consultaImporteRepuestos(Long idAveria)
			throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(Conf.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}

			return rs.getDouble(1);

		} catch (SQLException e) {
			throw new BusinessException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public double consultaImporteManoObra(Long idAveria)
			throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(Conf.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException(
						"La averia no existe o no se puede facturar");
			}

			return rs.getDouble(1);

		} catch (SQLException e) {
			throw new BusinessException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
