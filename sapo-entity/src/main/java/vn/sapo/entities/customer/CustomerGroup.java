package vn.sapo.entities.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerGroup {
    VIP("VIP"), LE("LE"), CHAN("CHAN");

    private final String value;

    CustomerGroup(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CustomerGroup parseCustomerGroup(String value) {
        CustomerGroup[] values = values();
        for (CustomerGroup customerGroup : values) {
            if (customerGroup.value.equals(value)) return customerGroup;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
