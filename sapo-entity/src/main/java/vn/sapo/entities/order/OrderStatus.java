package vn.sapo.entities.order;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    public OrderStatus(String id) {
        this.id = id;
    }
}