package uo.ri.conf;

import uo.ri.persistence.FaultGateway;
import uo.ri.persistence.InvoiceGateway;
import uo.ri.persistence.MechanicsGateway;
import uo.ri.persistence.impl.FaultGatewayImpl;
import uo.ri.persistence.impl.InvoiceGatewayImpl;
import uo.ri.persistence.impl.MechanicGatewayImpl;

/**
 * Factory for the persistence model.
 *
 * @author Guillermo Facundo Colunga
 * @version 201711201805
 * @since 201711201805
 */
public class PersistenceFactory {

	/**
	 * Returns the mechanic gateway implementation.
	 * @return the mechanic gateway implementation.
	 */
	public static MechanicsGateway getMechanicsGateway() {
		return new MechanicGatewayImpl();
	}
	
	public static InvoiceGateway getInvoiceGateway() {
		return new InvoiceGatewayImpl();
	}
	
	public static FaultGateway getFaultGateway() {
		return new FaultGatewayImpl();
	}
}
