package bookstore.services;

import java.math.BigDecimal;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.Supplier;

public class GermanSupplierJaxWs implements Supplier {

	private BookstoreLibrary library;

	public GermanSupplierJaxWs(BookstoreLibrary library) {
		this.library = library;
	}

	@Override
	public BigDecimal order(Product product, int amount) {
		for (int i = 0; i < amount; i++) {
			library.getFromGermanSupplier(product.getId());
		}
		return product.getSingleUnitPrice().multiply(new BigDecimal(amount));
	}

}
