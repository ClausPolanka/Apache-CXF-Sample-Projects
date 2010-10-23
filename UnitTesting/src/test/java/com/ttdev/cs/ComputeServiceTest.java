package com.ttdev.cs;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.transport.ConduitInitiatorManager;
import org.apache.cxf.transport.local.LocalTransportFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;

import com.ttdev.cs.ComplexLogic;
import com.ttdev.cs.ComputeServiceImpl;
import com.ttdev.cs.client.ComputeService;
import com.ttdev.cs.client.ComputeService_Service;

@ContextConfiguration(locations = { "/beans.xml", "/beans-test.xml" })
public class ComputeServiceTest extends AbstractJUnit38SpringContextTests {
	private Bus bus;

	@Override
	protected void setUp() throws Exception {
		initBus();
	}

	@Override
	protected void tearDown() throws Exception {
		bus.shutdown(false);
	}

	public void testCompute() {
		ComputeServiceImpl impl = (ComputeServiceImpl) applicationContext
				.getBean("serviceImpl");
		impl.setLogic(new ComplexLogic() {

			@Override
			public String calc(String s) {
				return s.toUpperCase();
			}
		});
		ComputeService_Service ss = new ComputeService_Service();
		ComputeService port = ss.getP1();
		setAddress(port, "local://ep1");
		assertEquals(port.compute("xyz"), "XYZ");

	}

	private void setAddress(ComputeService port, String addr) {
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				addr);
	}

	private void initBus() {
		bus = new SpringBusFactory(applicationContext).createBus();
		setupLocalTransport();
	}

	private void setupLocalTransport() {
		LocalTransportFactory localTransportFactory = (LocalTransportFactory) applicationContext
				.getBean("localTransportFactory");
		ConduitInitiatorManager cim = bus
				.getExtension(ConduitInitiatorManager.class);
		cim.registerConduitInitiator("http://cxf.apache.org/transports/local",
				localTransportFactory);
		cim.registerConduitInitiator(
				"http://schemas.xmlsoap.org/wsdl/soap/http",
				localTransportFactory);
		cim.registerConduitInitiator("http://schemas.xmlsoap.org/soap/http",
				localTransportFactory);
		cim.registerConduitInitiator("http://cxf.apache.org/bindings/xformat",
				localTransportFactory);
	}

}
