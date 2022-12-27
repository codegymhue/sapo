package vn.sapo.tax.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class TaxResult {
    private Integer id;
    private String code;
    private String title;
    private float tax;
    private boolean isDefault;

    @Override
    public String toString() {
        return  title ;
    }
}
