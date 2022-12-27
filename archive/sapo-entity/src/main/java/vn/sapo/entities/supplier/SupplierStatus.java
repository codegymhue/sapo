package vn.sapo.entities.supplier;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum SupplierStatus {

    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE");

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
        for (SupplierStatus supplierStatus : SupplierStatus.values())
            if (supplierStatus.value.equals(value)) return supplierStatus;

        throw new IllegalArgumentException("The supplier status [" + value + "] is invalid!");
    }

}