package bookstore;

import java.util.Date;
import java.util.logging.Logger;

public class SystemOutLogger implements InformationReporter {

	private static final String SHIPPING_SERVICE = "[ShippingService] ";
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
		logEntry.append(NEW_LINE + "      ");
		logEntry.append(SHIPPING_SERVICE);
		logEntry.append("Sending item" + (items.length > 1 ? "s " : " "));
		appendItems(logEntry, items);
		logEntry.append(NEW_LINE + "      ");
		logEntry.append(SHIPPING_SERVICE);
		logEntry.append(anAddress);
		logEntry.append(NEW_LINE);
		return logEntry;
	}

	private void appendItems(StringBuffer logEntry, Item[] items) {
		for (int i = 0; i < items.length; i++) {
			logEntry.append("\"" + items[i].getProductName() + "\"");
			logEntry.append(i == items.length - 1 ? "" : ",");
		}
		logEntry.append(" to");
	}
}
