package bookstore.services;

import java.math.BigDecimal;

import bookstore.Product;
import bookstore.Supplier;
import bookstore.SupplierRegistry;

public class SupplierJaxWs implements Supplier {

	private SupplierRegistry registry;
	private ServiceInvoker invoker;

	public SupplierJaxWs(SupplierRegistry registry, ServiceInvoker invoker) {
		this.registry = registry;
		this.invoker = invoker;
	}

	@Override
	public BigDecimal order(Product product, int amount) {
		Object address = registry.getAddressFromSupplierFor(product);
		Supplier supplier = invoker.invoke("address", Supplier.class);
		return supplier.order(product, amount);
	}
}
