package test.bookstore.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import java.math.BigDecimal;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bookstore.Product;
import bookstore.Supplier;
import bookstore.SupplierRegistry;
import bookstore.services.ServiceInvoker;
import bookstore.services.SupplierJaxWs;

public class SupplierJaxWSTest {

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	@Mock
	private SupplierRegistry registry;
	@Mock
	private ServiceInvoker invoker;

	private Supplier supplier;

	@Before
	public void createSupplier() {
		supplier = new SupplierJaxWs(registry, invoker);
	}

	@Test
	public void orderOneProductFromSupplierWhichWasReturnedFromRegistry() {
		final Product aProduct = aProduct().withProductId("xyz").withSingleUnitPrice(new BigDecimal(4)).build();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(registry).getAddressFromSupplierFor(aProduct); will(returnValue(new Object()));
			oneOf(invoker).invoke("address", Supplier.class); will(returnValue(dummySupplier()));
		}});

		//@formatter:on

		BigDecimal totalPrice = supplier.order(aProduct, 1);
		assertThat("Total price", totalPrice, is((equalTo(new BigDecimal(4)))));
	}

	private Supplier dummySupplier() {
		return new Supplier() {
			@Override
			public BigDecimal order(Product product, int amount) {
				return new BigDecimal(4);
			}
		};
	}
}
