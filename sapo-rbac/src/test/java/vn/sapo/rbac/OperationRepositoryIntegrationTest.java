package vn.sapo.rbac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.entities.rbac.RbacOperation;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OperationRepositoryIntegrationTest {
    @Autowired
    RbacOperationRepository rbacRoleRepository;
    List<RbacOperation> rbacOperations;

    @BeforeEach
    public void setUp() {
        rbacOperations = new ArrayList<>() {{
            add((new RbacOperation()
                    .setActive(true)
                    .setTitle("Chủ cửa hàng")));
            add(new RbacOperation()
                    .setActive(true)
                    .setTitle("Nhân viên kho"));
            add(new RbacOperation()
                    .setActive(true)
                    .setTitle("Nhân viên bán hàng"));
            add(new RbacOperation()
                    .setActive(true)
                    .setTitle("Quản lý chi nhánh"));
        }};
    }

    @Test
    public void create() {
        rbacRoleRepository.saveAll(rbacOperations);
    }


}
