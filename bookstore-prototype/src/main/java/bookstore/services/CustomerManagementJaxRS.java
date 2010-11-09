package bookstore.services;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bookstore.BookstoreLibrary;
import bookstore.Customer;
import bookstore.CustomerManagement;
import bookstore.InformationReporter;
import bookstore.NotificationMessage;

@Path("/customerservice/")
public class CustomerManagementJaxRS implements CustomerManagement {

	private BookstoreLibrary database;
	private InformationReporter reporter;

	public CustomerManagementJaxRS(BookstoreLibrary database, InformationReporter reporter) {
		this.database = database;
		this.reporter = reporter;
	}

	@POST
	@Path("/customers/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Customer createCustomer(Customer customer) {
		database.addCustomer(customer);
		return customer;
	}

	@DELETE
	@Path("/customers/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void deleteCustomer(String id) {
		database.deleteCustomer(id);
	}

	@GET
	@Path("/customers/{id}/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Customer getCustomer(@PathParam("id") String id) {
		Customer customer = database.getCustomer(id);
		reporter.notifyGetCustomerRequest(id, customer);
		return customer;
	}

	@PUT
	@Path("/customers/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void updateCustomer(Customer customer) {
		database.updateCustomer(customer);
	}

	@PUT
	@Path("/customers/{id}/account")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void updateAccount(@PathParam("id") String id, BigDecimal balance) {
		Customer customer = database.getCustomer(id);
		reporter.notifyUpdateOfCustomersAccount(customer, balance);
		database.updateAccount(customer, balance);
	}

	@PUT
	@Path("/customers/{id}/notification")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void notify(@PathParam("id") String id, NotificationMessage message) {
		Customer customer = database.getCustomer(id);
		reporter.notifyThatCustomerReceivesANotificationMessage(customer, message.getMessage());
		customer.notify(message.getMessage());
	}
}
