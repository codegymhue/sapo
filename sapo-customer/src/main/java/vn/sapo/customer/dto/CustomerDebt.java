package vn.sapo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;



public interface CustomerDebt {

      String getOrderCode();

      String getEmployeeName();

      String getDescription();

     Instant getCreateAt();


     BigDecimal getTotalDebt();

     BigDecimal getTransaction();

}
