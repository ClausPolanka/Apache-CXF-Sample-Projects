package com.mycompany.customerrelations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceServer {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
		        new String[] { "server-applicationContext.xml"});		
		System.in.read();
	}
}