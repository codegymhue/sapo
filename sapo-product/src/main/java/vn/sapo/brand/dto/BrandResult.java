package vn.sapo.brand.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BrandResult {
    private Integer id;
    private String title;

    @Override
    public String toString() {
        return  title ;
    }
}