package test.bookstore.services;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.AddressBuilder.anAddress;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bookstore.Address;
import bookstore.BookstoreLibrary;
import bookstore.Item;
import bookstore.ShippingService;
import bookstore.UnknownAddressFault;
import bookstore.services.ShippingServiceJaxWs;

public class ShippingServiceJaxWsTest {
	private static final Item[] ANY_ITEMS = null;
	private static final Address CUSTOMER_SHIPPING_ADDRESS = anAddress().build();
	private static final Address ANY_ADDRESS = anAddress().build();
	private static final Address UNKNOWN_ADDRESS = anAddress().build();

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	@Mock
	private BookstoreLibrary library;

	private ShippingService shippingService;

	@Before
	public void createShippingService() {
		shippingService = new ShippingServiceJaxWs(library);
	}

	@Test
	public void generateUUIDForShippedItems() {
		// @formatter:off
		context.checking(new Expectations() {{
			oneOf(library).isValid(ANY_ADDRESS.getId()); will(returnValue(true));
		}});
		// @formatter:on

		String uuid = shippingService.shipItems(ANY_ITEMS, ANY_ADDRESS);

		assertThat("Generated UUID", uuid, any(String.class));
	}

	@Test
	public void shipItemsToCustomersGivenValidShippingAddress() {
		// @formatter:off
		context.checking(new Expectations() {{
			oneOf(library).isValid(CUSTOMER_SHIPPING_ADDRESS.getId()); will(returnValue(true));
		}});
		// @formatter:on

		shippingService.shipItems(ANY_ITEMS, CUSTOMER_SHIPPING_ADDRESS);
	}

	@Test(expected = UnknownAddressFault.class)
	public void reportErrorForUnknownAddress() {
		// @formatter:off
		context.checking(new Expectations() {{
			oneOf(library).isValid(UNKNOWN_ADDRESS.getId()); will(returnValue(false));
		}});
		// @formatter:on

		shippingService.shipItems(ANY_ITEMS, UNKNOWN_ADDRESS);
	}
}
