package vn.sapo.entities.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FixPriceSellingType {
    //Not allowed to edit the entire sales channel
    DISABLED_MODIFY_ENTIRE_SALES_CHANEL("DISABLED_MODIFY_ENTIRE_SALES_CHANEL");
    private final String value;
    FixPriceSellingType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static FixPriceSellingType parseFixPriceSellingType(String value) {
        FixPriceSellingType[] values = values();
        for (FixPriceSellingType type : values) {
            if (type.value.equals(value)) return type;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
