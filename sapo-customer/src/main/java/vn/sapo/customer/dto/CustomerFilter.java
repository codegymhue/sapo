package vn.sapo.customer.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CustomerFilter {
    private String keyword;
    private Integer id;
    private String customerCode;
    List<Integer> groupIds;
    private String gender;
    private Integer dayOfBirthday;
    private Integer monthOfBirthday;
    private List<Integer> employeeIds;
    private List<CustomerStatus> statusList;
    private Date createdFrom;
    private Date createdTo;

    private Integer draw;           //page hiện tại
    private Integer length;            // số lượng trên 1 trang
    private Integer start;             // lấy bắt đầu từ



}
