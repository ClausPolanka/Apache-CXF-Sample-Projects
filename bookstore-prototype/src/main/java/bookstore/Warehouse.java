package bookstore;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

//@formatter:off
@WebService(targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse")
public interface Warehouse {
//@formatter:on

	/**
	 * @return ProductAvailability has additional information about the
	 *         product's estimated delivery time.
	 */
	@WebMethod(operationName = "check_availability")
	ProductAvailability checkAvailability(@WebParam(name = "product") Product product, @WebParam(name = "amount") int amaount);

	/** @return total price. */
	@WebMethod(operationName = "order")
	BigDecimal order(@WebParam(name = "product") Product product, @WebParam(name = "amount") int amount);

}
