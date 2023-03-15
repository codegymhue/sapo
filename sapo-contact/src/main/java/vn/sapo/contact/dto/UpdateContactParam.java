package vn.sapo.contact.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateContactParam extends BaseContact {
    @NotNull
    private Long id;
}
