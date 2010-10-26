package test.integration.jaxb.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ItemBuilder.anItem;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.Test;

import test.integration.jaxb.bookstore.services.ItemTest;
import test.integration.jaxb.bookstore.services.ItemTestImpl;

import bookstore.Item;

public class ItemJaxBTest {

	private static final String SERVICE_ENDPOINT = "http://localhost:9000/item";

	private ItemTest itemService;

	@Before
	public void publishItemTestEndpoint() {
		Endpoint.publish(SERVICE_ENDPOINT, new ItemTestImpl());
	}

	@Before
	public void createItemServiceClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ItemTest.class);
		factory.setAddress(SERVICE_ENDPOINT);
		itemService = (ItemTest) factory.create();
	}

	@Test
	public void checkIfItemJaxBAnnotationsAreValid() {
		// @formatter:off
		Item anItem = anItem().build();
		// @formatter:on

		Item result = itemService.testItem(anItem);

		assertThat("Item Id", result.getQuantity(), equalTo(2));
		assertThat("Item product", result.getProduct(), notNullValue());
		assertThat("Item order", result.getOrder(), notNullValue());
	}
}
