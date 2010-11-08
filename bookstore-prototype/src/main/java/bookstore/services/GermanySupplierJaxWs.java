package bookstore.services;

import java.math.BigDecimal;

import bookstore.BookstoreLibrary;
import bookstore.InformationReporter;
import bookstore.Product;
import bookstore.Supplier;
import bookstore.UnknownProductFault;

public class GermanySupplierJaxWs implements Supplier {

	private BookstoreLibrary library;
	private InformationReporter reporter;

	public GermanySupplierJaxWs(BookstoreLibrary library, InformationReporter reporter) {
		this.library = library;
		this.reporter = reporter;
	}

	@Override
	public BigDecimal order(Product aProduct, int amount) {
		if (!library.isAvailableInGermany(aProduct, amount)) {
			throw new UnknownProductFault("Product not available");
		}
		for (int i = 0; i < amount; i++) {
			library.getFromGermanSupplier(aProduct.getId());
		}
		BigDecimal totalPrice = aProduct.getSingleUnitPrice().multiply(new BigDecimal(amount));
		reporter.notifyOrderRequestFromGermanSupplier(aProduct, amount, totalPrice);
		return totalPrice;
	}

}
