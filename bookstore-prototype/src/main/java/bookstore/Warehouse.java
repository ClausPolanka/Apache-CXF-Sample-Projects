package bookstore;

import java.math.BigDecimal;

import javax.jws.WebService;

@WebService
public interface Warehouse {

	ProductAvailability checkAvailability(Product product, int quantity);

	BigDecimal order(Product product, int quantity);

}
