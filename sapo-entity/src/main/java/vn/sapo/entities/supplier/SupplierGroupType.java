package vn.sapo.entities.supplier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import vn.sapo.entities.customer.CustomerGroupType;

public enum SupplierGroupType {
    FIXED("FIXED"), DYNAMIC("DYNAMIC");

    private final String value;

    SupplierGroupType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SupplierGroupType parseSupplierGroupType(String value) {
        SupplierGroupType[] values = values();
        for (SupplierGroupType type : values) {
            if (type.value.equals(value)) return type;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
