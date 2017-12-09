package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity public class TipoVehiculo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	@Column(unique = true) private String nombre;
	private double precioHora;
	@OneToMany(mappedBy = "tipoVehiculo") private Set<Vehiculo> vehiculos = new HashSet<>();

	/**
	 * Allocates a vehicle type object and initializes it.
	 */
	TipoVehiculo() {}

	/**
	 * Allocates a vehicle type object and initializes it.
	 * 
	 * @param nombre of the vehicle type.
	 */
	public TipoVehiculo( String nombre ) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Allocates a vehicle type object and initializes it.
	 * 
	 * @param nombre of the vehicle type.
	 * @param precioHora is the price for hour of work.
	 */
	public TipoVehiculo( String nombre, double precioHora ) {
		this( nombre );
		this.precioHora = precioHora;
	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the name of the vehicle type.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return the price for hour of work.
	 */
	public double getPrecioHora() {
		return precioHora;
	}

	/**
	 * Changes the price for hour of work.
	 * 
	 * @param precioHora to set.
	 */
	public void setPrecioHora( double precioHora ) {
		this.precioHora = precioHora;
	}

	/**
	 * @return a copy of the original copy of the set of vehicles of this type.
	 */
	public Set<Vehiculo> getVehiculos() {
		return new HashSet<>( vehiculos );
	}

	/**
	 * @return the original copy of the set of vehicles of this type.
	 */
	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nombre == null ) ? 0 : nombre.hashCode() );
		return result;
	}

	@Override public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoVehiculo other = (TipoVehiculo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals( other.nombre ))
			return false;
		return true;
	}

}
