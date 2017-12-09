package uo.ri.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import alb.util.date.DateUtil;
import alb.util.math.Round;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;

@Entity public class Factura {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	private Long numero;
	private Calendar fecha = Calendar.getInstance();
	private double importe;
	private FacturaStatus facturaStatus = FacturaStatus.SIN_ABONAR;
	@OneToMany(mappedBy = "factura") private Set<Averia> averias = new HashSet<>();
	@OneToMany(mappedBy = "factura") private Set<Cargo> cargos = new HashSet<>();

	/**
	 * Allocates a invoice object and initializes it so that it represents an
	 * invoice from the real world.
	 */
	Factura() {}

	/**
	 * Allocates a invoice object and initializes it so that it represents an
	 * invoice from the real world.
	 * 
	 * @param numero is the number of the invoice.
	 */
	public Factura( Long numero ) {
		super();
		this.numero = numero;
	}

	/**
	 * Allocates a invoice object and initializes it so that it represents an
	 * invoice from the real world.
	 * 
	 * @param numero is the number of the invoice.
	 * @param fecha is the date where the invoice takes place.
	 */
	public Factura( Long numero, Date fecha ) {
		this( numero );
		this.fecha.setTime( fecha );
	}

	/**
	 * Allocates a invoice object and initializes it so that it represents an
	 * invoice from the real world.
	 * 
	 * @param numero is the number of the invoice.
	 * @param averias the list of faults that are billed in this invoice.
	 * @throws BusinessException if there's any fault that has not been fixed
	 *             yet.
	 */
	public Factura( long numero, List<Averia> averias ) throws BusinessException {
		this( numero );
		for (Averia a : averias) {
			if (a.getStatus() != AveriaStatus.TERMINADA) {
				throw new BusinessException( "Avería no terminada" );
			} else {
				addAveria( a );
			}
		}
	}

	/**
	 * Allocates a invoice object and initializes it so that it represents an
	 * invoice from the real world.
	 * 
	 * @param numero is the number of the invoice.
	 * @param fecha is the date where the invoice takes place.
	 * @param averias the list of faults that are billed in this invoice.
	 * @throws BusinessException if there's any fault that has not been fixed
	 *             yet.
	 */
	public Factura( long numero, Date fecha, List<Averia> averias ) throws BusinessException {
		this( numero, fecha );
		for (Averia a : averias) {
			if (a.getStatus() != AveriaStatus.TERMINADA) {
				throw new BusinessException( "Avería no terminada" );
			} else {
				addAveria( a );
			}
		}
	}

	/**
	 * @return the id unique if of the object. JPA.
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return the number of the invoice.
	 */
	public Long getNumero() {
		return numero;
	}

	/**
	 * @return the date of the invoice.
	 */
	public Calendar getFecha() {
		return fecha;
	}

	/**
	 * Changes the date of the invoice.
	 * 
	 * @param fecha is the new date.
	 */
	public void setFecha( Date fecha ) {
		this.fecha.setTime( fecha );
	}

	/**
	 * @return the tax percentage corresponding to the period of the invoice.
	 */
	public double getIva() {
		Date limit = DateUtil.fromString( "01/07/2012" );

		if (this.fecha != null) {
			Date actual = DateUtil.fromString(
					"" + this.fecha.get( Calendar.DAY_OF_MONTH ) + "/"
							+ this.fecha.get( Calendar.MONTH ) + "/"
							+ this.fecha.get( Calendar.YEAR ) + "" );
			if (actual.before( limit )) {
				return 0.18;
			}
		}
		return 0.21;
	}

	/**
	 * @return the status of the invoice.
	 */
	public FacturaStatus getStatus() {
		return facturaStatus;
	}

	/**
	 * Changes the status of the invoice.
	 * 
	 * @param newStatus is the new Status of the invoice.
	 */
	public void setStatus( FacturaStatus newStatus ) {
		this.facturaStatus = newStatus;
	}

	/**
	 * @return the amount of the invoice.
	 */
	public double getImporte() {
		return importe;
	}

	/**
	 * @return a copy of the original set of faults that will be charged in the
	 *         invoice.
	 */
	public Set<Averia> getAverias() {
		return new HashSet<>( averias );
	}

	/**
	 * @return a copy of the original set of payments done to this invoice.
	 */
	public Set<Cargo> getCargos() {
		return new HashSet<>( cargos );
	}

	/**
	 * Adds a given fault to the invoice.
	 * 
	 * @param averia to add to the invoice.
	 */
	public void addAveria( Averia averia ) {
		if (this.getStatus().equals( FacturaStatus.SIN_ABONAR )) {
			if (averia.getStatus().equals( AveriaStatus.TERMINADA )) {
				Association.Facturar.link( this, averia );
				averia.markAsInvoiced();
				calcularImporte();
			}
		}
	}

	/**
	 * Deletes the fault from the invoice. Only if the invoice has not been
	 * payed.
	 * 
	 * @param averia to remove from the invoice.
	 */
	public void removeAveria( Averia averia ) {
		if (this.getStatus().equals( FacturaStatus.SIN_ABONAR )) {
			Association.Facturar.unlink( this, averia );
			averia.markBackToFinished();
			importe = 0;
		}

	}

	/**
	 * @return original set of faults that will be charged in the invoice.
	 */
	Set<Averia> _getAverias() {
		return averias;
	}

	/**
	 * @return the original set of payments done to this invoice.
	 */
	Set<Cargo> _getCargos() {
		return cargos;
	}

	/**
	 * Calculates the import of the invoice.
	 * 
	 * @throws ParseException if any error while calculating the amount of the
	 *             invoice.
	 */
	void calcularImporte() {
		double acum = 0;
		for (Averia averia : averias) {
			acum += averia.getImporte();
		}
		this.importe = Round.twoCents( ( acum * getIva() ) + acum );
	}

}
