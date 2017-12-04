package uo.ri.model;

import javax.persistence.Entity;

@Entity
public class Metalico extends MedioPago {

	/**
	 * Allocates a cash object and initializes it.
	 */
	Metalico() {}

	/**
	 * Allocates a cash object and initializes it.
	 * 
	 * @param cliente
	 */
	public Metalico( Cliente cliente ) {
		Association.Pagar.link( cliente, this );
	}

}
