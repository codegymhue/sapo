package vn.sapo.entities.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;


@Getter
@Setter
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TaxConfig {
    private String defaultSalesTax;
    private String defaultPurchaseTax;
    private boolean taxInclusive;
}
