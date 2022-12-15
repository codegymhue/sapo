package vn.sapo.brand;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.brand.dto.*;
import vn.sapo.entities.product.Brand;

@Component
public class BrandMapper {
    @Autowired
    private ModelMapper modelMapper;

    public BrandResult toDTO(Brand brand) {
        return modelMapper.map(brand, BrandResult.class);
    }

    public Brand toModel(CreateBrandParam createBrandParam) {
        return modelMapper.map(createBrandParam, Brand.class);
    }
}