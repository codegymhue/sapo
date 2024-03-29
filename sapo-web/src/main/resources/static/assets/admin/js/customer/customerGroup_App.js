class CustomerGroup_App {

    static DOMAIN_API = origin;
    static BASE_URL_CUSTOMER_GROUP = this.DOMAIN_API + "/api/customer_groups";

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_DELETE = "Xóa dữ liệu thành công";

        static ERROR_TITLE = "Thao tác không thành công, Tên Group không được để trống.";
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

        static showSuspendedConfirmDialog() {
            return Swal.fire({
                icon: 'warning',
                text: 'Bạn có muốn xóa nhóm khách hàng này không ?',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Có',
                cancelButtonText: 'Không',
            })
        }

    }
    static IziToast = class {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
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
}

// class CustomerGroupDataTable{
//     constructor(draw, start, length, recordsTotal, recordsFiltered, data) {
//         this.draw = draw;
//         this.start = start;
//         this.length = length;
//         this.recordsTotal = recordsTotal;
//         this.recordsFiltered = recordsFiltered;
//         this.data = data;
//     }
// }

class CustomerGroupFilter {
    constructor(title, cusGrpCode, customerGroupType, description, countCus, createdAt, draw) {
        this.title = title;
        this.cusGrpCode = cusGrpCode;
        this.customerGroupType = customerGroupType;
        this.description = description;
        this.countCus = countCus;
        this.createdAt = createdAt;
        this.draw = draw;
    }
}