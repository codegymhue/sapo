package vn.sapo.entities.customer;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
//TODO: Modelmapper loi ko can quan tam: Cu
public enum CustomerGroupType {

    FIXED("FIXED"), DYNAMIC("DYNAMIC");

    private final String value;

    CustomerGroupType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static CustomerGroupType parseCustomerGroupType(String value) {
        CustomerGroupType[] values = values();
        for (CustomerGroupType type : values) {
            if (type.value.equals(value)) return type;
        }
        throw new IllegalArgumentException(value + "invalid");
    }

}
