package vn.sapo.entities.media;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.*;
import vn.sapo.entities.product.variation.ProductVariation;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "media")
@Accessors(chain = true)
public class Media {
    @Id
    @Column(name = "cloud_id", nullable = false)
    private String cloudId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_folder")
    private String fileFolder;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "is_main", nullable = false)
    private boolean isMain;

    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;

    @Column(name = "variation_id", insertable = false, updatable = false)
    private Integer variationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name="fk_media_product"))
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id", nullable = false, foreignKey = @ForeignKey(name="fk_media_product_variation"))
    private ProductVariation productVariation;

    public Media(Integer productId) {
        this.product = new Product(this.productId = productId);
    }

}
