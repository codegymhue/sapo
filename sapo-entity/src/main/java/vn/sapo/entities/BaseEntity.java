package vn.sapo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -6305026811763181957L;
    public static final String DATETIME_DEFAULT_CURRENT_TIMESTAMP = "DATETIME DEFAULT CURRENT_TIMESTAMP";
    @Setter(AccessLevel.NONE)
    @Column(name = "created_at",
            nullable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP"
    )
    private Instant createdAt;

    @Setter(AccessLevel.NONE)
    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }
}
