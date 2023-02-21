package vn.sapo.rbac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.entities.rbac.RbacModule;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ModuleRepositoryIntegrationTest {
    @Autowired
    RbacModuleRepository rbacModuleRepository;
    List<RbacModule> rbacModules;

    @BeforeEach
    public void setUp() {
        rbacModules = new ArrayList<>() {{
            add((new RbacModule()
                    .setModuleCode("PRODUCT")
                    .setActive(true)
                    .setTitle("Sản phẩm")));
            add(new RbacModule()
                    .setModuleCode("ORDER")
                    .setActive(true)
                    .setTitle("Đơn hàng"));
            add(new RbacModule()
                    .setModuleCode("SUPPLIER")
                    .setActive(true)
                    .setTitle("Nhà cung cấp"));
            add(new RbacModule()
                    .setModuleCode("CUSTOMER")
                    .setActive(true)
                    .setTitle("Khách hàng"));
        }};
    }

    @Test
    public void create() {
        rbacModuleRepository.saveAll(rbacModules);
    }


}
