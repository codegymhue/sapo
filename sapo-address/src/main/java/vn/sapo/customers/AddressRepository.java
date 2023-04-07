package vn.sapo.customers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.sapo.customers.dto.IAddressResult;
import vn.sapo.entities.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT " +
            "a.id AS id, " +
            "a.phoneNumber AS phoneNumber, " +
            "a.line1 AS line1, " +
            "a.provinceId AS provinceId, " +
            "a.provinceName AS provinceName, " +
            "a.districtId AS districtId, " +
            "a.districtName AS districtName, " +
            "a.wardId AS wardId, " +
            "a.wardName AS wardName, " +
            "a.zipCode AS zipCode, " +
            "a.email AS email " +
            "FROM Address AS a " +
            "WHERE a.customerId = :customerId")
    Page<IAddressResult> findAllByCustomerIdPageable(Pageable pageable, @Param("customerId") Integer customerId);

    @Query(value = "SELECT " +
            "a.line1 " +
            "FROM Address AS a " +
            "WHERE a.id IN :ids"
    )
    List<String> findLine1ByIds(List<Integer> ids);

    @Query(value = "SELECT " +
            "a.id " +
            "FROM Address AS a " +
            "WHERE a.customerId = :customerId"
    )
    List<Integer> findAllAddressIdByCustomerId(@Param("customerId") Integer customerId);

    Integer countAddressesByCustomerId(Integer id);

    void deleteByIdIn(List<Integer> listAddressesId);

    void deleteByCustomerId(Integer customerId);

    List<Address> findAllByCustomerId(Integer customerId);

    List<Address> findAllBySupplierId(Integer supplierId);

    Page<Address> findAllBySupplierId(Pageable pageable, Integer supplierId);

    void deleteAllBySupplierId(Integer id);

}
