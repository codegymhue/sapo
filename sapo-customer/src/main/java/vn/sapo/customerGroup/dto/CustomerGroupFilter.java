package vn.sapo.customerGroup.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerStatus;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CustomerGroupFilter {

    private String title;

    private String cusGrpCode;

    private String customerGroupType;

    private String description;

    private Long countCus;

    private Instant createdAt;

    private Integer draw;           //page hiện tại

    private Integer length;            // số lượng trên 1 trang

    private Integer start;             // lấy bắt đầu từ

//    private Integer id;

//    private String keyword;
//
//    private String customerCode;
//
//    private List<Integer> groupIds;
//
//    private String gender;
//
//    private Integer dayOfBirthday;
//
//    private Integer monthOfBirthday;
//
//    private List<Integer> employeeIds;
//
//    private List<CustomerStatus> statusList;
//
//    private Date createdFrom;
//
//    private Date createdTo;
//
}
