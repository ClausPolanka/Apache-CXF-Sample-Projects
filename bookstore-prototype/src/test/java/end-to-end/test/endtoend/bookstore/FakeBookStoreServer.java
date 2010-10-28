package test.endtoend.bookstore;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import bookstore.BookstoreLibrary;
import bookstore.CustomerManagement;
import bookstore.ShippingService;
import bookstore.Supplier;
import bookstore.Warehouse;
import bookstore.services.BookstoreJaxWS;
import bookstore.services.CustomerManagementJaxRS;
import bookstore.services.CustomerManagementJaxWS;
import bookstore.services.ShippingServiceJaxWS;
import bookstore.services.WarehouseJaxWS;

public class FakeBookStoreServer {

	private BookstoreJaxWS bookstoreService;
	private BookstoreLibrary repository;
	private List<Endpoint> endpoints = new ArrayList<Endpoint>();
	private Server jaxRsServer;

	public FakeBookStoreServer(BookstoreLibrary repository) {
		this.repository = repository;
	}

	public void startSellingProducts() {
		// @formatter:off
		// TODO Extract publishing to ServiceStarte within an explicit call of main-method in a separate thread as deamon-thread.
		// @formatter:on
		endpoints.add(Endpoint.publish("http://localhost:9000/warehouse", new WarehouseJaxWS(repository)));
		endpoints.add(Endpoint.publish("http://localhost:9000/customermanagement", new CustomerManagementJaxWS()));
		endpoints.add(Endpoint.publish("http://localhost:9000/shipping", new ShippingServiceJaxWS()));

		bookstoreService = new BookstoreJaxWS(createCustomerService(), createWarehouse(), createShippingService(), createSupplier());
		endpoints.add(Endpoint.publish("http://localhost:9000/bookstore", bookstoreService));

		publishJaxRSCustomerManagementService();

		assertThat("Order bevore new request", bookstoreService.getOrder(), nullValue());
	}

	private void publishJaxRSCustomerManagementService() {
		JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
		sf.setResourceClasses(CustomerManagementJaxRS.class);
		sf.setResourceProvider(CustomerManagementJaxRS.class, new SingletonResourceProvider(new CustomerManagementJaxRS(repository)));
		sf.setAddress("http://localhost:9000/");
		jaxRsServer = sf.create();
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

	private ShippingService createShippingService() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(ShippingService.class);
		factory.setAddress("http://localhost:9000/shipping");
		return (ShippingService) factory.create();
	}

	private Supplier createSupplier() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(Supplier.class);
		factory.setAddress("http://localhost:9000/supplier");
		return (Supplier) factory.create();
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

	public void stopSellingProducts() {
		for (Endpoint each : endpoints) {
			each.stop();
		}
		jaxRsServer.stop();
	}
}