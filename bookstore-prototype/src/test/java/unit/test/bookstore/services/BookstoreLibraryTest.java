package test.bookstore.services;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import test.endtoend.bookstore.FakeBookStoreLibrary;
import bookstore.BookstoreLibrary;
import bookstore.Product;

public class BookstoreLibraryTest {
	private static final String SUPPLIER_ADDRESS = "http://localhost:9000/austriasupplier";
	private static final String PRODUCT_ID = "productId";

	@Rule
	public JUnitRuleMockery context = new JUnitRuleMockery();

	private BookstoreLibrary library;

	@Before
	public void createBookstoreLibrary() {
		library = new FakeBookStoreLibrary();
	}

	@Test
	public void returnSupplierAddressForAGivenProductId() {
		Product aProduct = aProduct().withProductId(PRODUCT_ID).build();

		String address = library.getSupplierAddressFor(aProduct.getId());

		assertThat("Supplier's address", address, is(equalTo(SUPPLIER_ADDRESS)));
	}
}
