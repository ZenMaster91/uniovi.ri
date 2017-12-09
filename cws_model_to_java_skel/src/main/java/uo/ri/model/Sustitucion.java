package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity @Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "REPUESTO_ID, INTERVENCION_ID") }) public class Sustitucion {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
	@ManyToOne private Repuesto repuesto;
	@ManyToOne private Intervencion intervencion;
	private int cantidad;
	
	/**
	 * Allocates a substitution object and initializes it.
	 */
	Sustitucion() {}

	/**
	 * Allocates a substitution object and initializes it.
	 * 
	 * @param repuesto used in the substitution.
	 * @param intervencion that changed the replacement.
	 */
	public Sustitucion( Repuesto repuesto, Intervencion intervencion ) {
		super();
		Association.Sustituir.link( repuesto, this, intervencion );
	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the amount of the substitution.
	 */
	public double getImporte() {
		return this.cantidad * this.repuesto.getPrecio();
	}

	/**
	 * @return the replacement used.
	 */
	public Repuesto getRepuesto() {
		return repuesto;
	}

	/**
	 * @return the intervention attached to the substitution.
	 */
	public Intervencion getIntervencion() {
		return intervencion;
	}

	/**
	 * @return the amount of the substitution.
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Changes the quantity of the substitution.
	 * 
	 * @param cantidad to set.
	 */
	public void setCantidad( int cantidad ) {
		this.cantidad = cantidad;
	}

	/**
	 * Changes the replacement of the substitution.
	 * 
	 * @param repuesto to set.
	 */
	void _setRepuesto( Repuesto repuesto ) {
		this.repuesto = repuesto;
	}

	/**
	 * Changes the intervention of the substitution.
	 * 
	 * @param intervencion to set.
	 */
	void _setIntervencion( Intervencion intervencion ) {
		this.intervencion = intervencion;
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( intervencion == null ) ? 0 : intervencion.hashCode() );
		result = prime * result + ( ( repuesto == null ) ? 0 : repuesto.hashCode() );
		return result;
	}

	@Override public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sustitucion other = (Sustitucion) obj;
		if (intervencion == null) {
			if (other.intervencion != null)
				return false;
		} else if (!intervencion.equals( other.intervencion ))
			return false;
		if (repuesto == null) {
			if (other.repuesto != null)
				return false;
		} else if (!repuesto.equals( other.repuesto ))
			return false;
		return true;
	}

	@Override public String toString() {
		return "Sustitucion [repuesto=" + repuesto + ", intervencion=" + intervencion
				+ ", cantidad=" + cantidad + "]";
	}

}
