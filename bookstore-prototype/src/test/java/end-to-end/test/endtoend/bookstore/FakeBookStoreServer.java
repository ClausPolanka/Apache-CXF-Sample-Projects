package test.endtoend.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

import bookstore.services.BookstoreJaxWS;
import bookstore.services.CustomerManagementJaxRS;
import bookstore.services.CustomerManagementJaxWS;

public class FakeBookStoreServer {

	protected static final String[] NO_ARGS = null;
	private BookstoreJaxWS bookstoreWebService;
	private CustomerManagementJaxWS customerManagementJaxWS;

	public FakeBookStoreServer(BookstoreJaxWS bookstoreWebService, CustomerManagementJaxWS customerManagementJaxWS) {
		this.bookstoreWebService = bookstoreWebService;
		this.customerManagementJaxWS = customerManagementJaxWS;
	}

	public void startSellingProducts() {
		Endpoint.publish("http://localhost:9000/bookstore", bookstoreWebService);
		Endpoint.publish("http://localhost:9000/customermanagement", customerManagementJaxWS);

		publishJaxRSCustomerManagementService();

		assertThat("Order bevore new request", bookstoreWebService.getOrder(), nullValue());
	}

	private void publishJaxRSCustomerManagementService() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(CustomerManagementJaxRS.class);
		sf.setResourceProvider(CustomerManagementJaxRS.class, new SingletonResourceProvider(new CustomerManagementJaxRS()));
		sf.setAddress("http://localhost:9000/");
		sf.create();
	}

	public void hasReceivedNewOrderRequest() {
		// @formatter:off
		assertThat("Customer after new order request",
				   bookstoreWebService.getOrder().getCustomer(),
				   notNullValue());
		assertThat("Order after new order request",
				   bookstoreWebService.getOrder(),
				   notNullValue());
		// @formatter:on
	}

	public void queriesCustomer() {
		// @formatter:off
		assertThat("Customer queried via customer service",
				   bookstoreWebService.getCustomer().getName(),
				   equalTo("customer"));
		// @formatter:on
	}

	public void hasReceivedAvailabilityInformationOfProductFromWarehouse() {
		// @formatter:off
		assertThat("Product available in warehouse",
				   bookstoreWebService.isProductAvailableInWarehouse(),
				   equalTo(true));
		// @formatter:on
	}
}