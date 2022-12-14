package vn.sapo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CustomerDebtImpl {
    private String order_code;

    private String employee_name;

    private Instant create_at;

    private BigDecimal transaction;


    private  BigDecimal totalDebt;

    private  String description;


    public void setFromICustomerOwer(CustomerDebt customerDebt) {
        this.order_code = customerDebt.getOrderCode();
        this.employee_name = customerDebt.getEmployeeName();
        this.create_at = customerDebt.getCreateAt();
        this.transaction = customerDebt.getTransaction();
        this.totalDebt = customerDebt.getTotalDebt();
        this.description = customerDebt.getDescription();
    }
}
