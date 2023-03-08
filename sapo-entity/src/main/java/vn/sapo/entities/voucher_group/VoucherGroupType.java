package vn.sapo.entities.voucher_group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VoucherGroupType {
    PAYMENT_VOUCHER("PAYMENT_VOUCHER"),
    RECEIPT_VOUCHER("RECEIPT_VOUCHER");

    private final String value;

    VoucherGroupType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static VoucherGroupType parseVoucherGroupType(String value) {
        VoucherGroupType[] values = values();
        for (VoucherGroupType type : values) {
            if (type.value.equals(value)) return type;
        }
        throw new IllegalArgumentException("Voucher group type [" + value + "] is invalid!");
    }
}
