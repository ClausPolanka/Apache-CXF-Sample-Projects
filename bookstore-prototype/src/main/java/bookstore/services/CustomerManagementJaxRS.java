package bookstore.services;

import static bookstore.services.CustomerDatabase.database;

import java.math.BigDecimal;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import bookstore.Customer;
import bookstore.CustomerManagement;

@Path("/customerservice/")
public class CustomerManagementJaxRS implements CustomerManagement {

	@POST
	@Path("/customers/")
	@Override
	public Customer createCustomer(Customer customer) {
		database.addCustomer(customer);
		return customer;
	}

	@DELETE
	@Path("/customers/{id}/")
	@Override
	public void deleteCustomer(String id) {
		database.deleteCustomer(id);
	}

	@GET
	@Path("/customers/{id}/")
	@Override
	public Customer getCustomer(@PathParam("id") String id) {
		return database.getCustomer(id);
	}

	@PUT
	@Path("/customers/")
	@Override
	public void updateCustomer(Customer customer) {
		database.updateCustomer(customer);
	}

	@PUT
	@Path("/customers/{id}/account")
	@Override
	public void updateAccaount(@PathParam("id") String id, BigDecimal balance) {
		database.updateAccount(database.getCustomer(id), balance);
	}

	@PUT
	@Path("/customers/notification/{message}")
	@Override
	public void notify(Customer customer, @PathParam("message") String message) {
		Customer c = database.getCustomer(customer.getId());
		c.notify(message);
	}
}
