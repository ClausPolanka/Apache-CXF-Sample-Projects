package com.mycompany.customerrelations;

public class CustomerServiceImpl implements CustomerService {
	public Customer getCustomer(String customerNumber) throws BusinessLogicException {	
		if("12345".equals(customerNumber)) {
			Person maxMueller = new Person();
			maxMueller.setFirstname("Max");
			maxMueller.setLastname("Müller");
			maxMueller.setGender(Gender.MALE);
			Address maxMuellerAddress = new Address();
			maxMuellerAddress.setLine1("Mussterstr. 10");
			maxMuellerAddress.setLine2("");
			maxMuellerAddress.setPostalCode("12345");
			maxMuellerAddress.setCity("Musterhausen");
			Customer maxMuellerCustomer = new Customer();		
			maxMuellerCustomer.setPerson(maxMueller);
			maxMuellerCustomer.getAddress().add(maxMuellerAddress);
			return maxMuellerCustomer;
		}
		throw new BusinessLogicException("No such customer");
	}
}
