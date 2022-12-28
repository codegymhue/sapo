package vn.sapo.order.sale;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.order.sale.SaleOrderItem;
import vn.sapo.order.sale.dto.CreateSaleOrderItemParam;
import vn.sapo.order.sale.dto.SaleOrderItemResult;

@Component
public class SaleOrderItemMapper {
    @Autowired
    ModelMapper modelMapper;

    public SaleOrderItemResult toDTO(SaleOrderItem orderItem) {
        return modelMapper.map(orderItem, SaleOrderItemResult.class);
    }

    public SaleOrderItem toModel(CreateSaleOrderItemParam orderItemParam) {
        return modelMapper.map(orderItemParam, SaleOrderItem.class);
    }
}
