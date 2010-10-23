package com.mycompany.customerrelations.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://customerrelations.mycompany.com/", name = "getCustomerRequest")
@XmlType(namespace = "http://customerrelations.mycompany.com/", name = "getCustomerRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCustomerRequest {

	@XmlElement(namespace = "", name = "customerNumber")
	private java.lang.String arg0;

	public java.lang.String getArg0() {
		return this.arg0;
	}

	public void setArg0(String customerNumber) {
		this.arg0 = customerNumber;
	}

}
