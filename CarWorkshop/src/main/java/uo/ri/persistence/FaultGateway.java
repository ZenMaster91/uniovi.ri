package uo.ri.persistence;

import java.util.List;

import uo.ri.common.BusinessException;

public interface FaultGateway {

	/**
	 * 
	 * @param idsAveria
	 * @throws BusinessException
	 */
	void verificarAveriasTerminadas(List<Long> idsAveria) throws BusinessException;
	
	/**
	 * 
	 * @param idsAveria
	 * @param status
	 * @throws BusinessException
	 */
	void cambiarEstadoAverias(List<Long> idsAveria, String status) throws BusinessException;
	
	/**
	 * 
	 * @param idAveria
	 * @param totalAveria
	 * @throws BusinessException
	 */
	void actualizarImporteAveria(Long idAveria, double totalAveria) throws BusinessException;
	
	/**
	 * 
	 * @param idAveria
	 * @return
	 * @throws BusinessException
	 */
	double consultaImporteRepuestos(Long idAveria) throws BusinessException;
	
	/**
	 * 
	 * @param idAveria
	 * @return
	 * @throws BusinessException
	 */
	double consultaImporteManoObra(Long idAveria) throws BusinessException;
}
