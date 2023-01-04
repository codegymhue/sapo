package vn.sapo.entities.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerGender {

    NAM("NAM"), NU("NU"), KHAC("KHAC");

    private final String value;

    CustomerGender(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CustomerGender parseCustomerGender(String value) {
        CustomerGender[] values = values();
        for (CustomerGender gender : values) {
            if (gender.value.equals(value)) return gender;
        }
        throw new IllegalArgumentException(value + "invalid");
    }

}
