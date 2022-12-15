package vn.sapo.media.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Getter
@Setter
@Accessors(chain = true)
public class MediaResult {

    private String cloudId;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
    private String fileType;
    private Boolean isMain;
    private Instant createdAt;
    private Instant updatedAt;
    private Integer productId;
}
