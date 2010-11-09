package bookstore;

import javax.jws.WebService;

import org.xmlsoap.schemas.ws._2004._08.addressing.EndpointReferenceType;

@WebService
public interface SupplierRegistry {

	public static final String GERMAN_SUPPLIER_ADDRESS = "http://localhost:9000/germansupplier";
	public static final String AUSTRIA_SUPPLIER_ADDRESS = "http://localhost:9000/austriasupplier";

	EndpointReferenceType getAddressFromSupplierFor(Product aProduct);

}
