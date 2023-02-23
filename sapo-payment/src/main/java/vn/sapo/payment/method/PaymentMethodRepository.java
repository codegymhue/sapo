package vn.sapo.payment.method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.payment.PaymentMethod;
import vn.sapo.payment.method.dto.PaymentMethodResult;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    Optional<PaymentMethod> findByTitle(String title);
}