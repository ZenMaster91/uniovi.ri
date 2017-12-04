package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Mecanico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String dni;
	private String apellidos;
	private String nombre;

	@OneToMany(mappedBy = "mecanico")
	private Set<Averia> asigandas = new HashSet<>();

	@OneToMany(mappedBy = "mecanico")
	private Set<Intervencion> intervenciones = new HashSet<>();

	/**
	 * Allocates a mechanic object and initializes.
	 */
	Mecanico() {}

	/**
	 * Allocates a mechanic object and initializes.
	 * 
	 * @param dni of the mechanic
	 */
	public Mecanico( String dni ) {
		super();
		this.dni = dni;
	}

	/**
	 * Allocates a mechanic object and initializes.
	 * 
	 * @param dni of the mechanic.
	 * @param nombre of the mechanic.
	 * @param apellidos of the mechanic.
	 */
	public Mecanico( String dni, String nombre, String apellidos ) {
		this( dni );
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	/**
	 * @return the unique if of the object. JPA.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the national Id of the mechanic.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @return the name of the mechanic.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Changes the name of the mechanic.
	 * 
	 * @param nombre of the mechanic.
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	/**
	 * @return the surname of the mechanic.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Changes the surname of the mechanic.
	 * 
	 * @param apellidos of the mechanic.
	 */
	public void setApellidos( String apellidos ) {
		this.apellidos = apellidos;
	}

	/**
	 * @return a copy of the original set of the faults assigned.
	 */
	public Set<Averia> getAsignadas() {
		return new HashSet<>( asigandas );
	}

	/**
	 * @return a copy of the original set of interventions.
	 */
	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>( intervenciones );
	}

	/**
	 * @return the original set of the faults assigned.
	 */
	Set<Averia> _getAsignadas() {
		return asigandas;
	}

	/**
	 * @return the original set of interventions.
	 */
	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( dni == null ) ? 0 : dni.hashCode() );
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
		Mecanico other = (Mecanico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals( other.dni ))
			return false;
		return true;
	}

}
