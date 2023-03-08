package vn.sapo.entities.voucher;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VoucherSource {
    @NotNull
    @JsonProperty("source_id")
    private Integer sourceId;
    @NotBlank
    @JsonProperty("source_type")
    private String sourceType;
}
