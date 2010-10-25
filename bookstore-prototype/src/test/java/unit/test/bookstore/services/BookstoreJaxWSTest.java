package test.bookstore.services;

import static test.endtoend.bookstore.builder.ItemBuilder.anItem;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import bookstore.CustomerManagement;
import bookstore.Item;
import bookstore.Order;
import bookstore.Warehouse;
import bookstore.services.BookstoreJaxWS;

public class BookstoreJaxWSTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock
	private CustomerManagement customerService;

	@Mock
	private Warehouse warehouse;

	@Test
	public void orderExactlyOneProductFormWarehouse() {
		BookstoreJaxWS bookstoreService = new BookstoreJaxWS(customerService, warehouse);
		final Item anItem = anItem().build();
		final Order anOrder = anOrder().withItem(anItem).build();

		//@formatter:off
		context.checking(new Expectations() {{
			ignoring(customerService);

			oneOf(warehouse).checkAvailability(anItem.getProduct(), anItem.getQuantity());
        }});
		//@formatter:on

		bookstoreService.requestOrder(anOrder);
	}

	@Test
	public void orderExactlyTwoProductsFormWarehouse() {
		BookstoreJaxWS bookstoreService = new BookstoreJaxWS(customerService, warehouse);
		final Item anItem1 = anItem().build();
		final Item anItem2 = anItem().build();
		final Order anOrder = anOrder().withItem(anItem1).withItem(anItem2).build();

		//@formatter:off
		context.checking(new Expectations() {{
			ignoring(customerService);

			oneOf(warehouse).checkAvailability(anItem1.getProduct(), anItem1.getQuantity());
			oneOf(warehouse).checkAvailability(anItem2.getProduct(), anItem2.getQuantity());
		}});
		//@formatter:on

		bookstoreService.requestOrder(anOrder);
	}
}
