package uo.ri.conf;

import uo.ri.persistence.MechanicsGateway;
import uo.ri.persistence.impl.MechanicGatewayImpl;

public class PersistenceFactory {

	
	public static MechanicsGateway getMechanicsGateway() {
		return new MechanicGatewayImpl();
	}
}
