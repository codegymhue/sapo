package vn.sapo.payment.method;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.PaymentMethod;

import java.util.*;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
    Optional<PaymentMethod> findByTitle(String title);

    List<PaymentMethod> findByTitleIn(Set<String> titles);
}