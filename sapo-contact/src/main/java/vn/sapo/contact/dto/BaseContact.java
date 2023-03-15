package vn.sapo.contact.dto;

import lombok.Getter;
import lombok.Setter;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BaseContact {
    @NotBlank
    private String fullName;
    @NullOrNotBlank
    private String phoneNumber;
    @NullOrNotBlank
    private String email;
    @NullOrNotBlank
    private String fax;
    @NullOrNotBlank
    private String position;
    @NullOrNotBlank
    private String department;
    @NullOrNotBlank
    private String note;
}
