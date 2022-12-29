package vn.sapo.entities.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PolicyPriceConfig {
    private FixPriceSellingType fixPriceSellingType;
    private String defaultSalesPrice;
    private String defaultPurchasePrice;
    private boolean suggestLatestSellingPrice;
}
