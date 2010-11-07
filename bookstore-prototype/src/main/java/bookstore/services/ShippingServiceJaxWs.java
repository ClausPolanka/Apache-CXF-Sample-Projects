package bookstore.services;

import java.util.UUID;

import bookstore.Address;
import bookstore.BookstoreLibrary;
import bookstore.InformationReporter;
import bookstore.Item;
import bookstore.ShippingService;
import bookstore.UnknownAddressFault;

public class ShippingServiceJaxWs implements ShippingService {

	public static final String SHIPPING_ADDRESS_UNKNOWN = "Shipping address unknown";

	private BookstoreLibrary library;
	private InformationReporter reporter;

	public ShippingServiceJaxWs(BookstoreLibrary library, InformationReporter reporter) {
		this.library = library;
		this.reporter = reporter;
	}

	@Override
	public String shipItems(Item[] items, Address customerShippingAddress) {
		if (notValid(customerShippingAddress)) {
			throw new UnknownAddressFault(SHIPPING_ADDRESS_UNKNOWN);
		}
		reporter.notifyShippingEvent(items, customerShippingAddress);
		return new UUID(4242L, 4242L).toString();
	}

	private boolean notValid(Address customerShippingAddress) {
		return !library.isValid(customerShippingAddress.getId());
	}

}
