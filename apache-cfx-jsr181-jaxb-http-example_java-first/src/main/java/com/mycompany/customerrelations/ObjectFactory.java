
package com.mycompany.customerrelations;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;


public class ObjectFactory {

    private final static QName _Customer_QNAME = new QName("http://www.mycompany.com/customerrelations", "customer");
    private final static QName _Gender_QNAME = new QName("http://www.mycompany.com/customerrelations", "gender");
    private final static QName _Person_QNAME = new QName("http://www.mycompany.com/customerrelations", "person");    
    private final static QName _Address_QNAME = new QName("http://www.mycompany.com/customerrelations", "address");

    public ObjectFactory() {
    }

    public Person createPerson() {
        return new Person();
    }

    public Address createAddress() {
        return new Address();
    }

    public Customer createCustomer() {
        return new Customer();
    }
    
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    public JAXBElement<Gender> createGender(Gender value) {
        return new JAXBElement<Gender>(_Gender_QNAME, Gender.class, null, value);
    }

    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    public JAXBElement<Address> createAddress(Address value) {
        return new JAXBElement<Address>(_Address_QNAME, Address.class, null, value);
    }

}
