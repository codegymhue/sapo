package vn.sapo.item;

import org.springframework.stereotype.*;
import vn.sapo.entities.product.Item;
import vn.sapo.item.dto.*;
import vn.sapo.product.dto.*;

@Component
public class ItemMapper {


    public Item toModel(ItemParam itemParam) {
        return new Item()
                .setId(itemParam.getId())
                .setProductId(itemParam.getProductId())
                .setSupplierId(itemParam.getSupplierId())
                .setEmployeeId(itemParam.getEmployeeId())
                .setPurchaseOrderId(itemParam.getPurchaseOrderId())
                .setQuantity(itemParam.getQuantity());
    }

    public Item toModel(CreateItemParam createItemParam) {
        return new Item()
                .setQuantity(createItemParam.getQuantity())
                .setEmployeeId(createItemParam.getEmployeeId())
                .setProductId(createItemParam.getProductId())
                .setPrice(createItemParam.getPrice())
                .setAvailable(createItemParam.getAvailable())
                .setSold(0)
                .setDefective(0)
                .setTrading(0);
    }

    public CreateItemParam toDTO(CreateProductParam createProductParam, Integer productId, Integer employeeId) {
        return new CreateItemParam()
                .setQuantity(createProductParam.getQuantity())
                .setProductId(productId)
                .setEmployeeId(employeeId)
                .setPrice(createProductParam.getCostPrice())
                .setAvailable(createProductParam.getQuantity());
    }

    public ItemResult toDTO(Item item) {
        return new ItemResult()
                .setId(item.getId())
                .setProductId(item.getProductId())
                .setEmployeeId(item.getEmployeeId())
                .setSupplierId(item.getSupplierId())
                .setPurchaseOrderId(item.getPurchaseOrderId())
                .setQuantity(item.getQuantity())
                .setPrice(item.getPrice())
                .setAvailable(item.getAvailable())
                .setTrading(item.getTrading());

    }

}
