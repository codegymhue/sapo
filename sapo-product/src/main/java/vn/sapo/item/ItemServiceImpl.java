package vn.sapo.item;

import vn.sapo.entities.product.Item;
import vn.sapo.item.dto.*;
import vn.sapo.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

//    @Autowired
//    private ProductMapper productMapper;

    @Override
    public List<ItemResult> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public int getTotalInventoryQuantityByProductId(Integer productId) {
        return itemRepository.getTotalInventoryQuantityByProductId(productId).orElse(0);
    }

    @Override
    @Transactional(readOnly = true)
    public int getAvailableInventoryQuantityByProductId(Integer productId) {
        return itemRepository.getAvailableInventoryQuantityByProductId(productId).orElse(0);
    }

    @Override
    @Transactional(readOnly = true)
    public int getTradingQuantityByProductId(Integer productId) {
        return itemRepository.getTradingQuantityByProductId(productId).orElse(0);
    }

    @Override
    public ItemResult create(CreateItemParam createItemParam) {
        return itemMapper.toDTO(itemRepository.save(itemMapper.toModel(createItemParam)));
    }

    @Override
    public Optional <Item> findByProductId(Integer productId) {
        return itemRepository.findByProductId(productId);
    }

}
