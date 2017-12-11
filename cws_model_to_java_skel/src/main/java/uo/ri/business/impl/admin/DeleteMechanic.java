package uo.ri.business.impl.admin;

import uo.ri.business.impl.Command;
import uo.ri.business.repository.MecanicoRepository;
import uo.ri.conf.Factory;
import uo.ri.model.Mecanico;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

public class DeleteMechanic implements Command<Void> {

	private Long idMecanico;

	public DeleteMechanic( Long idMecanico ) {
		this.idMecanico = idMecanico;
	}

	@Override public Void execute() throws BusinessException {
		MecanicoRepository r = Factory.repository.forMechanic();
		Mecanico m = r.findById( idMecanico );
		// Checking that the mechanic can be deleted.
		assertCanBeRemoved(m);
		r.remove( m );
		return null;
	}

	private void assertCanBeRemoved( Mecanico m ) throws BusinessException {
		Check.isTrue( m.getIntervenciones().isEmpty(), "El mecánico tiene intervenciones.");
		Check.isTrue( m.getAsignadas().isEmpty(), "El mecánico tiene averías asignadas.");
		Check.isNotNull( m, "El mecanico no existe" );
	}

}
