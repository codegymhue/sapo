package vn.sapo.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sapo.entities.media.Media;
import vn.sapo.entities.product.Product;
import vn.sapo.media.dto.MediaParam;
import vn.sapo.media.dto.MediaResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageServiceImpl implements MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public List<MediaResult> findAllById(Integer productId) {
        List<MediaResult> mediaResults;
        mediaResults = mediaRepository.findAllById(productId).stream().map(mediaMapper::toDTO).collect(Collectors.toList());
        return mediaResults;
    }

    @Override
    @Transactional
    public List<MediaResult> save(List<MediaParam> mediaParam, Product productSaved) {
        List<Media> medias = mediaParam.stream().map(mediaMapper::toModel).collect(Collectors.toList());
        List<MediaResult> dtoList = new ArrayList<>();
        for (int i = 0; i < medias.size(); i++) {
            Media media = medias.get(i);
            media.setMain(i == 0);
            media.setProductId(productSaved.getId());
            media.setProduct(productSaved);
            Media mediaSaved = mediaRepository.save(media);
            dtoList.add(mediaMapper.toDTO(mediaSaved));
        }
        return dtoList;
    }

    @Override
    public List<MediaResult> save(List<MediaParam> mediaParam, Integer idProduct) {
        return null;
    }

    @Override
    public String getLinkMediaByProductIdIsMain(Integer productId) {
        return mediaRepository.getLinkMediaByProductIdIsMain(productId).orElse("");
    }

    @Override
    public void deleteAllByProductId(Integer productId) {
        mediaRepository.deleteAllByProductId(productId);
    }
}
