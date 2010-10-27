package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomerWithAddressesAndOpenBalanceOfFive;
import static test.endtoend.bookstore.builder.ItemBuilder.anItem;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import java.math.BigDecimal;

import org.junit.Test;

import bookstore.BookstoreRepository;
import bookstore.Item;
import bookstore.Order;

public class BookstoreEndToEndTest {

	private static final String MESSAGE = "message";
	private static final BigDecimal NEW_BALANCE = new BigDecimal(4);

	private BookstoreRepository repository = new TestRepository();
	private final FakeBookStoreServer bookstoreServer = new FakeBookStoreServer(repository);
	private final ApplicationClient customer = new ApplicationClient(repository);

	@Test
	public void customerOrdersOneProductWhichIsAvailableInWarehouse() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderWithOneItem());
		bookstoreServer.hasReceivedNewOrderRequest();
		// bookstoreServer.ordersItem();
		customer.hasReceivedUpdateForOpenBalance(NEW_BALANCE);
		customer.hasReceivedNotiyfication(MESSAGE);
	}

	private Order anOrderWithOneItem() {
		//@formatter:off
		return anOrder()
				.fromCustomer(aCustomerWithAddressesAndOpenBalanceOfFive())
				.withItem(anItemOfOneProduct()).build();
		//@formatter:on
	}

	private Item anItemOfOneProduct() {
		//@formatter:off
		return anItem()
				.ofQuantity(1)
				.ofProduct(aProduct().build()).build();
		//@formatter:on
	}

}
