package vn.sapo.entities.pricing_policy;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PricingPolicyType {
    BANHANG("Bán hàng"), NHAPHANG("Nhập hàng");
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
        for (PricingPolicyType pricingPolicyType : values) {
            if (pricingPolicyType.value.equals(value)) return pricingPolicyType;
        }
        throw new IllegalArgumentException(value + "invalid");
    }
}
