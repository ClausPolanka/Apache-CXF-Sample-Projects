package test.integration.jaxwsjaxb.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.ItemBuilder.anItem;
import static test.endtoend.bookstore.builder.ProductBuilder.aProduct;

import java.math.BigDecimal;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.integration.jaxwsjaxb.bookstore.services.ProductTest;
import test.integration.jaxwsjaxb.bookstore.services.ProductTestImpl;
import bookstore.Product;

public class ProductJaxWsJaxBTest {

	private static final String SERVICE_ENDPOINT = "http://localhost:9000/product";

	private ProductTest productService;

	@BeforeClass
	public static void publishProductTestEndpoint() {
		Endpoint.publish(SERVICE_ENDPOINT, new ProductTestImpl());
	}

	@Before
	public void createProductServiceClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ProductTest.class);
		factory.setAddress(SERVICE_ENDPOINT);
		productService = (ProductTest) factory.create();
	}

	@Test
	public void checkIfProductJaxBAnnotationsAreValid() {
		// @formatter:off
		Product aProduct = aProduct()
			.forItem(anItem().build())
		.build();
		// @formatter:on

		Product result = productService.testProduct(aProduct);

		assertThat("Product Id", result.getId(), equalTo("productId New"));
		assertThat("Product Id", result.getName(), equalTo("product New"));
		assertThat("Product single unit price", result.getSingleUnitPrice(), equalTo(new BigDecimal(2)));
		assertThat("Product for item", result.getItems().get(0), notNullValue());
	}
}
