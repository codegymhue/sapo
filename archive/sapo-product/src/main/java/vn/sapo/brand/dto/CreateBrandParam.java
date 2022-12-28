package vn.sapo.brand.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CreateBrandParam {
    private String title;
}