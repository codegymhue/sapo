package vn.sapo.entities.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerStatus {
    UNAVAILABLE("UNAVAILABLE"), AVAILABLE("AVAILABLE"), TERMINATED("TERMINATED");

    private final String value;

    CustomerStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CustomerStatus parseCustomerGroup(String value) {
        CustomerStatus[] values = values();
        for (CustomerStatus customerStatus : values) {
            if (customerStatus.value.equals(value)) return customerStatus;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}