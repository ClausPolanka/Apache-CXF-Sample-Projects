package bookstore.services;

import java.math.BigDecimal;

import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

import bookstore.Product;
import bookstore.Supplier;
import bookstore.SupplierRegistry;

public class SupplierFacadeJaxWs implements Supplier {

	private SupplierRegistry registry;
	private ServiceInvoker invoker;

	public SupplierFacadeJaxWs(SupplierRegistry registry, ServiceInvoker invoker) {
		this.registry = registry;
		this.invoker = invoker;
	}

	@Override
	public BigDecimal order(Product product, int amount) {
		EndpointReferenceType endPoint = registry.getAddressFromSupplierFor(product);
		Supplier supplier = invoker.invoke(endPoint.getAddress().getValue(), Supplier.class);
		return supplier.order(product, amount);
	}
}
