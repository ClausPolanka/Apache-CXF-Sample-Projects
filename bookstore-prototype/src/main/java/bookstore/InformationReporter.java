package bookstore;

public interface InformationReporter {

	void notifyShippingEvent(Item[] items, Address anAddress);

	void notifyNewOrderRequest(Order anOrder);

	void notifyGetCustomerRequest(String customerId, Customer aCustomer);

}
