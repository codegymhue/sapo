package vn.sapo.entities.configuration;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import vn.sapo.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "configuration")
@Entity
@TypeDef(
        typeClass = JsonType.class,
        defaultForType = TaxConfig.class
)
@TypeDef(
        typeClass = JsonType.class,
        defaultForType = PolicyPriceConfig.class
)
@TypeDef(
        name = "json",
        typeClass = JsonType.class)
public class Configuration extends BaseEntity {
    @Id
    @Column(name = "app_key")
    private String appKey;
    @Column(name = "tax", nullable = false, columnDefinition = "JSON")
    private TaxConfig tax;
    @Column(name = "policy_price", nullable = false, columnDefinition = "JSON")
    private PolicyPriceConfig policyPrice;

    @Override
    public void prePersist() {
        super.prePersist();
        appKey = UUID.randomUUID().toString();
    }
}
