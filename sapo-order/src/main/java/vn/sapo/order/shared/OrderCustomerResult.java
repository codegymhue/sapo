package vn.sapo.order.shared;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class OrderCustomerResult {
    private Integer id;
    private String fullName;
}
