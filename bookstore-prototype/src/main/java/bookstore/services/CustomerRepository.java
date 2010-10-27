package bookstore.services;

import java.math.BigDecimal;

import bookstore.Customer;

public interface CustomerRepository {

	void addCustomer(Customer customer);

	void deleteCustomer(String id);

	Customer getCustomer(String id);

	void updateCustomer(Customer customer);

	void updateAccount(Customer customer, BigDecimal balance);

}
