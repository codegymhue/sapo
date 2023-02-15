package vn.sapo.account;


import vn.sapo.account.dto.AccountResult;

import java.util.List;

public interface AccountService {

    List<AccountResult> findAll();

    AccountResult findById(Integer id);

}