package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService {

	@Override
	public void newMechanic(String name, String surname) throws BusinessException {
		new AddMechanic(name, surname).execute();
	}

	@Override
	public void deleteMechanic(Long id) throws BusinessException {
		new DeleteMechanic(id).execute();
	}

	@Override
	public void updateMechanic(Long id, String name, String surname) throws BusinessException {
		new UpdateMechanic(id, name, surname).execute();
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() throws BusinessException {
		return new FindAllMechanics().execute();
	}

}
