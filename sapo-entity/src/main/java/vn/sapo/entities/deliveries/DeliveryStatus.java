//package vn.sapo.entities.deliveries;
//
//import lombok.*;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@Accessors(chain = true)
//@Entity
//@Table(name = "delivery_status")
//public class DeliveryStatus {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "name", nullable = false, length = 50)
//    private String name;
//
//    public DeliveryStatus(Integer id) {
//        this.id = id;
//
//    }
//}