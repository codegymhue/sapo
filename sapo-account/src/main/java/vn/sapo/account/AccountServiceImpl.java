package vn.sapo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.account.dto.AccountMapper;
import vn.sapo.account.dto.AccountResult;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper employeeMapper;

    @Autowired
    private AccountRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AccountResult> findAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResult findById(Integer id) {
        return null;
    }

}