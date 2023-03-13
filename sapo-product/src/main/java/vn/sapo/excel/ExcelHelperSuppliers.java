package vn.sapo.excel;

import org.springframework.web.multipart.MultipartFile;

public class ExcelHelperSuppliers {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    static String[] HEADER = {"Tên nhà cung cấp", "Mã nhà cung cấp", "Mã nhóm nhà cung cấp", "Email", "Điện thoại", "Website", "FAX", "Mã số thuế", "Mô tả", "Chính sách giá mặc định", "Kỳ hạn thanh toán mặc định", "Phương thức thanh toán mặc định", "Người liên hệ", "SDT người liên hệ", "Email người liên hệ", "Nhãn", "Địa chỉ 1", "Địa chỉ 2", "Tỉnh/Thành Phố", "Quận/Huyện", "Nợ hiện tại", "Tags"};

    static String SHEET = "DuLieuNhap";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }


}