package vn.sapo.entities.voucher_group;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VoucherGroupStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");

    private final String value;

    VoucherGroupStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static VoucherGroupStatus parseVoucherGroupType(String value) {
        VoucherGroupStatus[] values = values();
        for (VoucherGroupStatus status : values) {
            if (status.value.equals(value)) return status;
        }
        throw new IllegalArgumentException("Voucher group status [" + value + "] is invalid!");
    }
}
