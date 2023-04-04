package vn.sapo.customers.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@Accessors(chain = true)
public class DeleteAddressResult {

    private Integer numberOfSuccess = 0;

    private Integer numberOfFail = 0;

    private Map<String, String> message;

    private AddressResult addressResult;

    private List<String> namesDeleted;

    private List<Integer> idsDeleted;

}
