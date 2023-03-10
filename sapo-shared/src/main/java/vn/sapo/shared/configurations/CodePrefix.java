package vn.sapo.shared.configurations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CodePrefix {

    SUPPLIER("SUPN"),
    SUPPLIER_GROUP("STN"),
    CUSTOMER("CUZN"),
    SALE_ORDER("SON"),
    CUSTOMER_GROUP("CTN"),
    RECEIPT_VOUCHER("RVGN"),
    PAYMENT_VOUCHER("PVGN"),

    ANONYMOUS("ANONYMOUS");


    private final String value;

    CodePrefix(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CodePrefix parseOrderCodePrefix(String value) {
        CodePrefix[] values = values();
        for (CodePrefix codePrefix : values) {
            if (codePrefix.value.equals(value))
                return codePrefix;
        }
        throw new IllegalArgumentException(value + "invalid");
    }

    public String generate(int value) {
        return String.format("%s%05d", this.value, value);
    }
}
