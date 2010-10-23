
package com.mycompany.customerrelations;

public class Address {
    
    protected String line1;
    protected String line2;
    protected String postalCode;   
    protected String city;
    protected String country;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String value) {
        this.line1 = value;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String value) {
        this.line2 = value;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

}
