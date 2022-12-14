package vn.sapo.address.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressParam {


    @NotBlank(message = "Không được để trông địa chỉ!!")
    @Size(min = 2, max = 200, message = "Dữ liệu địa chỉ không được ngắn hơn 2 kí tự và nhiều hơn 200 kí tự")
    private String line1;

    private String line2;

    @NotBlank(message = "Trường id ward không đươc trống!!")
    private Integer wardId;

    @NotBlank(message = "Địa chỉ phường xa không được trống")
    private String wardName;

    @NotBlank(message = "Không được để trống trường  district")
    private Integer districtId;

    @NotBlank(message = "Trường district bị trống")
    private String districtName;

    @NotBlank(message = "Không được để trống trường  province_id")
    private Integer provinceId;

    @NotBlank(message = "Không được để trống trường  province")
    private String provinceName;

    @NotBlank(message = "Trường customer_ID bị null")
    private Integer customerId;

    @NotBlank(message = "Không được để trống email")
    @Email(message = "Email : đây ko  phải là 1 email")
    @Size(min = 2, max = 30, message = "Dữ liệu email không được ngắn hơn 2 kí tự và nhiều hơn 30 kí tự")
    private String email;

    private String zipCode;

    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(min = 2, max = 200, message = "Dữ liệu tên không được ngắn hơn 2 kí tự và nhiều hơn 200 kí tự")
    private String fullName;

    @NotBlank(message = "KHông được để trống trường mobile")
    @Size(min = 2, max = 30, message = "Dữ liệu số điện thoại không được ngắn hơn 2 kí tự và nhiều hơn 30 kí tự")
    private String mobile;

    private Integer supplierId;

}
