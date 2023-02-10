package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.CustomerStatus;
import vn.sapo.entities.supplier.SupplierStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class SupplierFilter {
    private String filter;
    private List<SupplierStatus> statuses;
    private List<Integer> employeeIds;
    private List<Integer> supplierGroupId;
    private Date createdFrom;
    private Date createdTo;

//    private Integer pageNo;           //page hiện tại
//    private Integer pageSize;            // số lượng trên 1 trang
//    private Integer start;
}
