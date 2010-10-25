package bookstore;

public interface Warehouse {

	ProductAvailability checkAvailability(Product product, int quantity);

}
