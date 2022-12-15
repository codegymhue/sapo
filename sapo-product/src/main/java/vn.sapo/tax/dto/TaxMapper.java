package vn.sapo.tax.dto;

import org.springframework.stereotype.Component;
import vn.sapo.entities.tax.Tax;

@Component
public class TaxMapper {
    public TaxResult toDTO(Tax tax) {
        return new TaxResult()
                .setId(tax.getId())
                .setTax(tax.getTax())
                .setTitle(tax.getTitle())
                .setCode(tax.getCode());
    }

    public Tax toModel(TaxParam taxParam) {
        return new Tax()
                .setId(taxParam.getId())
                .setCode(taxParam.getCode())
                .setTitle(taxParam.getTitle())
                .setTax(taxParam.getTax());
    }
}
