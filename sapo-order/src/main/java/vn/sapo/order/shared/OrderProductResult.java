package vn.sapo.order.shared;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class OrderProductResult {
    private Integer id;
    private String title;
    private String mainUrl;
    private String sku;
}
