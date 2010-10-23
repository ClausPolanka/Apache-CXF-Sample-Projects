
package com.mycompany.customerrelations;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    protected String customerNumber;
    protected Person person;
    protected List<Address> address;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String value) {
        this.customerNumber = value;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person value) {
        this.person = value;
    }

    public List<Address> getAddress() {
        if (address == null) {
            address = new ArrayList<Address>();
        }
        return this.address;
    }

}
