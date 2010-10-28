package bookstore;

import javax.jws.WebService;

@WebService
public interface SupplierRegistry {

	Object getAddressFromSupplierFor(Product aProduct);

}
