package test.endtoend.bookstore.builder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import bookstore.Item;
import bookstore.Product;

public class ProductBuilder {

	private static final String PROVIDED_BY_AUSTRIA_SUPPLIER = "xyz";
	private static final String PROVIDED_BY_GERMAN_SUPPLIER = "abc";
	private String id = "productId";
	private String name = "product";
	private BigDecimal singleUnitPrice = new BigDecimal(1);
	private List<Item> items = new ArrayList<Item>();

	public static ProductBuilder aProduct() {
		return new ProductBuilder();
	}

	public static Product aProductProvidedByAustriaSupplier() {
		// @formatter:off
		return aProduct()
			   .withProductId(PROVIDED_BY_AUSTRIA_SUPPLIER)
			   .withSingleUnitPrice(new BigDecimal(2)).build();
		// @formatter:on
	}

	public static Product aProductProvidedByGermanSupplier() {
		// @formatter:off
		return aProduct()
				.withProductId(PROVIDED_BY_GERMAN_SUPPLIER)
				.withSingleUnitPrice(new BigDecimal(3)).build();
		// @formatter:on
	}

	public ProductBuilder withProductId(String id) {
		this.id = id;
		return this;
	}

	public ProductBuilder withSingleUnitPrice(BigDecimal singleUnitPrice) {
		this.singleUnitPrice = singleUnitPrice;
		return this;
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
