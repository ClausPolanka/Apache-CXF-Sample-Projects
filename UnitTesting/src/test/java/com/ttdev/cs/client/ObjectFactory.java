
package com.ttdev.cs.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ttdev.cs.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ComputeResponse_QNAME = new QName("http://ttdev.com/cs", "computeResponse");
    private final static QName _Compute_QNAME = new QName("http://ttdev.com/cs", "compute");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ttdev.cs.client
     * 
     */ 
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ttdev.com/cs", name = "computeResponse")
    public JAXBElement<String> createComputeResponse(String value) {
        return new JAXBElement<String>(_ComputeResponse_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ttdev.com/cs", name = "compute")
    public JAXBElement<String> createCompute(String value) {
        return new JAXBElement<String>(_Compute_QNAME, String.class, null, value);
    }

}
