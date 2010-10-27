package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomerWithAddressesAndOpenBalanceOfFive;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import bookstore.Customer;
import bookstore.services.CustomerRepository;

public class CustomerTestDatabase implements CustomerRepository {

	private Map<String, Customer> customers = new HashMap<String, Customer>();

	public CustomerTestDatabase() {
		createTestCustomers();
	}

	@Override
	public void addCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
	}

	void createTestCustomers() {
		Customer aCustomer = aCustomerWithAddressesAndOpenBalanceOfFive();
		customers.put(aCustomer.getId(), aCustomer);
	}

	@Override
	public void deleteCustomer(String id) {
		Customer c = customers.get(id);
		if (c != null) {
			customers.remove(id);
		}
	}

	@Override
	public Customer getCustomer(String id) {
		return customers.get(id);
	}

	@Override
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

	@Override
	public void updateAccount(Customer customer, BigDecimal balance) {
		Customer c = customers.get(customer.getId());
		c.setOpenBalance(balance);
		if (c != null) {
			customers.put(c.getId(), c);
		}

	}
}