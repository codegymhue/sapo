package vn.sapo.order.sale;

import vn.sapo.order.sale.dto.CreateSaleOrderParam;
import vn.sapo.order.sale.dto.SaleOrderResult;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public interface SaleOrderService {

    List<SaleOrderResult> findAll();

    SaleOrderResult create(CreateSaleOrderParam orderParam);

    SaleOrderResult findById(int id);

    BigDecimal getSpendTotalByCustomerId(Integer customerId);

    Integer getQuantityProductOrder(Integer id);

    List<SaleOrderResult> findAllSaleOrderByCustomerId(Integer customerId);


    Instant getLastDayOrderByCustomerId(Integer customerId);
}
