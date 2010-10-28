package bookstore;

import java.math.BigDecimal;

import javax.jws.WebMethod;
import javax.jws.WebService;

//@formatter:off
@WebService(serviceName = "SupplierService",
			portName = "SupplierPT",
			targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/supplier")
public interface Supplier {
//@formatter:on

	/** @return total price. */
	@WebMethod(operationName = "order")
	BigDecimal order(Product product, int amount);
	// TODO throws UnknownProductFault

}
