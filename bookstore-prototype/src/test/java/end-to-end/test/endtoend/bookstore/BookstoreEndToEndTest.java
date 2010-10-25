package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.AddressBuilder.anAddress;
import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomer;
import static test.endtoend.bookstore.builder.ItemBuilder.anItem;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import org.junit.Test;

import bookstore.Address;
import bookstore.Order;
import bookstore.services.BookstoreJaxWS;
import bookstore.services.CustomerManagementJaxWS;
import bookstore.services.WarehouseJaxWS;

public class BookstoreEndToEndTest {

	private final CustomerManagementJaxWS customerService = new CustomerManagementJaxWS();
	private final WarehouseJaxWS warehouse = new WarehouseJaxWS();

	//@formatter:off
	private final FakeBookStoreServer bookstoreServer = new FakeBookStoreServer(
		new BookstoreJaxWS(customerService, warehouse),
		customerService
	);
	//@formatter:on

	private final ApplicationClient customer = new ApplicationClient();

	@Test
	public void customerOrdersOneProductWhichIsAvailableInWarehouse() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderWithOneItem());
		bookstoreServer.hasReceivedNewOrderRequest();
		bookstoreServer.queriesCustomer();
		bookstoreServer.hasReceivedAvailabilityInformationOfProduct();
	}

	private Order anOrderWithOneItem() {
		//@formatter:off
		return anOrder().fromCustomer(aCustomer().build())
						.withItem(anItem()
									.ofQuantity(1)
									.ofProduct(aProduct().build()).build()).build();
		//@formatter:on
	}

	private Address aShippingAddress() {
		return anAddress().shippable().build();
	}

	private Address aBillingAddress() {
		return anAddress().asBillingAddress().build();
	}

}
