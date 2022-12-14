package vn.sapo.entities.media;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "media")
@Accessors(chain = true)
public class Media {
    @Id
    @Column(name = "cloud_id")
    private String cloudId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_folder")
    private String fileFolder;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "is_main")
    private Boolean isMain;


    @Column(name = "created_by")
    private Integer creattedBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Media(Product product) {
        setProduct(product);
    }

}
