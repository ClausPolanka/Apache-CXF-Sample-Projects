package bookstore;

import java.math.BigDecimal;

import javax.jws.WebService;

@WebService
public interface Warehouse {

	ProductAvailability checkAvailability(Product product, int amaount);

	/** @return total price. */
	BigDecimal order(Product product, int amount);

}
