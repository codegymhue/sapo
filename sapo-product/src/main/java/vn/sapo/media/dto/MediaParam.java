package vn.sapo.media.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MediaParam {
    private String cloudId;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
    private String fileType;
}
