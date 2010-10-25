package bookstore;

import javax.jws.WebService;

@WebService
public interface Bookstore {

	void requestOrder(Order order);

}
