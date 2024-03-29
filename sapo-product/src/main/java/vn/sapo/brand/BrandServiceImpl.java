package vn.sapo.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.brand.dto.BrandResult;
import vn.sapo.brand.dto.CreateBrandParam;
import vn.sapo.entities.product.Brand;
import vn.sapo.shared.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public BrandResult create(CreateBrandParam brandParam) {
        if(brandRepository.findByTitle(brandParam.getTitle().trim()).isPresent()) {
            throw new ValidationException("Tên nhãn hiệu đã tồn tại. Vui lòng kiểm tra!!!");
        }
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