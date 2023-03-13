//package vn.sapo.supplier;
//
//import vn.sapo.shared.validation.constraints.NullOrNotBlank;
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//
//public class SupplierForm {
//    @NotBlank(message = "Vui lòng nhập tên nhà cung cấp")
//    private String nameCreate;
//
//    @Pattern(regexp = "^0\\d{9,10}$", message = "Số điện thoại phải bắt đầu bằng số 0 và có độ dài từ 10 đến 11 số")
//    private String phoneCreate;
//
//    @NullOrNotBlank(message = "Vui lòng nhập địa chỉ email")
//    @Email(message = "Định dạng địa chỉ email không hợp lệ")
//    private String emailCreate;
//
//    private String faxCreate;
//
//    // getter và setter
//}
//
