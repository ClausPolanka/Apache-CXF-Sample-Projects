package bookstore.services;

import java.math.BigDecimal;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.Supplier;
import bookstore.UnknownProductFault;

public class GermanySupplierJaxWs implements Supplier {

	private BookstoreLibrary library;

	public GermanySupplierJaxWs(BookstoreLibrary library) {
		this.library = library;
	}

	@Override
	public BigDecimal order(Product aProduct, int amount) {
		if (!library.isAvailableInGermany(aProduct, amount)) {
			throw new UnknownProductFault("Product not available");
		}
		for (int i = 0; i < amount; i++) {
			library.getFromGermanSupplier(aProduct.getId());
		}
		return aProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));
	}

}
