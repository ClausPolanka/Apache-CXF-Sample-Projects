package test.endtoend.bookstore;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import bookstore.Bookstore;
import bookstore.Order;

public class ApplicationClient {

	public void orders(Order order) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Bookstore.class);
		factory.setAddress("http://localhost:9000/bookstore");
		Bookstore bookstore = (Bookstore) factory.create();
		bookstore.requestOrder(order);
	}
}
