package bookstore.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import bookstore.Product;
import bookstore.ProductAvailability;
import bookstore.Warehouse;

public class WarehouseJaxWS implements Warehouse {

	private Map<String, Product> products = new HashMap<String, Product>();

	public WarehouseJaxWS() {
		createTestProducts();
	}

	private void createTestProducts() {
		Product product = new Product("productId", "product", new BigDecimal(1));
		products.put(product.getId(), product);
	}

	@Override
	public ProductAvailability checkAvailability(Product product, int quantity) {
		Product found = products.get(product.getId());
		return new ProductAvailability(true, 1);
	}

}
