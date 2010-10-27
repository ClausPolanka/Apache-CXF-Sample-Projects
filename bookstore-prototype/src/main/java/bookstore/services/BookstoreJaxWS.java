package bookstore.services;

import java.math.BigDecimal;

import bookstore.Bookstore;
import bookstore.Customer;
import bookstore.CustomerManagement;
import bookstore.Item;
import bookstore.Order;
import bookstore.ProductAvailability;
import bookstore.ShippingService;
import bookstore.Warehouse;

public class BookstoreJaxWS implements Bookstore {
	private Order order;
	private Customer aCustomer;
	private BigDecimal totalPrice;

	private Warehouse warehouse;
	private CustomerManagement customerService;
	private ProductAvailability itemInWarehouse;
	private ShippingService shippingService;

	public BookstoreJaxWS(CustomerManagement customerService, Warehouse warehouse, ShippingService shippingService) {
		this.customerService = customerService;
		this.warehouse = warehouse;
		this.shippingService = shippingService;
	}

	@Override
	public void requestOrder(Order anOrder) {
		this.order = anOrder;
		aCustomer = customerService.getCustomer(anOrder.getCustomerId());
		for (Item each : anOrder.getItems()) {
			order(each);
		}
		shippingService.shipItems(itemsToArry(anOrder), aCustomer.getShippingAddress());
		customerService.updateAccaount(aCustomer.getId(), newCustomerBalance());
		customerService.notify(aCustomer, "message");
	}

	private void order(Item anItem) {
		itemInWarehouse = warehouse.checkAvailability(anItem.getProduct(), anItem.getQuantity());
		if (itemInWarehouse.isAvailable()) {
			totalPrice = warehouse.order(anItem.getProduct(), anItem.getQuantity());
		} else {
			// TODO Order from Supplier.
		}
	}

	private Item[] itemsToArry(Order order) {
		return order.getItems().toArray(new Item[] {});
	}

	private BigDecimal newCustomerBalance() {
		return aCustomer.getOpenBalance().subtract(totalPrice);
	}

	public Order getOrder() {
		return order;
	}

}
