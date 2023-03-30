package vn.sapo.contact.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import vn.sapo.shared.validation.constraints.NullOrNotBlank;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class BaseContact {

    @NotBlank(message = "{contact.validation.fullName.notBlank}")
    @Length(max = 255, message = "{contact.validation.fullName.length}")
    protected String fullName;

    @NullOrNotBlank
    @Pattern(regexp = "^$|[0-9]{8,15}$", message = "{contact.validation.phoneNumber.pattern}")
    protected String phoneNumber;

    @NullOrNotBlank
    @Pattern(regexp = "^$|[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{contact.validation.email.pattern}")
    protected String email;

    @NullOrNotBlank
    @Pattern(regexp = "^$|[0-9]{8,15}$", message = "{contact.validation.fax.pattern}")
    protected String fax;

    @NullOrNotBlank
    @Length(max = 50, message = "{contact.validation.position.length}")
    protected String position;

    @NullOrNotBlank
    @Length(max = 255, message = "{contact.validation.department.length}")
    protected String department;

    @NullOrNotBlank
    @Length(max = 255, message = "{contact.validation.note.length}")
    protected String note;
}
