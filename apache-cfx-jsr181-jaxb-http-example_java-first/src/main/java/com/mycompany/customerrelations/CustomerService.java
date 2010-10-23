package com.mycompany.customerrelations;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface CustomerService {
	@WebMethod(action = "getCustomer")
	public Customer getCustomer(
			@WebParam(name = "customerNummber") String customerNumber)
		throws BusinessLogicException;
}
