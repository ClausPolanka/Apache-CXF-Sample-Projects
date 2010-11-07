package test.bookstore.services;

import static test.endtoend.bookstore.builder.AddressBuilder.anAddressAtUniversity;
import static test.endtoend.bookstore.builder.ItemBuilder.anItemOfOneProduct;
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
import bookstore.InformationReporter;
import bookstore.Item;
import bookstore.SystemOutLogger;

public class SystemOutLoggerTest {

	private static final String PRODUCT_NAME = "War and Peace";
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
	public void notifyShippingEventOfItems() {
		final Address anAddress = anAddressAtUniversity();
		final Item anItem = anItemOfOneProduct(aProduct().ofName(PRODUCT_NAME).build());
		final Item[] items = new Item[] { anItem };

		// @formatter:off
		context.checking(new Expectations() {{
			oneOf(logger).info("[ShippingService] " + with(any(String.class)) + "\n      " +
					           "[ShippingService] Sending item \"" + PRODUCT_NAME + "\" to\n      " +
					           "[ShippingService] " + anAddress + "\n");
		}});
		// @formatter:on

		reporter.notifyShippingEvent(items, anAddress);
	}
}
