package bookstore;

public class Item {
	private int quantity;
	private Order order;
	private Product product;

	@SuppressWarnings("unused")
	private Item() {
		// Needed by Apache CXF.
	}

	public Item(int quantity, Order order, Product product) {
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
