
package com.mycompany.customerrelations.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(namespace = "http://customerrelations.mycompany.com/", name = "getCustomerResponse")
@XmlType(namespace = "http://customerrelations.mycompany.com/", name = "getCustomerResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCustomerResponse {

	@XmlElement(namespace = "", name = "return")
    private com.mycompany.customerrelations.Customer _return;

    public com.mycompany.customerrelations.Customer get_return ()     {
	           return this._return;
        }

    public void set_return (   com.mycompany.customerrelations.Customer new_return  )     {
	           this._return = new_return;
        }

}

