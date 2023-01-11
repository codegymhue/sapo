package vn.sapo.order.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.order.shared.OrderCustomerResult;
import vn.sapo.order.shared.OrderEmployeeResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SaleOrderResult {

    private Integer id;

    private String fullName;

    private String mobile;

    private String line1;

    private String line2;

    private String city;

    private String province;

    private String zipCode;

    private Integer employeeId;

    private OrderEmployeeResult employee;

    private String orderCode;

    private String orderStatusId;

    private SaleOrderStatusResult orderStatus;

    private String paymentStatusId;

    private SaleOrderStatusResult paymentStatus;

    private BigDecimal discount;

    private String description;

    private Instant createdAt;

    private Instant updatedAt;

    private Integer customerId;

    private OrderCustomerResult customer;

    private BigDecimal total;

    private BigDecimal subTotal;

    private BigDecimal grandTotal;

    private List<SaleOrderItemResult> orderItems;

}