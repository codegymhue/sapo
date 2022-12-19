package vn.sapo.category.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UpdateCategoryParam {
    private Integer id;
    private String title;
}