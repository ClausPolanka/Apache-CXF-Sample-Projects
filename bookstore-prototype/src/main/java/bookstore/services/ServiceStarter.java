package bookstore.services;

import javax.xml.ws.Endpoint;

public class ServiceStarter {

	public static void main(String[] args) {
		new ServiceStarter().provideServices();
	}

	private void provideServices() {
		Endpoint.publish("http://localhost:9000/shipping", new ShippingServiceJaxWS());
	}

}
