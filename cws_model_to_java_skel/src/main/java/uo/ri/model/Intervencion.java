package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity @Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = "AVERIA_ID, MECANICO_ID") }) public class Intervencion {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

	@ManyToOne private Averia averia;

	@ManyToOne private Mecanico mecanico;
	private int minutos;

	@OneToMany(mappedBy = "intervencion") private Set<Sustitucion> sustituciones = new HashSet<>();

	/**
	 * Allocates a intervention object and initializes it.
	 */
	Intervencion() {}

	/**
	 * Allocates a intervention object and initializes it.
	 * 
	 * @param mecanico that performs the action.
	 * @param averia where the action is performed.
	 */
	public Intervencion( Mecanico mecanico, Averia averia ) {
		super();
		Association.Intervenir.link( averia, this, mecanico );
	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the cost of the intervention.
	 */
	public double getImporte() {
		double acum = 0;
		for (Sustitucion sustitucion : sustituciones) {
			acum += sustitucion.getImporte();
		}
		acum += ( getMinutos() * this.averia.getVehiculo().getTipo().getPrecioHora() ) / 60;
		return acum;
	}

	/**
	 * @return the fault where the intervention takes place.
	 */
	public Averia getAveria() {
		return averia;
	}

	/**
	 * @return the mechanic that performs the intervention.
	 */
	public Mecanico getMecanico() {
		return mecanico;
	}

	/**
	 * @return the minutes that the intervention needed to be completed.
	 */
	public int getMinutos() {
		return minutos;
	}

	/**
	 * Changes the amount of time in minutes that the action needed to be
	 * completed.
	 * 
	 * @param minutos to set.
	 */
	public void setMinutos( int minutos ) {
		this.minutos = minutos;
	}

	/**
	 * @return a copy of the original set of substitutions performed.
	 */
	public Set<Sustitucion> getSustituciones() {
		return new HashSet<>( sustituciones );
	}

	/**
	 * @return the original set of substitutions performed.
	 */
	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}

	/**
	 * Sets the fault where the action takes place.
	 * 
	 * @param averia to set
	 */
	void _setAveria( Averia averia ) {
		this.averia = averia;
	}

	/**
	 * Changes the mechanic that performs the action.
	 * 
	 * @param mecanico to set.
	 */
	void _setMecanico( Mecanico mecanico ) {
		this.mecanico = mecanico;
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( averia == null ) ? 0 : averia.hashCode() );
		result = prime * result + ( ( mecanico == null ) ? 0 : mecanico.hashCode() );
		return result;
	}

	@Override public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervencion other = (Intervencion) obj;
		if (averia == null) {
			if (other.averia != null)
				return false;
		} else if (!averia.equals( other.averia ))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals( other.mecanico ))
			return false;
		return true;
	}

	@Override public String toString() {
		return "Intervencion [averia=" + averia + ", mecanico=" + mecanico + ", minutos=" + minutos
				+ "]";
	}

}
