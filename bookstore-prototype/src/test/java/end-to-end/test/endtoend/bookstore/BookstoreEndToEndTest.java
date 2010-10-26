package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.AddressBuilder.anAddress;
import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomer;
import static test.endtoend.bookstore.builder.ItemBuilder.anItem;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import org.junit.Test;

import bookstore.Address;
import bookstore.Item;
import bookstore.Order;

public class BookstoreEndToEndTest {

	private final FakeBookStoreServer bookstoreServer = new FakeBookStoreServer();
	private final ApplicationClient customer = new ApplicationClient();

	@Test
	public void customerOrdersOneProductWhichIsAvailableInWarehouse() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderWithOneItem());
		bookstoreServer.hasReceivedNewOrderRequest();
		bookstoreServer.queriesCustomer();
		bookstoreServer.hasReceivedAvailabilityInformationOfProductFromWarehouse();
	}

	private Order anOrderWithOneItem() {
		//@formatter:off
		return anOrder()
			       .fromCustomer(aCustomer().build())
				   .withItem(anItemOfOneProduct())
			   .build();
		//@formatter:on
	}

	private Item anItemOfOneProduct() {
		//@formatter:off
		return anItem()
				.ofQuantity(1)
				.ofProduct(aProduct().build())
				.build();
		//@formatter:on
	}

	private Address aShippingAddress() {
		return anAddress().shippable().build();
	}

	private Address aBillingAddress() {
		return anAddress().asBillingAddress().build();
	}

}
