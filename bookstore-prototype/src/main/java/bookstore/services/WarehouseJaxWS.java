package bookstore.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import bookstore.Item;
import bookstore.Product;
import bookstore.ProductAvailability;
import bookstore.ShippingService;
import bookstore.Warehouse;

public class WarehouseJaxWS implements Warehouse {

	private Map<String, Product> products = new HashMap<String, Product>();
	private ShippingService shippingService;

	public WarehouseJaxWS() {
		createTestProducts();
	}

	public WarehouseJaxWS(ShippingService shippingService) {
		this.shippingService = shippingService;
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

	@Override
	public BigDecimal order(Product product, int quantity) {
		shippingService.shipItems(product.getItems().toArray(new Item[] {}), "address");
		return new BigDecimal(quantity).multiply(product.getSingleUnitPrice());
	}
}
