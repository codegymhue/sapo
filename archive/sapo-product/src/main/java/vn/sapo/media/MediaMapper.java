package vn.sapo.media;

import org.springframework.stereotype.Component;
import vn.sapo.entities.media.Media;
import vn.sapo.media.dto.MediaParam;
import vn.sapo.media.dto.MediaResult;

@Component
public class MediaMapper {

    public Media toModel(MediaParam productImageParam) {
        return new Media()
                .setCloudId(productImageParam.getCloudId())
                .setFileName(productImageParam.getFileName())
                .setFileFolder(productImageParam.getFileFolder())
                .setFileUrl(productImageParam.getFileUrl())
                .setFileType(productImageParam.getFileType());
    }

    public MediaResult toDTO(Media media) {
        return new MediaResult()
                .setCloudId(media.getCloudId())
                .setFileName(media.getFileName())
                .setFileFolder(media.getFileFolder())
                .setFileUrl(media.getFileUrl())
                .setFileType(media.getFileType())
                .setIsMain(media.isMain())
                .setProductId(media.getProductId());
    }
}
