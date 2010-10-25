package bookstore.services;

import javax.xml.ws.Endpoint;

public class ServiceStarter {

	public static void main(String[] args) {
		new ServiceStarter().provideServices();
	}

	private void provideServices() {
		BookstoreJaxWS bookstore = new BookstoreJaxWS(new CustomerManagementJaxWS(), new WarehouseJaxWS());
		String address = "http://localhost:9000/bookstore";
		Endpoint.publish(address, bookstore);
	}

}
