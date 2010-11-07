package test.endtoend.bookstore.builder;

import bookstore.Address;

public class AddressBuilder {

	private String id = "addressId";
	private String street = "street";
	private String city = "city";
	private int houseNr = 1;
	private int door = 1;
	private String zipCode = "zipCode";
	private boolean isOther;
	private boolean isBilling;
	private boolean isShipping;

	public static AddressBuilder anAddress() {
		return new AddressBuilder();
	}

	public static Address anAddressAtUniversity() {
		// @formatter:off
		// @formatter:off
		return anAddress()
				.onStreet("Argentinierstrasse")
				.withHouseNr(8)
				.withDoorNr(1)
				.withZipCode("1040")
				.inCity("Vienna").build();
		// @formatter:on
	}

	public AddressBuilder withAddressId(String id) {
		this.id = id;
		return this;
	}

	public AddressBuilder onStreet(String street) {
		this.street = street;
		return this;
	}

	public AddressBuilder withHouseNr(int houseNr) {
		this.houseNr = houseNr;
		return this;
	}

	public AddressBuilder withDoorNr(int doorNr) {
		this.door = doorNr;
		return this;
	}

	public AddressBuilder withZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}

	public AddressBuilder inCity(String city) {
		this.city = city;
		return this;
	}

	public AddressBuilder shippable() {
		this.isShipping = true;
		return this;
	}

	public AddressBuilder asBillingAddress() {
		this.isBilling = true;
		return this;
	}

	public AddressBuilder asOther() {
		this.isOther = true;
		return this;
	}

	public Address build() {
		return new Address(id, street, city, houseNr, door, zipCode, isShipping, isBilling, isOther);
	}
}
