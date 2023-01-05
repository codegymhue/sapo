package vn.sapo.entities.product.attribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.product.Product;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@Table(name = "product_attribute")
public class AttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "attribute_value", nullable = false)
    private String title;

    @Column(name = "attribute_id", insertable = false, updatable = false)
    private Integer attributeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id", nullable = false, foreignKey = @ForeignKey(name="fk_attribute_value_atribute"))
    private Attribute attribute;

    public AttributeValue(Integer id) {
        this.id = id;
    }
}
