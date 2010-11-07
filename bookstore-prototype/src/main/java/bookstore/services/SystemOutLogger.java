package bookstore.services;

import java.util.Date;
import java.util.logging.Logger;

import bookstore.Address;
import bookstore.Customer;
import bookstore.InformationReporter;
import bookstore.Item;
import bookstore.Order;

public class SystemOutLogger implements InformationReporter {

	private static final String SEPARATOR = "      ";
	private static final String SHIPPING_SERVICE = "[ShippingService] ";
	private static final String BOOKSTORE = "[Bookstore] ";
	private static final String CUSTOMER_MANAGEMENT_JAXRS = "[CustomerManagement (Jax-RS)] ";
	private static final String NEW_LINE = "\n";
	private Logger logger;

	public SystemOutLogger(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void notifyShippingEvent(Item[] items, Address anAddress) {
		StringBuffer logEntry = buildShippingLogEntry(items, anAddress);
		logger.info(logEntry.toString());
	}

	private StringBuffer buildShippingLogEntry(Item[] items, Address anAddress) {
		StringBuffer logEntry = new StringBuffer();
		logEntry.append(SHIPPING_SERVICE);
		logEntry.append(new Date());
		logEntry.append(NEW_LINE + SEPARATOR);
		logEntry.append(SHIPPING_SERVICE);
		logEntry.append("Sending item" + (items.length > 1 ? "s " : " "));
		appendItems(logEntry, items);
		logEntry.append(NEW_LINE + SEPARATOR);
		logEntry.append(SHIPPING_SERVICE);
		logEntry.append(anAddress);
		logEntry.append(NEW_LINE);
		return logEntry;
	}

	private void appendItems(StringBuffer logEntry, Item[] items) {
		for (int i = 0; i < items.length; i++) {
			logEntry.append("\"" + items[i].getProductName() + "\"");
			logEntry.append(i == items.length - 1 ? "" : ", ");
		}
		logEntry.append(" to");
	}

	@Override
	public void notifyNewOrderRequest(Order anOrder) {
		StringBuffer logEntry = new StringBuffer();
		logEntry.append(BOOKSTORE + "Received new order request from customer:");
		logEntry.append(NEW_LINE + SEPARATOR);
		logEntry.append(BOOKSTORE + anOrder.getCustomer());
		logEntry.append(NEW_LINE);

		logger.info(logEntry.toString());
	}

	@Override
	public void notifyGetCustomerRequest(String customerId, Customer aCustomer) {
		StringBuffer logEntry = new StringBuffer();
		logEntry.append(CUSTOMER_MANAGEMENT_JAXRS + "Get customer for id: " + customerId);
		logEntry.append(NEW_LINE + SEPARATOR);
		logEntry.append(CUSTOMER_MANAGEMENT_JAXRS + "Found customer: " + aCustomer);
		logEntry.append(NEW_LINE + SEPARATOR);

		logger.info(logEntry.toString());
	}
}
