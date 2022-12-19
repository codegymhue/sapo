package vn.sapo.brand;

import vn.sapo.brand.dto.*;

import java.util.List;

public interface BrandService {
    BrandResult create (CreateBrandParam brandParam);
    List<BrandResult>findAll();
}
