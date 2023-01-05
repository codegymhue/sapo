package vn.sapo.tax;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.tax.Tax;
import vn.sapo.tax.dto.CreateTaxParam;
import vn.sapo.tax.dto.TaxResult;

import java.util.List;

@Component
public class TaxMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TaxResult toDTO(Tax tax) {
        return modelMapper.map(tax, TaxResult.class);
    }

    public Tax toModel(CreateTaxParam taxParam) {
        return modelMapper.map(taxParam, Tax.class);
    }

    public List<Tax> toModelList(List<CreateTaxParam> taxListParam) {
        return null;
    }
}
