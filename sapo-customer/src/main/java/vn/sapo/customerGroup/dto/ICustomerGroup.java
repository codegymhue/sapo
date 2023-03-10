package vn.sapo.customerGroup.dto;

import java.time.Instant;

public interface ICustomerGroup {
    Integer getId();

    String getTitle();

    String getCusGrpCode();

    Instant getCreatedAt();

    Long getCountCus();

    String getDescription();

}
