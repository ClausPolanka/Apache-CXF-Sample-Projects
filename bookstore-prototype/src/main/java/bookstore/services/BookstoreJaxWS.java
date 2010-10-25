package bookstore.services;

import bookstore.Bookstore;
import bookstore.Customer;
import bookstore.CustomerManagement;
import bookstore.Item;
import bookstore.Order;
import bookstore.ProductAvailability;
import bookstore.Warehouse;

public class BookstoreJaxWS implements Bookstore {

	private Order order;
	private Customer customer;
	private CustomerManagement customerService;
	private Warehouse warehouse;
	private ProductAvailability availability;

	public BookstoreJaxWS(CustomerManagement customerService, Warehouse warehouse) {
		this.customerService = customerService;
		this.warehouse = warehouse;
	}

	@Override
	public void requestOrder(Order order) {
		this.order = order;
		customer = customerService.getCustomer(order.getCustomerId());
		for (Item each : order.getItems()) {
			availability = warehouse.checkAvailability(each.getProduct(), each.getQuantity());
		}
	}

	public Order getOrder() {
		return order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public boolean isProductAvailableInWarehouse() {
		return availability.isAvailable();
	}

}
