package bookstore.services;

import java.math.BigDecimal;

import javax.jws.WebService;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.Supplier;

// @formatter:off
@WebService(endpointInterface = "bookstore.Supplier",
    	    serviceName = "AustriaSupplier",
            targetNamespace = "http://infosys.tuwien.ac.at/aic10/ass1/dto/supplier",
            portName = "SupplierPT")
public class AustriaSupplierJaxWs implements Supplier {
// @formatter:on
	private BookstoreLibrary library;

	public AustriaSupplierJaxWs(BookstoreLibrary library) {
		this.library = library;
	}

	@Override
	public BigDecimal order(Product product, int amount) {
		for (int i = 0; i < amount; i++) {
			library.getFromAustriaSupplier(product.getId());
		}
		return product.getSingleUnitPrice().multiply(new BigDecimal(amount));
	}
}
