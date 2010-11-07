package bookstore;

import java.math.BigDecimal;

public interface InformationReporter {

	void notifyShippingEvent(Item[] items, Address anAddress);

	void notifyNewOrderRequest(Order anOrder);

	void notifyGetCustomerRequest(String customerId, Customer aCustomer);

	void notifyOrderProcessingOf(Product aProduct, int amount, BigDecimal totalPrice);

}
