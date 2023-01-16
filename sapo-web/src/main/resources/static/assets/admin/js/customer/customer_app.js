class App {

    static DOMAIN_API = "http://localhost:8080";
    // static BASE_URL_Customer = this.DOMAIN_API + "/api/customers";
    static BASE_URL_CUSTOMER  = this.DOMAIN_API + "/api/customers";
    static BASE_URL_CUSTOMER_GROUP  = this.DOMAIN_API + "/api/customer_groups";

    static URL_CREATE_CUSTOMER = this.BASE_URL_Customer + "/create";
    static URL_UPDATE_CUSTOMER = this.BASE_URL_Customer  + "/update";

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_FILTERED = "Lưu bộ lọc thành công !";
        static SUCCESS_DELETE = "Xóa dữ liệu thành công";
        static ERROR_NAME = "Thao tác không thành công, Tên sản phẩm không được để trống";
        static ERROR_TITLE = "Thao tác không thành công, Tên sản phẩm không được để trống.";
        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

    }


    static SweetAlert = class {
        static showSuccessAlert(t) {
            Swal.fire({
                icon: 'success',
                title: t,
                position: 'top-end',
                showConfirmButton: false,
                timer: 1500
            })
        }


        static showErrorAlert(t) {
            Swal.fire({
                icon: 'error',
                title: 'Warning',
                text: t,
            })
        }

    }
    static IziToast = class  {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                timeout: 2500,
                message: m,
            });
        }
        static showFilter(m) {
            iziToast.success({
                title: 'lưu bộ lọc thành công',
                position: 'topRight',
                timeout: 2500,
                message: m,
            });
        }

        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m,
            });
        }
    }

    static renderRowCustomer(item) {
        let str = `
             <tr id="tr_${item.id}" >
                <td class="align-middle">
                    <label for="${item.id}" style="padding: 0.5rem; cursor:pointer">
                        <input style="cursor:pointer" id ="${item.id}" class="selectCheckbox"  name="options[]" type="checkbox" value="${item.id}">
                    </label>
                </td>

                <td class="align-middle"><a href="/admin/customer/${item.id}" style="text-decoration: none">${item.code}</a></td>
                
                <td class="align-middle">${item.name || ""}</td>
                  <td class="align-middle">${item.phoneNumber || ""}</td>
                <td class="align-middle text-center ">${item.groupCustomer}</td>
                <td class="align-middle text-center">${item.debtsTotal}</td>
                <td class="align-middle text-center">${item.spendTotal}</td>
                <td class="align-middle text-end">${item.quantityItemOrder}</td>
            </tr>
        `;
        return str;
    }


}


