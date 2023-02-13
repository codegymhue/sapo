package vn.sapo.account.dto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.sapo.entities.Account;

@Component
public class AccountMapper {
    @Autowired
    private ModelMapper modelMapper;

    public AccountResult toDTO(Account account) {
        return modelMapper.map(account, AccountResult.class);
    }

    public Account toModel(CreateAccountParam AccountParam) {
        return null;
    }

}