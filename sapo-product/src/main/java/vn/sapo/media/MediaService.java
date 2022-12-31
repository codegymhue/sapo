package vn.sapo.media;

import vn.sapo.entities.product.Product;
import vn.sapo.media.dto.MediaParam;
import vn.sapo.media.dto.MediaResult;

import java.util.List;

public interface MediaService {
    List<MediaResult> findAllById(Integer productId);
    List<MediaResult> save(List<MediaParam> mediaParam, Product productSaved);

    List<MediaResult> save(List<MediaParam> mediaParam, Integer idProduct);
    String getLinkMediaByProductIdIsMain(Integer productId);

    void deleteAllByProductId(Integer productId);
}
