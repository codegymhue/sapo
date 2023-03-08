package vn.sapo.entities.voucher;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Doi tuong luu tru la:khach hang, nhan vien, nha cung cap
 * Phieu thu danh cho ai
 */
@Getter
@Setter
@Accessors(chain = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class VoucherObject {

    @NotNull
    @JsonProperty("object_id")
    //id nguoi nop/thu
    private Integer objectId;

    @NotBlank
    @JsonProperty("object_code")
    //code nguoi nop/thu
    private String objectCode;
    @NotBlank
    @JsonProperty("object_name")
    //ten nguoi nop/thu
    private String objectName;

    @NotBlank
    @JsonProperty("object_type")
    //Nhom nguoi nop/thu
    private String objectType;
}
