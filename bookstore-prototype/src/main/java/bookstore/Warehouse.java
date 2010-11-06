package bookstore;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebService;

//@formatter:off
@WebService(serviceName = "WarehouseService",
			portName = "WarehousePT",
			targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/warehouse")
public interface Warehouse {
//@formatter:on

	/**
	 * @return ProductAvailability has additional information about the
	 *         product's estimated delivery time.
	 */
	@WebMethod(operationName = "check_availability")
	ProductAvailability checkAvailability(Product product, int amaount);

	/** @return total price. */
	@WebMethod(operationName = "order")
	BigDecimal order(Product product, int amount);

}
