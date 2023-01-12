package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;

@Getter
@Setter
@Accessors(chain = true)
public class CusGrpPaymentMethodResult {

    private String id;

    private String title;

    @Override
    public String toString() {
        return  title;
    }
}
