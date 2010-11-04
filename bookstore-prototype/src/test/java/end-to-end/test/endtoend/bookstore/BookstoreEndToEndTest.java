package test.endtoend.bookstore;

import static test.endtoend.bookstore.builder.OrderBuilder.anOrderOfAProductProvideByGermanSupplier;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrderOfAProductProvidedByAustriaSupplier;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrderWithOneItem;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;

import bookstore.BookstoreLibrary;

public class BookstoreEndToEndTest {

	private static final BigDecimal NEW_BALANCE_OF_2 = new BigDecimal(2);
	private static final BigDecimal NEW_BALANCE_OF_3 = new BigDecimal(3);
	private static final BigDecimal NEW_BALANCE_OF_4 = new BigDecimal(4);
	private static final String MESSAGE = "message";

	private final BookstoreLibrary library = new FakeBookStoreLibrary();
	private final FakeBookStoreServer bookstoreServer = new FakeBookStoreServer(library);
	private final ApplicationClient customer = new ApplicationClient(library);

	// @formatter:off
	@Test public void
	customerWithBalanceOfFiveOrdersOneProductOfPriceOneWhichIsAvailableInWarehouse() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderWithOneItem());
		bookstoreServer.hasReceivedNewOrderRequest();
		customer.hasReceivedUpdateForOpenBalance(NEW_BALANCE_OF_4);
		customer.hasReceivedNotiyfication(MESSAGE);
	}

	@Test public void
	customerWithBalanceOfFiveOrdersOneProductOfPriceTwoWhichWillBeProvidedByAustriaSupplier() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderOfAProductProvidedByAustriaSupplier());
		bookstoreServer.hasReceivedNewOrderRequest();
		customer.hasReceivedUpdateForOpenBalance(NEW_BALANCE_OF_3);
		customer.hasReceivedNotiyfication(MESSAGE);
	}

	@Test public void
	customerWithBalanceOfFiveOrdersOneProductOfPriceThreeWhichWillBeProvidedByGermanSupplier() {
		bookstoreServer.startSellingProducts();
		customer.orders(anOrderOfAProductProvideByGermanSupplier());
		bookstoreServer.hasReceivedNewOrderRequest();
		customer.hasReceivedUpdateForOpenBalance(NEW_BALANCE_OF_2);
		customer.hasReceivedNotiyfication(MESSAGE);
	}
	// @formatter:on

	@After
	public void stopSelling() {
		bookstoreServer.stopSellingProducts();
	}

}
