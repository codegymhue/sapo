package vn.sapo.voucher.shared.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class VoucherResult {
    private Integer id;
    private String voucherCode;
    private BigDecimal amount;
    private String status;
    private String type;
    private String reference;
    private String note;
    //Thay đổi công nợ đối tượng nộp
    private boolean counted;
    //Hạch toán kết quả kinh doanh
    private boolean isDebt;
    private VouObjectResult object;
    private VouDocumentResult document;
    private VouSourceResult source;
    private VouPaymentMethodResult paymentMethod;
    private Integer accountId;
    private Instant issuedAt;
    private Instant createdAt;
    private Instant updatedAt;
}
