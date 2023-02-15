package vn.sapo.rbac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.entities.rbac.RbacRole;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RoleRepositoryIntegrationTest {
    @Autowired
    RbacRoleRepository rbacRoleRepository;
    List<RbacRole> rbacRoles;

    @BeforeEach
    public void setUp() {
        rbacRoles = new ArrayList<>() {{
            add((new RbacRole()
                    .setActive(true)
                    .setTitle("Chủ cửa hàng")));
            add(new RbacRole()
                    .setActive(true)
                    .setTitle("Nhân viên kho"));
            add(new RbacRole()
                    .setActive(true)
                    .setTitle("Nhân viên bán hàng"));
            add(new RbacRole()
                    .setActive(true)
                    .setTitle("Quản lý chi nhánh"));
        }};
    }

    @Test
    public void create() {
        rbacRoleRepository.saveAll(rbacRoles);
    }


}
