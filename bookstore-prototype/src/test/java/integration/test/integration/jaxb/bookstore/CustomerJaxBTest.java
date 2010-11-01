package test.integration.jaxb.bookstore;

import javax.xml.bind.JAXBContext;

import org.junit.Test;

import bookstore.Customer;

public class CustomerJaxBTest {

	@Test
	public void printOutXmlSchemaForCustomer() throws Exception {
		JAXBContext ctx = JAXBContext.newInstance(Customer.class);
		ctx.generateSchema(new SystemOutSchemaOutput());
	}
}
