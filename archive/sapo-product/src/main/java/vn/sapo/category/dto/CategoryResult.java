package vn.sapo.category.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CategoryResult {
    private Integer id;
    private String title;

    @Override
    public String toString() {
        return  title;
    }
}