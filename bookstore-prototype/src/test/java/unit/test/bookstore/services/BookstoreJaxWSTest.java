package test.bookstore.services;

import static test.endtoend.bookstore.builder.ItemBuilder.anItem;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bookstore.CustomerManagement;
import bookstore.Item;
import bookstore.Order;
import bookstore.ProductAvailability;
import bookstore.Warehouse;
import bookstore.services.BookstoreJaxWS;

public class BookstoreJaxWSTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock
	private CustomerManagement customerService;

	@Mock
	private Warehouse warehouse;

	private BookstoreJaxWS bookstoreService;

	@Before
	public void createBookStoreService() {
		bookstoreService = new BookstoreJaxWS(customerService, warehouse);
	}

	@Test
	public void orderExactlyOneProductFormWarehouse() {
		final Item anItem = anItem().build();
		final Order anOrder = anOrder().withItem(anItem).build();

		//@formatter:off
		context.checking(new Expectations() {{
			ignoring(customerService);

			oneOf(warehouse).checkAvailability(anItem.getProduct(), anItem.getQuantity());
			will(returnValue(new ProductAvailability(true, 1)));

			oneOf(warehouse).order(anItem.getProduct(), anItem.getQuantity());
        }});
		//@formatter:on

		bookstoreService.requestOrder(anOrder);
	}

	@Test
	public void orderExactlyTwoProductsFormWarehouse() {
		final Item anItem1 = anItem().build();
		final Item anItem2 = anItem().build();
		final Order anOrder = anOrder().withItem(anItem1).withItem(anItem2).build();

		//@formatter:off
		context.checking(new Expectations() {{
			ignoring(customerService);

			oneOf(warehouse).checkAvailability(anItem1.getProduct(), anItem1.getQuantity());
			will(returnValue(new ProductAvailability(true, 1)));

			oneOf(warehouse).order(anItem1.getProduct(), anItem1.getQuantity());

			oneOf(warehouse).checkAvailability(anItem2.getProduct(), anItem2.getQuantity());
			will(returnValue(new ProductAvailability(true, 1)));

			oneOf(warehouse).order(anItem2.getProduct(), anItem2.getQuantity());
		}});
		//@formatter:on

		bookstoreService.requestOrder(anOrder);
	}
}
