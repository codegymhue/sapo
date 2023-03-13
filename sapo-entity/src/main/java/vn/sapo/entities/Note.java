package vn.sapo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Note {
    private Integer id;
    private Integer accountId;
    private String content;
    private String status;


}
