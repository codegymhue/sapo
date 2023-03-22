package vn.sapo.customerGroup.dto;

import java.time.Instant;

public interface ICustomerGroupResult {
    Integer getId();

    String getTitle();

    String getType();

    String getCusGrpCode();

    Integer getCountCus();

    Instant getCreatedAt();

    String getNote();

}
