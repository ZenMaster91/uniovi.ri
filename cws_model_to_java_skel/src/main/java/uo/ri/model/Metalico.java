package uo.ri.model;

import javax.persistence.Entity;

@Entity
public class Metalico extends MedioPago {

	Metalico() {}

	public Metalico( Cliente cliente ) {
		Association.Pagar.link( cliente, this );
	}

}
