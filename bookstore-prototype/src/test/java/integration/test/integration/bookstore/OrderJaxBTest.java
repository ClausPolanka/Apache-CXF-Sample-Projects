package test.integration.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.OrderBuilder.anOrder;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import test.integration.bookstore.services.OrderTest;
import test.integration.bookstore.services.OrderTestImpl;

import bookstore.Order;

public class OrderJaxBTest {

	private static final String SERVICE_ENDPOINT = "http://localhost:9000/order";

	private OrderTest orderService;

	@Before
	public void publishOrderTestEndpoint() {
		Endpoint.publish(SERVICE_ENDPOINT, new OrderTestImpl());
	}

	@Before
	public void createOrderServiceClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(OrderTest.class);
		factory.setAddress(SERVICE_ENDPOINT);
		orderService = (OrderTest) factory.create();
	}

	@Test
	public void checkIfOrderJaxBAnnotationsAreValid() {
		// @formatter:off
		Order anOrder = anOrder().build();
		// @formatter:on

		Order result = orderService.testOrder(anOrder);

		assertThat("Order Id", result.getId(), equalTo("orderId New"));
		assertThat("Order customer", result.getCustomer(), notNullValue());
	}
}
