package vn.sapo.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDataTable {
    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private List<CustomerResult> data;
}
