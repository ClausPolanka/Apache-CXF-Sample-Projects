package bookstore.services;

import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

import bookstore.Customer;
import bookstore.CustomerManagement;
import bookstore.NotificationMessage;

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

	// @formatter:off
	@Override
	public Customer getCustomer(String id) {
		return webClient()
				.type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.path(MAIN_PATH + id)
				.get(Customer.class);
	}

	private WebClient webClient() {
		return WebClient.create(SERVICE_URL);
	}

	@Override
	public void updateAccount(String id, BigDecimal balance) {
		webClient()
			.type(MediaType.APPLICATION_JSON)
			.path(MAIN_PATH + id + "/account/")
			.put(balance);
	}

	@Override
	public void updateCustomer(Customer customer) {
		webClient()
			.type(MediaType.APPLICATION_JSON)
			.path(MAIN_PATH)
			.put(customer);
	}

	@Override
	public void notify(String customerId, NotificationMessage message) {
		webClient()
			.type(MediaType.APPLICATION_JSON)
			.path(MAIN_PATH + customerId + "/notification")
			.put(message);
	}
	// @formatter:on
}
