package vn.sapo.entities.voucher;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Chứng từ cho phiếu thu/chi: là id & code của đơn đặt hàng hoặc đơn nhập hàng.
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VoucherDocument {
    @JsonProperty("document_root_id")
    private Integer documentRootId;
    @JsonProperty("document_root_code")
    private String documentRootCode;
}
