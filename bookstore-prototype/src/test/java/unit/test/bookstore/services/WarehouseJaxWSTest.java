package test.bookstore.services;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import test.endtoend.bookstore.builder.ItemBuilder;
import test.endtoend.bookstore.builder.ProductBuilder;
import bookstore.Item;
import bookstore.Product;
import bookstore.ShippingService;
import bookstore.Warehouse;
import bookstore.services.WarehouseJaxWS;

public class WarehouseJaxWSTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock
	private ShippingService shippingService;

	private Warehouse warehouseService;

	@Before
	public void createWarehouseService() {
		warehouseService = new WarehouseJaxWS(shippingService);
	}

	@Test
	public void orderExactlyOneProductAndShipIt() {
		final Item anItem = new ItemBuilder().build();
		final Product aProduct = new ProductBuilder().forItem(anItem).build();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(shippingService).shipItems(new Item[] {anItem}, "address");
		}});
		// @formatter:on

		warehouseService.order(aProduct, 1);
	}
}
