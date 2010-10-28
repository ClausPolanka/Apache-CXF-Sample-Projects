package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.OrderBuilder.anOrderOfAProductNotavailAbleInWarehouse;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrderWithOneItem;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;

import bookstore.BookstoreLibrary;

public class BookstoreEndToEndTest {

	private static final BigDecimal NEW_BALANCE_OF_3 = new BigDecimal(3);
	private static final BigDecimal NEW_BALANCE_OF_2 = new BigDecimal(4);
	private static final String MESSAGE = "message";

	private BookstoreLibrary repository = new TestRepository();
	private final FakeBookStoreServer bookstoreServer = new FakeBookStoreServer(repository);
	private final ApplicationClient customer = new ApplicationClient(repository);

	@Test
	public void customerOrdersOneProductWhichIsAvailableInWarehouse() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderWithOneItem());
		bookstoreServer.hasReceivedNewOrderRequest();
		// bookstoreServer.ordersItem();
		customer.hasReceivedUpdateForOpenBalance(NEW_BALANCE_OF_2);
		customer.hasReceivedNotiyfication(MESSAGE);
	}

	@Test
	public void customerOrdersOneProductWhichWillBeProvidedBySupplierOneBecauseItIsNotAvailableInWarehouse() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderOfAProductNotavailAbleInWarehouse());
		bookstoreServer.hasReceivedNewOrderRequest();
		// bookstoreServer.ordersItem();
		customer.hasReceivedUpdateForOpenBalance(NEW_BALANCE_OF_3);
		customer.hasReceivedNotiyfication(MESSAGE);
	}

	@After
	public void stopSelling() {
		bookstoreServer.stopSellingProducts();
	}

}
