package uo.ri.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Bono extends MedioPago {

	private double disponible = 0.0;
	private String descripcion;
	@Column(unique = true)
	private String codigo;

	/**
	 * Allocates a Bono object and initializes it so that it represents a pay
	 * way.
	 */
	Bono() {}

	/**
	 * Allocates a Bono object and initializes it so that it represents a pay
	 * way.
	 * 
	 * @param codigo of the bono.
	 */
	public Bono( String codigo ) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Allocates a Bono object and initializes it so that it represents a pay
	 * way.
	 * 
	 * @param codigo of the bono.
	 * @param disponible represents the amount of money that the bono contains.
	 */
	public Bono( String codigo, double disponible ) {
		this( codigo );
		this.disponible = disponible;
	}

	/**
	 * @return the code of the bono.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the amount of money that the bono contains.
	 */
	public double getDisponible() {
		return disponible;
	}

	/**
	 * Changes the amount of money available in the bono.
	 * 
	 * @param disponible is the new amount of money available in the bono.
	 */
	public void setDisponible( double disponible ) {
		this.disponible = disponible;
	}

	/**
	 * @return the description of the bono.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Changes the description of the bono.
	 * 
	 * @param descripcion of the bono.
	 */
	public void setDescripcion( String descripcion ) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bono other = (Bono) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals( other.codigo ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bono [disponible=" + disponible + ", descripcion=" + descripcion + ", codigo="
				+ codigo + ", acumulado="
				+ acumulado + "]";
	}

}
