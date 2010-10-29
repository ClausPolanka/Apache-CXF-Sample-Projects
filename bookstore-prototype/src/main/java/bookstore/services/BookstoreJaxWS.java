package bookstore.services;

import java.math.BigDecimal;

import bookstore.Bookstore;
import bookstore.Customer;
import bookstore.CustomerManagement;
import bookstore.Item;
import bookstore.Order;
import bookstore.ProductAvailability;
import bookstore.ShippingService;
import bookstore.Supplier;
import bookstore.Warehouse;

public class BookstoreJaxWS implements Bookstore {
	private Order order;
	private Customer aCustomer;
	private BigDecimal totalPrice = new BigDecimal(0);

	private Warehouse warehouse;
	private CustomerManagement customerService;
	private ShippingService shippingService;
	private Supplier supplier;

	public BookstoreJaxWS(CustomerManagement customerService, Warehouse warehouse, ShippingService shippingService, Supplier supplier) {
		this.customerService = customerService;
		this.warehouse = warehouse;
		this.shippingService = shippingService;
		this.supplier = supplier;
	}

	@Override
	public void requestOrder(Order anOrder) {
		this.order = anOrder;
		aCustomer = customerService.getCustomer(anOrder.getCustomerId());
		for (Item each : anOrder.getItems()) {
			order(each);
		}
		shippingService.shipItems(itemsToArry(anOrder), aCustomer.getShippingAddress());
		customerService.updateAccount(aCustomer.getId(), newCustomerBalance());
		customerService.notify(aCustomer, "message");
	}

	private void order(Item anItem) {
		if (availableInWarehouse(anItem)) {
			addToTotalPriceOf(orderFromWarehouse(anItem));
		} else {
			addToTotalPriceOf(orderFromSupplier(anItem));
		}
	}

	private BigDecimal orderFromWarehouse(Item anItem) {
		return warehouse.order(anItem.getProduct(), anItem.getQuantity());
	}

	private void addToTotalPriceOf(BigDecimal price) {
		totalPrice = totalPrice.add(price);
	}

	private BigDecimal orderFromSupplier(Item anItem) {
		return supplier.order(anItem.getProduct(), anItem.getQuantity());
	}

	private boolean availableInWarehouse(Item anItem) {
		ProductAvailability item = warehouse.checkAvailability(anItem.getProduct(), anItem.getQuantity());
		return item.isAvailable();
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
