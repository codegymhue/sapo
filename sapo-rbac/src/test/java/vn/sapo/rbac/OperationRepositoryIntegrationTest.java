package vn.sapo.rbac;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import vn.sapo.entities.rbac.RbacOperation;
import vn.sapo.entities.rbac.RbacRole;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OperationRepositoryIntegrationTest {
    @Autowired
    RbacOperationRepository rbacOperationRepository;
    List<RbacOperation> rbacOperations;

    @BeforeEach
    public void setUp() {
        rbacOperations = new ArrayList<>() {{
            add((new RbacOperation()
                    .setOperationCode("PRODUCT")
                    .setActive(true)
                    .setTitle("Sản phẩm")));
            add(new RbacOperation()
                    .setOperationCode("DAT_HANG_NHAP")
                    .setActive(true)
                    .setTitle("Đặt hàng nhập"));
            add(new RbacOperation()
                    .setOperationCode("NHAP_HANG")
                    .setActive(true)
                    .setTitle("Nhập hàng"));
            add(new RbacOperation()
                    .setOperationCode("CHUYEN_HANG")
                    .setActive(true)
                    .setTitle("Chuyển hàng"));
            add(new RbacOperation()
                    .setOperationCode("KIEM_HANG")
                    .setActive(true)
                    .setTitle("Kiểm hàng"));
            add(new RbacOperation()
                    .setOperationCode("DIEU_CHINH_GIA_VON")
                    .setActive(true)
                    .setTitle("Điều chỉnh giá vốn"));
            add(new RbacOperation()
                    .setOperationCode("SUPPLIER")
                    .setActive(true)
                    .setTitle("Nhà cung cấp"));
            add(new RbacOperation()
                    .setOperationCode("SALE_ORDER")
                    .setActive(true)
                    .setTitle("Đơn hàng"));
            add(new RbacOperation()
                    .setOperationCode("BAO_CAO_CUOI_NGAY")
                    .setActive(true)
                    .setTitle("Báo cáo bán hàng cuối ngày"));
            add(new RbacOperation()
                    .setOperationCode("DON_TRA_HANG")
                    .setActive(true)
                    .setTitle("Đơn trả hàng (khách trả hàng)"));
            add(new RbacOperation()
                    .setOperationCode("DOI_SOAT_VAN_CHUYEN")
                    .setActive(true)
                    .setTitle("Đối soát vận chuyển"));
            add(new RbacOperation()
                    .setOperationCode("DOI_TAC_VAN_CHUYEN_&_GIAO_HANG")
                    .setActive(true)
                    .setTitle("Đối tác vận chuyển và giao hàng"));
            add(new RbacOperation()
                    .setOperationCode("CUSTOMER")
                    .setActive(true)
                    .setTitle("Khách hàng"));
            add(new RbacOperation()
                    .setOperationCode("CHINH_SACH_BAO_HANH_&_CAU_HINH")
                    .setActive(true)
                    .setTitle("Chính sách bảo hành và cấu hình"));
            add(new RbacOperation()
                    .setOperationCode("PHIEU_BAO_HANH")
                    .setActive(true)
                    .setTitle("Phiếu bảo hành"));
            add(new RbacOperation()
                    .setOperationCode("PHIEU_YEU_CAU_HANH")
                    .setActive(true)
                    .setTitle("Phiếu yêu cầu bảo hành"));
            add(new RbacOperation()
                    .setOperationCode("PHIEU_THU")
                    .setActive(true)
                    .setTitle("Phiếu thu"));
            add(new RbacOperation()
                    .setOperationCode("PHIEU_CHI")
                    .setActive(true)
                    .setTitle("Phiếu chi"));
            add(new RbacOperation()
                    .setOperationCode("REPORT")
                    .setActive(true)
                    .setTitle("Báo cáo"));
            add(new RbacOperation()
                    .setOperationCode("CONFIGURATION_&_APPLICATION")
                    .setActive(true)
                    .setTitle("Cấu hình và ứng dụng"));
            add(new RbacOperation()
                    .setOperationCode("KENH_BAN_HANG")
                    .setActive(true)
                    .setTitle("Kênh bán hàng"));
            add(new RbacOperation()
                    .setOperationCode("PROMOTION")
                    .setActive(true)
                    .setTitle("Khuyến mãi"));
            add(new RbacOperation()
                    .setOperationCode("TICH_DIEM")
                    .setActive(true)
                    .setTitle("Tích điểm"));
            add(new RbacOperation()
                    .setOperationCode("HOA_DON_DIEN_TU")
                    .setActive(true)
                    .setTitle("Hóa đơn điện tử"));

        }};
    }


    @Test
    public void create() {
        rbacOperationRepository.saveAll(rbacOperations);
    }

    @Test
    @Transactional
    public void findAll() {
        List<RbacOperation> operations = rbacOperationRepository.findAll();
        System.out.println("s");
    }

}
