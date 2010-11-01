package test.bookstore.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.SupplierRegistry;
import bookstore.services.SupplierRegistryJaxWs;

public class SupplierRegistryJaxWsTest {
	private static final String PRODUCT_ID = "xyz";
	private static final String EMPTY_ADDRESS = "";
	private static final String ADDRESS = "http://localhist:9000/supplieraustria";
	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	@Mock
	private BookstoreLibrary library;

	private SupplierRegistry registry;

	@Before
	public void createSupplier() {
		registry = new SupplierRegistryJaxWs(library);
	}

	@Test
	public void provideAddressOfSupplierForAGivenProduct() {
		final Product aProduct = aProduct().withProductId(PRODUCT_ID).build();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(library).getSupplierAddressFor(PRODUCT_ID); will(returnValue(ADDRESS));
		}});
		//@formatter:on

		EndpointReferenceType endpoint = registry.getAddressFromSupplierFor(aProduct);
		assertThat("Supplier's address", endpoint.getAddress().getValue(), is(equalTo(ADDRESS)));
	}

	@Test(expected = RuntimeException.class)
	public void reportErrorBecauseGivenProductIsUnknown() {
		final Product aProduct = aProduct().withProductId(PRODUCT_ID).build();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(library).getSupplierAddressFor(PRODUCT_ID); will(returnValue(EMPTY_ADDRESS));
		}});
		//@formatter:on

		registry.getAddressFromSupplierFor(aProduct);
	}
}
