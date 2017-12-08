package uo.ri.model.types;

import javax.persistence.Embeddable;

@Embeddable public class Address {

	private String street, city, zipcode;

	/**
	 * Default empty constructor, only visible at package level. JPA.
	 */
	Address() {}

	/**
	 * Address type represents a physical address from the real world.
	 * 
	 * @param street
	 * @param city
	 * @param zipcode as a string.
	 */
	public Address( String street, String city, String zipcode ) {
		super();
		this.street = street;
		this.city = city;
		this.zipcode = zipcode;
	}

	/**
	 * @return the street of the address.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the city of the address.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the zip code of the address.
	 */
	public String getZipcode() {
		return zipcode;
	}

	@Override public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( city == null ) ? 0 : city.hashCode() );
		result = prime * result + ( ( street == null ) ? 0 : street.hashCode() );
		result = prime * result + ( ( zipcode == null ) ? 0 : zipcode.hashCode() );
		return result;
	}

	@Override public boolean equals( Object obj ) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals( other.city ))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals( other.street ))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals( other.zipcode ))
			return false;
		return true;
	}

}
