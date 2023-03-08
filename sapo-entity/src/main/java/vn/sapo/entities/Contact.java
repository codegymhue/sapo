package vn.sapo.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Contact {
    @Setter(AccessLevel.NONE)
    private Long id = System.currentTimeMillis();
    private String fullName;
    private String phoneNumber;
    private String email;
    private String fax;
    private String position;
    private String department;
}
