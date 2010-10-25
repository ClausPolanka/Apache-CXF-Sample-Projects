package test.endtoend.bookstore.builder;

import bookstore.Address;

public class AddressBuilder {

	private String id = "addressId";
	private String street = "street";
	private String city = "city";
	private int house = 1;
	private int door = 1;
	private String zipCode = "zipCode";
	private boolean isOther;
	private boolean isBilling;
	private boolean isShipping;

	public static AddressBuilder anAddress() {
		return new AddressBuilder();
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
		return new Address(id, street, city, house, door, zipCode, isShipping, isBilling, isOther);
	}
}
