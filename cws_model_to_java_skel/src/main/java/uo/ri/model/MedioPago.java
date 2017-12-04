package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	protected double acumulado = 0.0;

	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "medioPago")
	private Set<Cargo> cargos = new HashSet<>();

	/**
	 * @return the unique id of the object. JPA.
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the client that if the owner of the payment type.
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @return the accumulated value payed in the payment type.
	 */
	public double getAcumulado() {
		return acumulado;
	}

	/**
	 * Changes the accumulated value of the payment type.
	 * 
	 * @param acumulado is the new accumulated.
	 */
	public void setAcumulado( double acumulado ) {
		this.acumulado = acumulado;
	}

	/**
	 * @return a copy of the set of charges done to the payment type.
	 */
	public Set<Cargo> getCargos() {
		return new HashSet<>( cargos );
	}

	/**
	 * Changes the client that is the owner of the payment type.
	 * 
	 * @param cliente that will be the new owner.
	 */
	void _setCliente( Cliente cliente ) {
		this.cliente = cliente;
	}

	/**
	 * @return the original set of charges done on the payment type.
	 */
	Set<Cargo> _getCargos() {
		return cargos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
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
		MedioPago other = (MedioPago) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals( other.cliente ))
			return false;
		return true;
	}

}
