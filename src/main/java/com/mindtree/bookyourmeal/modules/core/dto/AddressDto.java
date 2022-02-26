package com.mindtree.bookyourmeal.modules.core.dto;

import java.time.LocalDateTime;

public class AddressDto {
	private int addressId;
	private String doorNumber;
	private String streetName;
	private String locality;
	private String city;
	private String zipcode;
	private String state;
	private LocalDateTime localDateTime= LocalDateTime.now();

	public AddressDto() {
		super();
	}

	public AddressDto(int addressId, String doorNumber, String streetName, String locality, String city, String zipcode,
			String state, LocalDateTime localDateTime) {
		super();
		this.addressId = addressId;
		this.doorNumber = doorNumber;
		this.streetName = streetName;
		this.locality = locality;
		this.city = city;
		this.zipcode = zipcode;
		this.state = state;
		this.localDateTime = LocalDateTime.now();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	@Override
	public String toString() {
		return "AddressDto [addressId=" + addressId + ", doorNumber=" + doorNumber + ", streetName=" + streetName
				+ ", locality=" + locality + ", city=" + city + ", zipcode=" + zipcode + ", state=" + state
				+ ", localDateTime=" + localDateTime + "]";
	}

}