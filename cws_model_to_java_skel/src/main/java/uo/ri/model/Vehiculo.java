package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String marca;

	@Column(unique = true)
	private String matricula;
	private String modelo;
	private int numAverias = 0;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private TipoVehiculo tipoVehiculo;

	@OneToMany(mappedBy = "vehiculo")
	private Set<Averia> averias = new HashSet<>();

	/**
	 * Allocates a vehicle object and initializes it.
	 */
	Vehiculo() {}

	/**
	 * Allocates a vehicle object and initializes it.
	 * 
	 * @param matricula of the vehicle.
	 */
	public Vehiculo( String matricula ) {
		super();
		this.matricula = matricula;
	}

	/**
	 * Allocates a vehicle object and initializes it.
	 * 
	 * @param matricula of the vehicle.
	 * @param marca of the vehicle.
	 * @param modelo of the vehicle.
	 */
	public Vehiculo( String matricula, String marca, String modelo ) {
		this( matricula );
		this.marca = marca;
		this.modelo = modelo;
	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the plate number of the vehicle.
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @return the client that is the owner of the vehicle.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @return the type of vehicle.
	 */
	public TipoVehiculo getTipo() {
		return tipoVehiculo;
	}

	/**
	 * @return a copy of the original set of faults of the vehicle.
	 */
	public Set<Averia> getAverias() {
		return new HashSet<>( averias );
	}

	/**
	 * @return the brand of the vehicle.
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Changes the brand of the vehicle.
	 * 
	 * @param marca is the new brand of the vehicle.
	 */
	public void setMarca( String marca ) {
		this.marca = marca;
	}

	/**
	 * @return the model of the vehicle.
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Changes the model of the vehicle.
	 * 
	 * @param modelo is the model of the vehicle.
	 */
	public void setModelo( String modelo ) {
		this.modelo = modelo;
	}

	/**
	 * @return the number of faults of that vehicle.
	 */
	public int getNumAverias() {
		this.numAverias = averias.size();
		return numAverias;
	}

	/**
	 * Changes the number of faults of the vehicle.
	 * 
	 * @param numAverias of the vehicle.
	 */
	public void setNumAverias( int numAverias ) {
		this.numAverias = numAverias;
	}

	/**
	 * Changes the client that is the owner of the vehicle.
	 * 
	 * @param cliente that is the owner of the vehicle.
	 */
	void _setCliente( Cliente cliente ) {
		this.cliente = cliente;
	}

	/**
	 * Changes vehicle type.
	 * 
	 * @param tipoVehiculo is the new type of vehicle.
	 */
	void _setTipo( TipoVehiculo tipoVehiculo ) {
		this.tipoVehiculo = tipoVehiculo;
	}

	/**
	 * @return the original set of faults of the vehicle.
	 */
	Set<Averia> _getAverias() {
		return averias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( matricula == null ) ? 0 : matricula.hashCode() );
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
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals( other.matricula ))
			return false;
		return true;
	}

}
