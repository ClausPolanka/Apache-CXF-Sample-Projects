package bookstore.services;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomerWithAddressesAndOpenBalanceOfFive;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import bookstore.Customer;

public class CustomerDatabase {

	public static CustomerDatabase database = new CustomerDatabase();

	public Map<String, Customer> customers = new HashMap<String, Customer>();

	private CustomerDatabase() {
		createTestCustomers();
	}

	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	void createTestCustomers() {
		Customer aCustomer = aCustomerWithAddressesAndOpenBalanceOfFive();
		customers.put(aCustomer.getId(), aCustomer);
	}

	public void deleteCustomer(String id) {
		Customer c = customers.get(id);
		if (c != null) {
			customers.remove(id);
		}
	}

	public Customer getCustomer(String id) {
		return customers.get(id);
	}

	public void updateCustomer(Customer customer) {
		Customer c = customers.get(customer.getId());
		c.setAddresses(customer.getAddresses());
		c.setName(customer.getName());
		c.setOpenBalance(customer.getOpenBalance());
		c.setOrders(customer.getOrders());
		if (c != null) {
			customers.put(c.getId(), c);
		}
	}

	public void updateAccount(Customer customer, BigDecimal balance) {
		Customer c = customers.get(customer.getId());
		c.setOpenBalance(balance);
		if (c != null) {
			customers.put(c.getId(), c);
		}

	}
}