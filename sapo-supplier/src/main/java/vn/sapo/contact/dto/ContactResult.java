package vn.sapo.contact.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ContactResult extends BaseContact {
    private Instant createdAt;
    private Instant updatedAt;
}
