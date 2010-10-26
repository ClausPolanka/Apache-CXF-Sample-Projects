package bookstore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {
	private String id;
	private String name;
	private BigDecimal singleUnitPrice;
	private List<Item> items = new ArrayList<Item>();

	@SuppressWarnings("unused")
	private Product() {
		// Needed by Apache CXF.
	}

	public Product(String id, String name, BigDecimal singleUnitPrice) {
		this.id = id;
		this.name = name;
		this.singleUnitPrice = singleUnitPrice;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSingleUnitPrice() {
		return singleUnitPrice;
	}

	public void setSingleUnitPrice(BigDecimal singleUnitPrice) {
		this.singleUnitPrice = singleUnitPrice;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
