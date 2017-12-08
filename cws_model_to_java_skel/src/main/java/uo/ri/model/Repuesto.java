package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity public class Repuesto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;

	private String codigo;
	private String descripcion;
	private double precio;

	@OneToMany(mappedBy = "repuesto") private Set<Sustitucion> sustituciones = new HashSet<>();

	/**
	 * Allocates a replacement object and initializes it so that it represents.
	 */
	Repuesto() {}

	/**
	 * Allocates a replacement object and initializes it so that it represents.
	 * 
	 * @param codigo is the code of the
	 */
	public Repuesto( String codigo ) {
		super();
		this.codigo = codigo;
	}

	/**
	 * Allocates a replacement object and initializes it so that it represents.
	 * 
	 * @param codigo of the replacement.
	 * @param descripcion of the replacement.
	 * @param precio of the replacement.
	 */
	public Repuesto( String codigo, String descripcion, double precio ) {
		this( codigo );
		this.descripcion = descripcion;
		this.precio = precio;
	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the code of the replacement.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the description of the replacement.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Changes the description of the replacement.
	 * 
	 * @param descripcion to set.
	 */
	public void setDescripcion( String descripcion ) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the price of the replacement.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Changes the price of the replacement.
	 * 
	 * @param precio to set.
	 */
	public void setPrecio( double precio ) {
		this.precio = precio;
	}

	/**
	 * @return a copy of the original set of replacements.
	 */
	public Set<Sustitucion> getSustituciones() {
		return new HashSet<Sustitucion>( sustituciones );
	}

	/**
	 * @return the original set of replacements.
	 */
	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( codigo == null ) ? 0 : codigo.hashCode() );
		return result;
	}

	@Override public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Repuesto other = (Repuesto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals( other.codigo ))
			return false;
		return true;
	}

}
