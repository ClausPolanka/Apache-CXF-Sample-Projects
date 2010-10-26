package test.endtoend.bookstore.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import bookstore.Item;
import bookstore.Product;

public class ProductBuilder {

	private String id = "productId";
	private String name = "product";
	private BigDecimal singleUnitPrice = new BigDecimal(1);
	private List<Item> items = new ArrayList<Item>();

	public static ProductBuilder aProduct() {
		return new ProductBuilder();
	}

	public ProductBuilder forItem(Item item) {
		items.add(item);
		return this;
	}

	public Product build() {
		Product product = new Product(id, name, singleUnitPrice);
		for (Item each : items) {
			product.addItem(each);
		}
		return product;
	}

}
