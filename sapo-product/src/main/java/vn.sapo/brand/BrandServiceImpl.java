package vn.sapo.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.brand.dto.*;
import vn.sapo.entities.product.Brand;

import java.util.*;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandResult create(CreateBrandParam brandParam) {
        Brand brand = brandRepository.save(brandMapper.toModel(brandParam));
        return brandMapper.toDTO(brand);
    }

    @Override
    public List<BrandResult> findAll() {
        List<Brand> brands = brandRepository.findAll();
        List<BrandResult> brandResults = new ArrayList<>();
        for(Brand brand : brands){
            brandResults.add(brandMapper.toDTO(brand));
        }
        return brandResults;
    }
}