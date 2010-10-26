package test.integration.bookstore.services;
import javax.jws.WebService;

import bookstore.Order;

@WebService
public interface OrderTest {

	Order testOrder(Order order);
}
