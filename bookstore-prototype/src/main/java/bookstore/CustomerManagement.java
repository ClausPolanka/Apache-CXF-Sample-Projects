package bookstore;

import java.math.BigDecimal;

import javax.jws.WebService;

@WebService
public interface CustomerManagement {

	Customer createCustomer(Customer customer);

	void deleteCustomer(String id);

	Customer getCustomer(String id);

	void updateCustomer(Customer customer);

	void updateAccount(String id, BigDecimal balance);

	void notify(String customerId, NotificationMessage message);

}
