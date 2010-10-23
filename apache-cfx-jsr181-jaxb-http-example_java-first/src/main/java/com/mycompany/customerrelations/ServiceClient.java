package com.mycompany.customerrelations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceClient {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "client-applicationContext.xml" });		
		CustomerService customerService = (CustomerService) applicationContext
				.getBean("customerService");
		Customer customer = customerService.getCustomer("12345");
		System.out.println(customer);
	}
}