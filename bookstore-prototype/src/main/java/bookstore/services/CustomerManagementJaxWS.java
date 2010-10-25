package bookstore.services;

import org.apache.cxf.jaxrs.client.WebClient;

import bookstore.Customer;
import bookstore.CustomerManagement;

public class CustomerManagementJaxWS implements CustomerManagement {

	@Override
	public Customer createCustomer(Customer customer) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void deleteCustomer(String id) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public Customer getCustomer(String id) {
		WebClient client = WebClient.create("http://localhost:9000/");
		Customer customer = client.path("customerservice/customers/" + id).accept("application/xml").get(Customer.class);
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		throw new RuntimeException("Not implemented yet");
	}

}
