package vn.sapo.voucher.shared.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateVoucherParam {
    private Integer id;
    private String voucherCode;
    private BigDecimal amount;
    private Integer objectId;
    private Integer objectType;
    private Integer groupId;
    private Integer documentId;
    private String reference;
    private String note;
    private Integer paymentMethodId;
    //Thay đổi công nợ đối tượng nộp/thu
    private boolean counted;
    //Hạch toán kết quả kinh doanh
    private boolean isDebt;
    private List<String> tags;

}
