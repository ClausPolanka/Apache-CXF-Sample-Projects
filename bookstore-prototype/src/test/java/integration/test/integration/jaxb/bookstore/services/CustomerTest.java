package test.integration.jaxb.bookstore.services;

import javax.jws.WebService;

import bookstore.Customer;

@WebService
public interface CustomerTest {
	Customer testCustomer(Customer customer);
}
