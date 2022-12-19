package vn.sapo.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ProductShortParam {
    private String id;
    private String title;
    private String sku;
    private String quantity;
    private String retailPrice;
    private String importPrice;
    private String categoryId;
    private String mass;
}
