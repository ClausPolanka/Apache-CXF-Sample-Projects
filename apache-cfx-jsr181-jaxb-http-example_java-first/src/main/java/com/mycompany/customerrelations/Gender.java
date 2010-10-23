
package com.mycompany.customerrelations;

public enum Gender {

    FEMALE,
    MALE;

    public String value() {
        return name();
    }

    public static Gender fromValue(String v) {
        return valueOf(v);
    }

}
