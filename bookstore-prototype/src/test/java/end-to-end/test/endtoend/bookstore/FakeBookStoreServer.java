package test.endtoend.bookstore;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import bookstore.CustomerManagement;
import bookstore.Warehouse;
import bookstore.services.BookstoreJaxWS;
import bookstore.services.CustomerManagementJaxRS;
import bookstore.services.CustomerManagementJaxWS;
import bookstore.services.WarehouseJaxWS;

public class FakeBookStoreServer {

	private BookstoreJaxWS bookstoreService;

	public void startSellingProducts() {
		Endpoint.publish("http://localhost:9000/warehouse", new WarehouseJaxWS());
		Endpoint.publish("http://localhost:9000/customermanagement", new CustomerManagementJaxWS());

		bookstoreService = new BookstoreJaxWS(createCustomerService(), createWarehouse());
		Endpoint.publish("http://localhost:9000/bookstore", bookstoreService);

		publishJaxRSCustomerManagementService();

		assertThat("Order bevore new request", bookstoreService.getOrder(), nullValue());
	}

	private void publishJaxRSCustomerManagementService() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(CustomerManagementJaxRS.class);
		sf.setResourceProvider(CustomerManagementJaxRS.class, new SingletonResourceProvider(new CustomerManagementJaxRS()));
		sf.setAddress("http://localhost:9000/");
		sf.create();
	}

	private CustomerManagement createCustomerService() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(CustomerManagement.class);
		factory.setAddress("http://localhost:9000/customermanagement");
		return (CustomerManagement) factory.create();
	}

	private Warehouse createWarehouse() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Warehouse.class);
		factory.setAddress("http://localhost:9000/warehouse");
		return (Warehouse) factory.create();
	}

	public void hasReceivedNewOrderRequest() {
		// @formatter:off
		assertThat("Customer after new order request",
				   bookstoreService.getOrder().getCustomer(),
				   notNullValue());
		assertThat("Order after new order request",
				   bookstoreService.getOrder(),
				   notNullValue());
		// @formatter:on
	}

	public void queriesCustomer() {
		// @formatter:off
		assertThat("Customer queried via customer service",
				   bookstoreService.getCustomer().getName(),
				   equalTo("customer"));
		// @formatter:on
	}

	public void hasReceivedAvailabilityInformationOfProductFromWarehouse() {
		// @formatter:off
		assertThat("Product available in warehouse",
				   bookstoreService.isProductAvailableInWarehouse(),
				   equalTo(true));
		// @formatter:on
	}
}