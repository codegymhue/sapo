package vn.sapo.configurations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CodePrefix {

    CUSTOMER("CUZN"),
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

    public static String format(int value) {
        return String.format("%05d", value);
    }
}
