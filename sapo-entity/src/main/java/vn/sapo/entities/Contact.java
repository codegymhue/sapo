package vn.sapo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class Contact implements Serializable {
    private static final long serialVersionUID = -6245815162176279999L;
    private Long id;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("email")
    private String email;
    private String fax;
    private String position;
    private String department;
    private String note;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}
