package vn.sapo.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.sapo.entities.Account;
import vn.sapo.entities.Employee;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}