package vn.sapo.contact.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ContactResult extends BaseContact {

    private Long id;

    private Instant createdAt;

    private Instant updatedAt;

    @Override
    public String toString() {
        return "ContactResult{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
