package vn.sapo.rbac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.sapo.entities.rbac.RbacPermission;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PermissionRepositoryIntegrationTest {
    @Autowired
    RbacPermissionRepository rbacPermissionRepository;
    List<RbacPermission> rbacOperations;

    @BeforeEach
    public void setUp() {
        rbacOperations = new ArrayList<>() {{
            add((new RbacPermission()
                    .setPermissionCode("XEM_SAN_PHAM")
                    .setActive(true)
                    .setTitle("Xem sản phẩm")));
            add((new RbacPermission()
                    .setPermissionCode("TAO_SAN_PHAM")
                    .setActive(true)
                    .setTitle("Tạo sản phẩm")));
            add((new RbacPermission()
                    .setPermissionCode("XEM_DON_DAT_HANG_NHAP")
                    .setActive(true)
                    .setTitle("Xem đơn đặt hàng nhập")));
            add((new RbacPermission()
                    .setPermissionCode("TAO_DON_DAT_HANG_NHAP")
                    .setActive(true)
                    .setTitle("Tạo đơn đặt hàng nhập")));
            add((new RbacPermission()
                    .setPermissionCode("SUA_DON_DAT_HANG_NHAP")
                    .setActive(true)
                    .setTitle("Sửa đơn đặt hàng nhập")));
            add((new RbacPermission()
                    .setPermissionCode("XEM_DON_NHAP")
                    .setActive(true)
                    .setTitle("Xem đơn nhập")));
            add((new RbacPermission()
                    .setPermissionCode("TAO_DON_NHAP")
                    .setActive(true)
                    .setTitle("Tạo đơn nhập")));
            add((new RbacPermission()
                    .setPermissionCode("SUA_DON_NHAP")
                    .setActive(true)
                    .setTitle("Sửa đơn nhập")));
            add((new RbacPermission()
                    .setPermissionCode("XEM_PHIEU_CHUYEN")
                    .setActive(true)
                    .setTitle("Xem phiếu chuyển")));
            add((new RbacPermission()
                    .setPermissionCode("TAO_PHIEU_CHUYEN")
                    .setActive(true)
                    .setTitle("Tạo phiếu chuyển")));
            add((new RbacPermission()
                    .setPermissionCode("SUA_PHIEU_CHUYEN")
                    .setActive(true)
                    .setTitle("Sửa phiếu chuyển")));
            add((new RbacPermission()
                    .setPermissionCode("XEM_PHIEU_KIEM_HANG")
                    .setActive(true)
                    .setTitle("Xem phiếu kiểm hàng")));
            add((new RbacPermission()
                    .setPermissionCode("TAO_PHIEU_KIEM_HANG")
                    .setActive(true)
                    .setTitle("Tạo phiếu kiểm hàng")));
            add((new RbacPermission()
                    .setPermissionCode("SUA_PHIEU_KIEM_HANG")
                    .setActive(true)
                    .setTitle("Sửa phiếu kiểm hàng")));
        }};
    }

    @Test
    public void create() {
        rbacPermissionRepository.saveAll(rbacOperations);
    }


}
