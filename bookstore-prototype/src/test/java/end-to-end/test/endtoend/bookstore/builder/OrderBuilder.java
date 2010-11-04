package test.endtoend.bookstore.builder;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomer;
import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomerWithAddressesAndOpenBalanceOfFive;
import static test.endtoend.bookstore.builder.ItemBuilder.anItemOfOneProduct;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;
import static test.endtoend.bookstore.builder.ProductBuilder.aProductProvidedByAustriaSupplier;
import static test.endtoend.bookstore.builder.ProductBuilder.aProductProvidedByGermanSupplier;

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

	public static Order anOrderWithOneItem() {
		//@formatter:off
		return anOrder()
				.fromCustomer(aCustomerWithAddressesAndOpenBalanceOfFive())
				.withItem(anItemOfOneProduct(aProduct().build())).build();
		//@formatter:on
	}

	public static Order anOrderOfAProductProvidedByAustriaSupplier() {
		//@formatter:off
		return anOrder()
				.fromCustomer(aCustomerWithAddressesAndOpenBalanceOfFive())
				.withItem(anItemOfOneProduct(aProductProvidedByAustriaSupplier()))
				.build();
		//@formatter:on
	}

	public static Order anOrderOfAProductProvideByGermanSupplier() {
		//@formatter:off
		return anOrder()
				.fromCustomer(aCustomerWithAddressesAndOpenBalanceOfFive())
				.withItem(anItemOfOneProduct(aProductProvidedByGermanSupplier()))
				.build();
		//@formatter:on
	}

	public static Order anOrderOfAProductNotavailAbleInWarehouse(Customer aCustomer) {
		//@formatter:off
		return anOrder()
		.fromCustomer(aCustomer)
		.withItem(anItemOfOneProduct(aProductProvidedByAustriaSupplier()))
		.build();
		//@formatter:on
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
