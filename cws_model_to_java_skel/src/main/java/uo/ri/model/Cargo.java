package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.FacturaStatus;

@Entity
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Factura factura;
	@ManyToOne
	private MedioPago medioPago;
	private double importe = 0.0;

	/**
	 * Allocates a cargo object and initializes it so that it represents a
	 * payment.
	 */
	Cargo() {}

	/**
	 * Allocates a cargo object and initializes it so that it represents a
	 * payment from an invoice with a payment type and an amount of money.
	 * 
	 * @param factura represents the invoice.
	 * @param medioPago that has been used in the payment.
	 * @param importe is the amount of money payed.
	 * @throws BusinessException if the payment cannot be done.
	 */
	public Cargo( Factura factura, MedioPago medioPago, double importe ) throws BusinessException {
		super();
		medioPago.acumulado += importe;
		this.importe = importe;
		Association.Cargar.link( factura, this, medioPago );

	}

	/**
	 * @return the unique id of the object. JPA.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the current invoice.
	 */
	public Factura getFactura() {
		return factura;
	}

	/**
	 * @return the payment type used.
	 */
	public MedioPago getMedioPago() {
		return medioPago;
	}

	/**
	 * Rolls-back the payment from the invoice and from the payment type.
	 * 
	 * @throws BusinessException if the action cannot be done.
	 */
	public void rewind() throws BusinessException {
		if (this.factura.getStatus().equals( FacturaStatus.SIN_ABONAR )) {
			medioPago.acumulado -= this.importe;
			Association.Cargar.unlink( this );
		}
	
	}

	/**
	 * Changes the current invoice.
	 * 
	 * @param factura to set.
	 */
	void _setFactura( Factura factura ) {
		this.factura = factura;
	}

	/**
	 * Changes the payment type.
	 * 
	 * @param medioPago to set.
	 */
	void _setMedioPago( MedioPago medioPago ) {
		this.medioPago = medioPago;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( factura == null ) ? 0 : factura.hashCode() );
		result = prime * result + ( ( medioPago == null ) ? 0 : medioPago.hashCode() );
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
		Cargo other = (Cargo) obj;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals( other.factura ))
			return false;
		if (medioPago == null) {
			if (other.medioPago != null)
				return false;
		} else if (!medioPago.equals( other.medioPago ))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cargo [factura=" + factura + ", medioPago=" + medioPago + ", importe=" + importe
				+ "]";
	}

}
