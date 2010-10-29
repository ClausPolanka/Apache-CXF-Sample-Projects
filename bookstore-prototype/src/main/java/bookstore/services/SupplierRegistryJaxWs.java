package bookstore.services;

import org.xmlsoap.schemas.ws._2004._08.addressing.AttributedURI;
import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

import bookstore.BookstoreLibrary;
import bookstore.Product;
import bookstore.SupplierRegistry;

public class SupplierRegistryJaxWs implements SupplierRegistry {

	private BookstoreLibrary library;

	public SupplierRegistryJaxWs(BookstoreLibrary library) {
		this.library = library;
	}

	@Override
	public EndpointReferenceType getAddressFromSupplierFor(Product aProduct) {
		String address = library.getSupplierAddressFor(aProduct.getId());
		if (notValid(address)) {
			throw new RuntimeException("UnknownProductSoapFault");
			// TODO Throw UnknownProductFault instead of RuntimeException
		}
		return anEndpointFor(address);
	}

	private boolean notValid(String address) {
		if (address.isEmpty()) {
			return true;
		}
		return false;
	}

	private EndpointReferenceType anEndpointFor(String address) {
		AttributedURI uri = new AttributedURI();
		uri.setValue(address);
		EndpointReferenceType endpoint = new EndpointReferenceType();
		endpoint.setAddress(uri);
		return endpoint;
	}
}
