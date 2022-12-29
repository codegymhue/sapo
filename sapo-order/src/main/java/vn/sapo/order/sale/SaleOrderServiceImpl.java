package vn.sapo.order.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.customer.CustomerService;
import vn.sapo.entities.order.OrderStatusCode;
import vn.sapo.entities.order.sale.SaleOrder;
import vn.sapo.entities.order.sale.SaleOrderItem;
import vn.sapo.entities.product.Item;
import vn.sapo.entities.product.Product;
import vn.sapo.entities.tax.ProductTax;
import vn.sapo.entities.tax.TaxType;
import vn.sapo.shared.exceptions.NotEnoughQuantityException;
import vn.sapo.shared.exceptions.NotFoundException;
import vn.sapo.item.ItemRepository;
import vn.sapo.order.sale.dto.*;
import vn.sapo.order.sale.item.SaleOrderItemRepository;
import vn.sapo.product.ProductRepository;
import vn.sapo.product_tax.ProductTaxRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class SaleOrderServiceImpl implements SaleOrderService {

    @Autowired
    private SaleOrderRepository saleOrderRepository;

    @Autowired
    private SaleOrderItemRepository orderItemRepository;

    @Autowired
    SaleOrderMapper orderMapper;

    @Autowired
    SaleOrderItemMapper orderItemMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private EmployeeRepository employeeRepository;


    @Autowired
    private CustomerService customerService;


    @Autowired
    private ProductTaxRepository productTaxRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SaleOrderResult> findAll() {
        return saleOrderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SaleOrderResult create(CreateSaleOrderParam orderParam) {
        Integer customerId = orderParam.getCustomerId();
        if (customerId == null && !customerService.existsById(customerId))
            throw new NotFoundException("Không tìm thấy khách hàng");


        SaleOrder order = orderMapper.toModel(orderParam);
        order.setGrandTotal(new BigDecimal(0));
        order.setTotal(new BigDecimal(0));

        order.setSubTotal(new BigDecimal(0));
        order.setLine1(order.getLine1());
        order.setLine2(order.getLine2());
        order.setCustomerId(customerId);
        order.setEmployeeId(1);
        order.setOrderStatus(OrderStatusCode.CHECKOUT);
        order.setPaymentStatus(OrderStatusCode.UNPAID);
        order.setOrderCode("SON" + order.getId());
        order = saleOrderRepository.save(order);

        BigDecimal total = new BigDecimal(0);
        BigDecimal subTotal = new BigDecimal(0);
        BigDecimal grandTotal = new BigDecimal(0);
        for (CreateSaleOrderItemParam saleOrderItemParam : orderParam.getSaleOrderItems()) {

            Product product = productRepository.findById(saleOrderItemParam.getProductId())
                    .orElseThrow(
                            () -> new NotFoundException("Không tìm thấy Id sản phẩm " + saleOrderItemParam.getProductId())
                    );

            //lấy toàn bộ item theo productId

            List<Item> items = itemRepository.findAllByProductIdAndAvailableGreaterThanOrderByCreatedAt(saleOrderItemParam.getProductId(), 0);
            int totalAvailable = items.stream().mapToInt(Item::getAvailable).sum();
            if (totalAvailable < saleOrderItemParam.getQuantity()) {
                throw new NotEnoughQuantityException("Không đủ số lượng cho đơn hàng, vui lòng kiểm tra lại !");
            }
            Integer productId = product.getId();
            BigDecimal retailPrice = product.getRetailPrice();

            int quantityCustomerOrder = saleOrderItemParam.getQuantity();
            BigDecimal orderItemTotal = retailPrice.multiply(new BigDecimal(quantityCustomerOrder));

            SaleOrderItem saleOrderItem = new SaleOrderItem();
            if (product.isApplyTax()) {
                List<ProductTax> productTaxList = productTaxRepository.findAllByProductIdAndTaxType(productId, TaxType.TAX_SALE);
                float taxTotal = (float) productTaxList.stream()
                        .mapToDouble(productTax -> productTax.getTax().getTax()).sum();
                saleOrderItem.setTax(taxTotal);
                BigDecimal amountTax = (orderItemTotal.multiply(BigDecimal.valueOf(taxTotal / 100)));
                orderItemTotal = orderItemTotal.add(amountTax);
            }


            if (saleOrderItem.getDiscount() != null) {
                orderItemTotal = orderItemTotal.subtract(orderParam.getDiscount());
            }

            subTotal = subTotal.add(orderItemTotal);

            for (Item item : items) {
                if (quantityCustomerOrder == 0) {
                    break;
                }
                int available = item.getAvailable();
                int orderItemSold;
                if (quantityCustomerOrder >= available) {
                    quantityCustomerOrder = quantityCustomerOrder - available;
                    item.setAvailable(0);
                    orderItemSold = available;
                    int itemSold = item.getSold() + available;
                    item.setSold(itemSold);
                } else {
                    available = available - quantityCustomerOrder;
                    item.setAvailable(available);
                    orderItemSold = quantityCustomerOrder;
                    int itemSold = item.getSold() + quantityCustomerOrder;
                    item.setSold(itemSold);
                    quantityCustomerOrder = 0;
                }

                saleOrderItem.setQuantity(orderItemSold);
                saleOrderItem.setPrice(retailPrice);
                saleOrderItem.setProductId(productId);
                saleOrderItem.setItemId(item.getId());
                saleOrderItem.setOrderId(order.getId());
                saleOrderItem.setDiscount(saleOrderItemParam.getDiscount());
                orderItemRepository.save(saleOrderItem);
            }
        }
        order.setSubTotal(subTotal);
        //cong them phi giao hang
        total = subTotal.add(BigDecimal.valueOf(0));
        order.setTotal(total);
        if (order.getDiscount() != null) {
            grandTotal = total.subtract(order.getDiscount());
        } else {
            grandTotal = total;
        }
        //tru ma giam gia
        order.setGrandTotal(grandTotal);
        return orderMapper.toDTO(order);


    }

    @Override
    @Transactional
    public SaleOrderResult findById(int id) {
        Optional<SaleOrder> optional = saleOrderRepository.findById(id);
        if (!optional.isPresent())
            throw new NotFoundException("Đơn hàng không hợp lệ!");
        SaleOrderResult result = orderMapper.toDTO(optional.get());
        List<SaleOrderItemResult> orderItems = orderItemRepository.findAllByOrderId(result.getId())
                .stream()
                .map(orderItemMapper::toDTO)
                .collect(Collectors.toList());
        result.setOrderItems(orderItems);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getSpendTotalByCustomerId(Integer customerId) {
        return saleOrderRepository.getSpendTotalByCustomerId(customerId);
    }

    @Override
    public Integer getQuantityProductOrder(Integer id) {
        return saleOrderRepository.getQuantityProductOrderById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleOrderResult> findAllSaleOrderByCustomerId(Integer customerId) {
        return saleOrderRepository.findAllCustomerOrderHistory(customerId).stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Instant getLastDayOrderByCustomerId(Integer customerId) {
        return saleOrderRepository.getLastDayOrderByCustomerId(customerId);
    }


}
