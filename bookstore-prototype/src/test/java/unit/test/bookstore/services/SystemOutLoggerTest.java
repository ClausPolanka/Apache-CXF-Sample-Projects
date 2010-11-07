package test.bookstore.services;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static test.endtoend.bookstore.builder.AddressBuilder.anAddressAtUniversity;
import static test.endtoend.bookstore.builder.CustomerBuilder.aCustomer;
import static test.endtoend.bookstore.builder.ItemBuilder.anItemOfOneProduct;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import java.util.logging.Logger;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bookstore.Address;
import bookstore.Customer;
import bookstore.InformationReporter;
import bookstore.Item;
import bookstore.Order;
import bookstore.services.SystemOutLogger;

public class SystemOutLoggerTest {
	private static final String CUSTOMER_NAME = "MiniMe";
	private static final String CUSTOMER_ID = "279 cedd6-ba3b-4a30-a63e-8e54f28f0037";
	private static final String PRODUCT_NAME_1 = "War and Peace";
	private static final String PRODUCT_NAME_2 = "Moby Dick";
	// @formatter:off
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery() {{
		setImposteriser(ClassImposteriser.INSTANCE);
	}};
	// @formatter:on

	@Mock
	private Logger logger;

	private InformationReporter reporter;

	@Before
	public void createInformationReporter() {
		reporter = new SystemOutLogger(logger);
	}

	@Test
	public void printNotificationAboutShippingOneItem() {
		final Address anAddress = anAddressAtUniversity();
		final Item anItem = anItemOfOneProduct(aProduct().ofName(PRODUCT_NAME_1).build());
		final Item[] items = new Item[] { anItem };

		// @formatter:off
		context.checking(new Expectations() {{
			oneOf(logger).info(with(allOf(containsString("[ShippingService] Sending item \"" + PRODUCT_NAME_1 + "\" to\n"),
										  containsString("[ShippingService] " + anAddress + "\n"))));
		}});
		// @formatter:on

		reporter.notifyShippingEvent(items, anAddress);
	}

	@Test
	public void printNotificationAboutShippingTwoItems() {
		final Address anAddress = anAddressAtUniversity();
		Item item1 = anItemOfOneProduct(aProduct().ofName(PRODUCT_NAME_1).build());
		Item item2 = anItemOfOneProduct(aProduct().ofName(PRODUCT_NAME_2).build());
		final Item[] items = new Item[] { item1, item2 };

		// @formatter:off
		context.checking(new Expectations() {{
			oneOf(logger).info(with(allOf(containsString("[ShippingService] Sending items \"" + PRODUCT_NAME_1 + "\", \"" + PRODUCT_NAME_2 + "\" to\n"),
										  containsString("[ShippingService] " + anAddress + "\n")
			)));
		}});
		// @formatter:on

		reporter.notifyShippingEvent(items, anAddress);
	}

	@Test
	public void printNotificationAboutNewOrderRequest() {
		// @formatter:off
		final Customer aCustomer = aCustomer()
									.withId(CUSTOMER_ID)
									.withName(CUSTOMER_NAME).build();
		final Order anOrder = anOrder().fromCustomer(aCustomer).build();

		context.checking(new Expectations() {{
			oneOf(logger).info(with(allOf(containsString("[Bookstore] Received new order request from customer:\n      "),
										  containsString("[Bookstore] " + aCustomer))));
		}});
		// @formatter:on

		reporter.notifyNewOrderRequest(anOrder);
	}

	@Test
	public void printNotificationAboutGetCustomerRequest() {
		// @formatter:off
		final Customer aCustomer = aCustomer()
									.withId(CUSTOMER_ID)
									.withName(CUSTOMER_NAME).build();

		context.checking(new Expectations() {{
			oneOf(logger).info(with(allOf(containsString("[CustomerManagement (Jax-RS)] Get customer for id: " + CUSTOMER_ID + "\n      "),
										  containsString("[CustomerManagement (Jax-RS)] Found customer: " + aCustomer))));
		}});
		// @formatter:on

		reporter.notifyGetCustomerRequest(CUSTOMER_ID, aCustomer);
	}
}
