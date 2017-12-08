package uo.ri.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity public class TarjetaCredito extends MedioPago {

	@Column(unique = true) private String numero;
	private String tipo;
	private Date validez;

	/**
	 * Allocates a credit card object and initializes it.
	 */
	TarjetaCredito() {}

	/**
	 * Allocates a credit card object and initializes it.
	 * 
	 * @param numero is the number of the credit card.
	 */
	public TarjetaCredito( String numero ) {
		super();
		this.numero = numero;
	}

	/**
	 * @return the number of the credit card.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @return the type of credit card.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Changes the type of the card.
	 * 
	 * @param tipo to set.
	 */
	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	/**
	 * @return the expiration date of the card.
	 */
	public Date getValidez() {
		return validez;
	}

	/**
	 * Changes the expiration date of the credit card.
	 * 
	 * @param validez is the expiration date of the card.
	 */
	public void setValidez( Date validez ) {
		this.validez = validez;
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		return result;
	}

	@Override public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TarjetaCredito other = (TarjetaCredito) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals( other.numero ))
			return false;
		return true;
	}

	@Override public String toString() {
		return "TarjetaCredito [numero=" + numero + ", tipo=" + tipo + ", validez=" + validez
				+ ", acumulado="
				+ acumulado + "]";
	}

}
