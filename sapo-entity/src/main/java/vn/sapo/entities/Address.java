package vn.sapo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import vn.sapo.entities.customer.Customer;
import vn.sapo.entities.supplier.Supplier;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", length = 50)
    private String fullName;

    @Column(name = "label", length = 50)
    private String label;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "line1", length = 50, nullable = false)
    private String line1;

    @Column(name = "line2", length = 50)
    private String line2;

    @Column(name = "district_id")
    private Integer districtId;

    @Column(name = "district_name", length = 50)
    private String districtName;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(name = "province_name", length = 50)
    private String provinceName;

    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "ward_name", length = 50)
    private String wardName;

    @Column(name = "zip_code", length = 10)
    private String zipCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_address_customer"))
    private Customer customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Integer customerId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id", foreignKey = @ForeignKey(name = "fk_address_supplier"))
    private Supplier supplier;

    @Column(name = "supplier_id", insertable = false, updatable = false)
    private Integer supplierId;

    @Column(name = "is_shipping", nullable = false)
    private boolean isShippingAddress;

    @Column(name = "is_receive_bill", nullable = false)
    private boolean isReceiveBill;

    public Address setCustomerId(Integer customerId) {
        this.customer = new Customer(this.customerId = customerId);
        return this;
    }

    public Address setSupplierId(Integer supplierId) {
        this.supplier = new Supplier(this.supplierId = supplierId);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
