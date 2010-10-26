package test.endtoend.bookstore.builder;

import java.util.ArrayList;
import java.util.List;

import bookstore.Address;
import bookstore.Customer;
import bookstore.Order;

public class CustomerBuilder {

	private String id = "customerId";
	private String name = "customer";
	private List<Address> addresses = new ArrayList<Address>();
	private List<Order> orders = new ArrayList<Order>();

	public static CustomerBuilder aCustomer() {
		return new CustomerBuilder();
	}

	public CustomerBuilder withAddress(Address address) {
		this.addresses.add(address);
		return this;
	}

	public CustomerBuilder withOrder(Order order) {
		this.orders.add(order);
		return this;
	}

	public Customer build() {
		Customer customer = new Customer(id, name);
		for (Address each : addresses) {
			customer.addAddress(each);
		}
		for (Order each : orders) {
			customer.addOrder(each);
		}
		return customer;
	}

}
