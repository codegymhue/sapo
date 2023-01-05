package vn.sapo.configuration.dto;

import lombok.Getter;
import lombok.Setter;
import vn.sapo.entities.configuration.PolicyPriceConfig;
import vn.sapo.entities.configuration.TaxConfig;

@Getter
@Setter
public class ConfigurationResult {
    private String appKey;
    private TaxConfig tax;
    private PolicyPriceConfig policyPrice;
}
