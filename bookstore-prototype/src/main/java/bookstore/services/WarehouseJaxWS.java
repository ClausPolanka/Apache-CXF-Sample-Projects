package bookstore.services;

import java.math.BigDecimal;

import bookstore.Product;
import bookstore.ProductAvailability;
import bookstore.Warehouse;

public class WarehouseJaxWS implements Warehouse {

	// private Map<String, Product> products = new HashMap<String, Product>();

	public WarehouseJaxWS() {
		// createTestProducts();
	}

	private void createTestProducts() {
		Product product = new Product("productId", "product", new BigDecimal(1));
		// products.put(product.getId(), product);
	}

	@Override
	public ProductAvailability checkAvailability(Product product, int amount) {
		// Product found = products.get(product.getId());
		return new ProductAvailability(true, 1);
	}

	@Override
	public BigDecimal order(Product product, int amount) {
		// products.remove(product.getId());
		return new BigDecimal(amount).multiply(product.getSingleUnitPrice());
	}
}
