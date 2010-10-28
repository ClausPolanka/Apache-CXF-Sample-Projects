package bookstore;

import javax.jws.WebMethod;
import javax.jws.WebService;

// @formatter:off
@WebService(serviceName = "ShippingService",
			portName = "ShippingPT",
			targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/shipping")
public interface ShippingService {
// @formatter:on

	@WebMethod(operationName = "ship_items")
	String shipItems(Item[] items, String customerShippingAddress);
	// TODO throws UnknownAddressFault, UnknownProductFault

}
