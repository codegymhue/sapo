package vn.sapo.entities.product.pricing_policy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PricingPolicyType {
    SALE("SALE"), PURCHASE("PURCHASE");
    private final String value;

    PricingPolicyType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static PricingPolicyType parsePricingPolicyType(String value) {
        PricingPolicyType[] values = values();
        for (PricingPolicyType type : values) {
            if (type.value.equals(value)) return type;
        }
        throw new IllegalArgumentException(value + " invalid");
    }
}
