package bookstore.services;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import bookstore.Customer;
import bookstore.CustomerManagement;

@Path("/customerservice/")
public class CustomerManagementJaxRS implements CustomerManagement {

	private Map<String, Customer> customers = new HashMap<String, Customer>();

	public CustomerManagementJaxRS() {
		createTestCustomers();
	}

	private void createTestCustomers() {
		Customer customer = new Customer("customerId");
		customer.setName("customer");
		customers.put(customer.getId(), customer);
	}

	@POST
	@Path("/customers/")
	@Override
	public Customer createCustomer(Customer customer) {
		customers.put(customer.getId(), customer);
		return customer;
	}

	@DELETE
	@Path("/customers/{id}/")
	@Override
	public void deleteCustomer(String id) {
		Customer c = customers.get(id);
		Response r;
		if (c != null) {
			r = Response.ok().build();
			customers.remove(id);
		} else {
			r = Response.notModified().build();
		}
	}

	@GET
	@Path("/customers/{id}/")
	@Override
	public Customer getCustomer(@PathParam("id") String id) {
		return customers.get(id);
	}

	@PUT
	@Path("/customers/")
	@Override
	public void updateCustomer(Customer customer) {
		Customer c = customers.get(customer.getId());
		Response r;
		if (c != null) {
			customers.put(customer.getId(), customer);
			r = Response.ok().build();
		} else {
			r = Response.notModified().build();
		}
	}

}
