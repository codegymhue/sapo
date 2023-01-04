package vn.sapo.tax.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CreateTaxParam {

    private Integer id;
    private String code;
    private String title;
    private float tax;
}
