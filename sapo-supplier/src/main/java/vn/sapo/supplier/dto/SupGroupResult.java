package vn.sapo.supplier.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
@Getter
@Setter
@Accessors(chain = true)
public class SupGroupResult {
    private Integer id;

    private String title;

    private String supplierCode;

    private String description;
}
