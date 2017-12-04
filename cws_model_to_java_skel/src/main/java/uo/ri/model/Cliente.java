package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import uo.ri.model.types.Address;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String dni;
	private String nombre;
	private String apellidos;
	private Address address;

	@OneToMany(mappedBy = "cliente")
	private Set<Vehiculo> vehiculos = new HashSet<>();

	@OneToMany(mappedBy = "cliente")
	private Set<MedioPago> mediosDePago = new HashSet<>();

	/**
	 * Allocates a client object and initializes it so that it represents a
	 * client from the real world.
	 */
	Cliente() {}

	/**
	 * Allocates a client object and initializes it so that it represents a
	 * client from the real world.
	 * 
	 * @param dni of the client.
	 */
	public Cliente( String dni ) {
		super();
		this.dni = dni;
	}

	/**
	 * Allocates a client object and initializes it so that it represents a
	 * client from the real world.
	 *
	 * @param dni to set.
	 * @param nombre of the client
	 * @param apellidos of the client.
	 */
	public Cliente( String dni, String nombre, String apellidos ) {
		this( dni );
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the dni of the client.
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * @return the name of the client.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Changes the name of the client.
	 * 
	 * @param nombre of the client.
	 */
	public void setNombre( String nombre ) {
		this.nombre = nombre;
	}

	/**
	 * @return the surname of the client.
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Changes the surname of the client.
	 * 
	 * @param apellidos of the client to change/set.
	 */
	public void setApellidos( String apellidos ) {
		this.apellidos = apellidos;
	}

	/**
	 * @return the address of the client.
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Changes the address of the client.
	 * 
	 * @param address of the client to change/set.
	 */
	public void setAddress( Address address ) {
		this.address = address;
	}

	/**
	 * @return a copy of the original set of vehicles of the client.
	 */
	public Set<Vehiculo> getVehiculos() {
		return new HashSet<>( vehiculos );
	}

	/**
	 * @return a copy of the original set of payment types.
	 */
	public Set<MedioPago> getMediosPago() {
		return new HashSet<>( mediosDePago );
	}

	/**
	 * @return the original set of vehicles of the client.
	 */
	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	/**
	 * @return the original set of payment types of the client.
	 */
	Set<MedioPago> _getMediosPago() {
		return mediosDePago;
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
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals( other.dni ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", address=" + address + "]";
	}

}
