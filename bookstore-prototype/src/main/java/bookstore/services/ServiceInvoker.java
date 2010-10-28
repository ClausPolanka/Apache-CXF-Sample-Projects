package bookstore.services;

import bookstore.Supplier;

public interface ServiceInvoker {

	Supplier invoke(String serviceAddress, Class<?> type);

}
