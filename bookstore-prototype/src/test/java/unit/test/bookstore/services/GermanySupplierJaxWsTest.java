package test.bookstore.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ProductBuilder.aProductProvidedByGermanSupplier;
import static test.endtoend.bookstore.builder.ProductBuilder.aProductProvidedByGermanySupplierButNotAvailable;

import java.math.BigDecimal;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.UnknownProductFault;
import bookstore.services.GermanySupplierJaxWs;

public class GermanySupplierJaxWsTest {
	private static final int AMOUNT_OF_ONE = 1;
	private static final int AMOUNT_OF_TWO = 2;

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();
	@Mock
	private BookstoreLibrary library;

	private GermanySupplierJaxWs supplier;

	@Before
	public void createGermanySupplier() {
		supplier = new GermanySupplierJaxWs(library);
	}

	@Test(expected = UnknownProductFault.class)
	public void reportErrorIfProductIsNotAvailableAnymore() {
		final Product aProduct = aProductProvidedByGermanySupplierButNotAvailable();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(library).isAvailableInGermany(aProduct, AMOUNT_OF_ONE); will(returnValue(false));
		}});
		//@formatter:on

		supplier.order(aProduct, AMOUNT_OF_ONE);
	}

	@Test
	public void calculateTotalPriceOfOneOrderedProduct() {
		final Product aProduct = aProductProvidedByGermanSupplier();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(library).isAvailableInGermany(aProduct, AMOUNT_OF_ONE); will(returnValue(true));
			oneOf(library).getFromGermanSupplier(aProduct.getId());
		}});
		//@formatter:on

		BigDecimal totalPrice = supplier.order(aProduct, AMOUNT_OF_ONE);
		assertThat("Total price of prodcuct", totalPrice, is(equalTo(new BigDecimal(3))));
	}

	@Test
	public void calculateTotalPriceOfTwpOrderedProduct() {
		final Product aProduct = aProductProvidedByGermanSupplier();

		//@formatter:off
		context.checking(new Expectations() {{
			oneOf(library).isAvailableInGermany(aProduct, AMOUNT_OF_TWO); will(returnValue(true));

			oneOf(library).getFromGermanSupplier(aProduct.getId());
			oneOf(library).getFromGermanSupplier(aProduct.getId());
		}});
		//@formatter:on

		BigDecimal totalPrice = supplier.order(aProduct, AMOUNT_OF_TWO);
		assertThat("Total price of prodcuct", totalPrice, is(equalTo(new BigDecimal(6))));
	}
}
