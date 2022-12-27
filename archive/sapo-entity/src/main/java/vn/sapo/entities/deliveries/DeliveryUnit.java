//package vn.sapo.entities.deliveries;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
//import vn.fx.qh.sapo.entities.employee.Employee;
//import vn.fx.qh.sapo.entities.payment.PartnerType;
//
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@Accessors(chain = true)
//@Entity
//@Table(name = "delivery_unit")
//public class DeliveryUnit {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Integer id;
//
//    @Column(name = "delivery_code", nullable = false, length = 50)
//    private String deliveryCode;
//
//    @Column(name = "name", nullable = false, length = 50)
//    private String name;
//
//    @Column(name = "phone", nullable = false, length = 50)
//    private String phone;
//
//    @Column(name = "email", nullable = false, length = 50)
//    private String email;
//
//    @Column(name = "address", nullable = false, length = 50)
//    private String address;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "employee_id", nullable = false)
//
//    private Employee employee;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "partner_type_id", nullable = false)
//    private PartnerType partnerType;
//
//    public DeliveryUnit(Integer integer) {
//    }
//
//}