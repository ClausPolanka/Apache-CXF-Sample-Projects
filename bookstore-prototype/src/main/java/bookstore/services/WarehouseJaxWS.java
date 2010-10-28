package bookstore.services;

import java.math.BigDecimal;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.ProductAvailability;
import bookstore.Warehouse;

public class WarehouseJaxWS implements Warehouse {
	private static final boolean AVAILABLE = true;
	private static final boolean NOT_AVAILABLE = false;

	private BookstoreLibrary library;

	public WarehouseJaxWS(BookstoreLibrary library) {
		this.library = library;
	}

	@Override
	public ProductAvailability checkAvailability(Product product, int amount) {
		if (countOf(product) < amount) {
			return new ProductAvailability(NOT_AVAILABLE, 0);
		}
		return new ProductAvailability(AVAILABLE, 1);
	}

	private int countOf(Product product) {
		return library.countProducts(product.getId());
	}

	@Override
	public BigDecimal order(Product product, int amount) {
		for (int i = 0; i < amount; i++) {
			library.deleteProduct(product);
		}
		return new BigDecimal(amount).multiply(product.getSingleUnitPrice());
	}
}
