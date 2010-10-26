package test.integration.jaxwsjaxb.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static test.endtoend.bookstore.builder.AddressBuilder.anAddress;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import test.integration.jaxwsjaxb.bookstore.services.AddressTest;
import test.integration.jaxwsjaxb.bookstore.services.AddressTestImpl;
import bookstore.Address;

public class AddressJaxWsJaxBTest {

	private static final String SERVICE_ENDPOINT = "http://localhost:9000/address";

	private AddressTest addressService;

	@BeforeClass
	public static void publishAddressTestEndpoint() {
		Endpoint.publish(SERVICE_ENDPOINT, new AddressTestImpl());
	}

	@Before
	public void createAddressServiceClient() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(AddressTest.class);
		factory.setAddress(SERVICE_ENDPOINT);
		addressService = (AddressTest) factory.create();
	}

	@Test
	public void checkIfAddressJaxBAnnotationsAreValid() {
		// @formatter:off
		Address anAddress = anAddress().build();
		// @formatter:on

		Address result = addressService.testAddress(anAddress);

		assertThat("Address Id", result.getId(), equalTo("addressId New"));
		assertThat("Address street", result.getStreet(), equalTo("street New"));
		assertThat("Address city", result.getCity(), equalTo("city New"));
		assertThat("Address house", result.getHouse(), equalTo(2));
		assertThat("Address door", result.getDoor(), equalTo(2));
		assertThat("Address zipCode", result.getZipCode(), equalTo("zipCode New"));
		assertThat("Address shippable", result.isShipping(), equalTo(true));
		assertThat("Address billing", result.isBilling(), equalTo(true));
		assertThat("Address other", result.isOther(), equalTo(true));

	}

}
