package vn.sapo.customerGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.sapo.customer.dto.CustomerResult;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGroupDataTable {

    private Integer draw;

    private Long recordsTotal;

    private Long recordsFiltered;

    private List<CustomerGroupResult> data;
}
