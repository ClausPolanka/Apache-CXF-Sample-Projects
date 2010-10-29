package bookstore;

import javax.jws.WebService;

import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

@WebService
public interface SupplierRegistry {

	EndpointReferenceType getAddressFromSupplierFor(Product aProduct);

}
