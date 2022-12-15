package vn.sapo.category.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CategoryCreateParam {

    private String name;

}