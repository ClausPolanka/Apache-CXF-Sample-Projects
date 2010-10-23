package com.ttdev;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

public class ClientCodeGenerator {
	public static void main(String[] args) {
		WSDLToJava.main(new String[] { 
				"-client", 
				"-d", "src/test/java",
				"-p", "http://ttdev.com/cs=com.ttdev.cs.client",
				"src/main/resources/ComputeService.wsdl" });
		System.out.println("Done!");
	}
}
