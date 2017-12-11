package uo.ri.business.impl.admin;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.Command;
import uo.ri.business.impl.util.DtoAssembler;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;

public class FindAllMechanics implements Command<List<MechanicDto>> {

	@Override public List<MechanicDto> execute() {
		MecanicoRepository r = Factory.repository.forMechanic();
		return DtoAssembler.toMechanicDtoList( r.findAll() );
	}

}
