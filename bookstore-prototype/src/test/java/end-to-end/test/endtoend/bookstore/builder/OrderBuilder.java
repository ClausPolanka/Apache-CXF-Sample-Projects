package test.endtoend.bookstore.builder;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bookstore.Customer;
import bookstore.Item;
import bookstore.Order;

public class OrderBuilder {
	private String id = "orderId";
	private Date orderDate = new Date();
	private Customer customer = aCustomer().build();
	private List<Item> items = new ArrayList<Item>();

	public static OrderBuilder anOrder() {
		return new OrderBuilder();
	}

	public OrderBuilder fromCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public OrderBuilder withItem(Item item) {
		items.add(item);
		return this;
	}

	public Order build() {
		Order order = new Order(id, orderDate, customer);
		for (Item each : items) {
			order.addItem(each);
		}
		return order;
	}
}
