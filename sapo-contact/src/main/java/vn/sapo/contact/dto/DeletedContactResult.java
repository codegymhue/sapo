package vn.sapo.contact.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
@Accessors(chain = true)
public class DeletedContactResult {

    private Integer numberOfSuccess = 0;

    private Integer numberOfFail = 0;

    private Map<String, String> message;

    private List<String> namesDeleted;

    private Set<Long> idsDeleted;

    private Integer numberOfContactsLeft;
}
