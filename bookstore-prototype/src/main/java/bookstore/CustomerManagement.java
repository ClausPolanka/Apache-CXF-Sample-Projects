package bookstore;

import javax.jws.WebService;

@WebService
public interface CustomerManagement {

	Customer createCustomer(Customer customer);

	void deleteCustomer(String id);

	Customer getCustomer(String id);

	void updateCustomer(Customer customer);

}
