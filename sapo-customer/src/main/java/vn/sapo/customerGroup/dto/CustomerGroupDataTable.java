package vn.sapo.customerGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerGroupDataTable {

    private Integer draw;

    private Integer start;

    private Integer length;

    private Integer recordsTotal;

    private Integer recordsFiltered;

    private List<CustomerGroupResult> data;
}
