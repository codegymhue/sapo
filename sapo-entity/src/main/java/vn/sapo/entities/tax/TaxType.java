package vn.sapo.entities.tax;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TaxType {

    TAX_SALE("TAX_SALE"),
    TAX_PURCHASE("TAX_PURCHASE");

    private final String value;

    TaxType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TaxType parseTaxType(String value) {
        TaxType[] values = values();
        for (TaxType typeTax : values) {
            if (typeTax.value.equals(value)) return typeTax;
        }
        throw new IllegalArgumentException("Tax type [" + value + "] is invalid!");
    }
}
