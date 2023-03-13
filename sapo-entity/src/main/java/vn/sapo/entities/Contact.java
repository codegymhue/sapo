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
public class Contact {
    private Integer id;
    private Integer accountId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String fax;
    private String position;
    private String department;
    private String note;
    private String status;


}
