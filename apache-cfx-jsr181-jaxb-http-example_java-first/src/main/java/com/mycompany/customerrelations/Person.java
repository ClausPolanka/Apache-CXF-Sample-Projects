
package com.mycompany.customerrelations;

import java.util.Calendar;

public class Person {
	
    protected String firstname;    
    protected String lastname;
    protected Gender gender;
    protected Calendar birthday;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String value) {
        this.firstname = value;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String value) {
        this.lastname = value;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender value) {
        this.gender = value;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar value) {
        this.birthday = value;
    }

}
