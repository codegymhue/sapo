package vn.sapo.entities.supplier;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum SupplierStatus {

    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"),
    DELETED("DELETED");

    private final String value;

    SupplierStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SupplierStatus parseSupplierStatus(String value) {
        for (SupplierStatus status : SupplierStatus.values())
            if (status.value.equals(value)) return status;

        throw new IllegalArgumentException("The supplier status [" + value + "] is invalid!");
    }

}