package bookstore.services;

import java.math.BigDecimal;

import org.apache.cxf.jaxrs.client.WebClient;

import bookstore.Customer;
import bookstore.CustomerManagement;

public class CustomerManagementJaxWS implements CustomerManagement {

	private static final String MAIN_PATH = "customerservice/customers/";
	private static final String SERVICE_URL = "http://localhost:9000/";

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
		return webClient().path(MAIN_PATH + id).accept("application/xml").get(Customer.class);
	}

	@Override
	public void updateAccaount(String id, BigDecimal balance) {
		webClient().path(MAIN_PATH + id + "/account/").put(balance);
	}

	private WebClient webClient() {
		return WebClient.create(SERVICE_URL);
	}

	@Override
	public void updateCustomer(Customer customer) {
		webClient().path(MAIN_PATH).put(customer);
	}

	@Override
	public void notify(Customer customer, String message) {
		webClient().path(MAIN_PATH + "notification/" + message).put(customer);
	}

}
