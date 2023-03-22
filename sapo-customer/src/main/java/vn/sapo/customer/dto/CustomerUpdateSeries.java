package vn.sapo.customer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerUpdateSeries {
    private Integer customerId;
    private Integer employeeId;
    private Integer defaultPrice;
    private String paymentMethodId;

    @Override
    public String toString() {
        return "CustomerUpdateSeries{" +
                "customerId=" + customerId +
                ", employeeId=" + employeeId +
                ", defaultPrice=" + defaultPrice +
                ", paymentMethodId='" + paymentMethodId + '\'' +
                '}';
    }
}
