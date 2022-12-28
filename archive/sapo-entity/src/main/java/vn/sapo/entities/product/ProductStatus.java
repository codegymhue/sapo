package vn.sapo.entities.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductStatus {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE");

    private final String value;

    ProductStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ProductStatus parseProductStatus(String value) {
        ProductStatus[] values = values();
        for (ProductStatus productStatus : values) {
            if (productStatus.value.equals(value)) return productStatus;
        }
        throw new IllegalArgumentException(value + " is invalid!");
    }
}
