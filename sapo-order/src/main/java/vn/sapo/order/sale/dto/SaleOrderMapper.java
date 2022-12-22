package vn.sapo.order.sale.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.order.sale.SaleOrder;

@Component
public class SaleOrderMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SaleOrderResult toDTO(SaleOrder saleOrder) {
        return modelMapper.map(saleOrder, SaleOrderResult.class);
    }

    public SaleOrder toModel(CreateSaleOrderParam createSaleOrderParam) {
        return modelMapper.map(createSaleOrderParam, SaleOrder.class);

    }
}


