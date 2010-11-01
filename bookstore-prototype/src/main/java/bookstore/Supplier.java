package bookstore;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Supplier {

	/** @return total price. */
	@WebMethod(operationName = "order")
	BigDecimal order(Product product, int amount);
	// TODO throws UnknownProductFault

}
