package sapo.address;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.sapo.account.AccountRepository;
import vn.sapo.account.AccountService;
import vn.sapo.account.dto.AccountMapper;
import vn.sapo.account.dto.AccountResult;
import vn.sapo.entities.Account;
import vn.sapo.shared.exceptions.NotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@Import(AccountConfigurationTest.class)
public class AccountServiceTest {

    @MockBean
    AccountRepository addressRepository;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;

    @BeforeAll
    public static void setUp() {

    }

    @BeforeEach
    public void init() {

    }

    @Test
    public void testFindAll() {
        Assertions.assertThat(accountService.findAll()).hasSize(5);
        try {
            accountService.findAll().isEmpty();
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "find all not possible");
        }
    }

    @Test
    public void testFindById() {
    }

    @Test
    public void testNotFindById() {
        try {
            accountService.findById(3);
        } catch (Exception e) {
            assertThat(e, instanceOf(NotFoundException.class));
            assertEquals(e.getMessage(), "address not found");
        }
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testCreate() {

    }

    public void assertAccount(AccountResult actual, Account expected) {


    }


}
