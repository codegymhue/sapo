class CustomerApp {

    static DOMAIN_API = origin;
    // static BASE_URL_Customer = this.DOMAIN_API + "/api/customers";
    static BASE_URL_CUSTOMER = this.DOMAIN_API + "/api/customers";
    static BASE_URL_CUSTOMER_GROUP = this.DOMAIN_API + "/api/customer_groups";

    static URL_CREATE_CUSTOMER = this.BASE_URL_Customer + "/create";
    static URL_UPDATE_CUSTOMER = this.BASE_URL_Customer + "/update";

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
    static IziToast = class {
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
        return `
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
    }

    static formatDatePattern = (date) => {
        let fullYear = date.split('-');
        let yyyy = fullYear[0];
        let mm = fullYear[1];
        let dd = fullYear[2].split('T')[0];

        return dd + '/' + mm + '/' + yyyy;
    }

    static showErrorValidate = (element, error) => {
        element.addClass('show').removeClass('hidden');
        element.text(error);
        CustomerApp.IziToast.showErrorAlert(error);
    }

    static getValue = (input) => {
        let value = input.val().trim();
        return value ? value : undefined;
    }

    static getSelectedValue = (input, variable1, variable2) => {
        if (Number(input.find(':selected').val()) !== 0) {
            variable1 = input.find(':selected').val();
            variable2 = input.find(':selected').text();
        } else {
            variable1 = undefined;
            variable2 = undefined;
        }
    }

    static setNullObj = (obj, val) => Object.keys(obj).forEach(k => obj[k] = val);

    static redrawDatatables = (tableName, url) => {
        $.get(url, function(newData) {
            tableName.clear();
            tableName.rows.add(newData);
            tableName.draw();
        });
    }

    static searchDefaultPriceDropdown = () => {
        let filter, input, div, a, i;

        input = CustomerApp.page.elements.searchDefaultPriceDropdown;

        filter = input.val().toUpperCase();

        div = document.getElementById("defaultPriceDropdown");

        a = div.getElementsByTagName("a");
        for (i = 0; i < a.length; i++) {
            let txtValue = a[i].textContent || a[i].innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                a[i].style.display = "";
            } else {
                a[i].style.display = "none";
            }
        }
    }

    static selectPricePolicy = () => {
        $(".selectPricePolicy").on('click', function () {
            defaultPricingPolicyId = $(this).data('id');
            let value = $(this).data('value');

            CustomerApp.page.elements.selectedPriceDefault.text(value);
            CustomerApp.page.elements.defaultPriceDropdown[0].classList.toggle("show")
        })
    }

    static page = {
        urls: {
            getAllCustomers: CustomerApp.BASE_URL_CUSTOMER,
            getGroupCustomerById: CustomerApp.BASE_URL_CUSTOMER + "/groupCustomer",
            getAllStatus: CustomerApp.BASE_URL_CUSTOMER + "/status",
            updateStatusAvailable: CustomerApp.BASE_URL_CUSTOMER + "/updateStatusAvailable",
            updateStatusUnavailable: CustomerApp.BASE_URL_CUSTOMER + "/updateStatusUnavailable",
            deleteProduct: CustomerApp.BASE_URL_CUSTOMER + "/delete",
            getAllCustomerGroup: CustomerApp.BASE_URL_CUSTOMER_GROUP
        },
        elements: {},
        loadData: {},
        commands: {},
        dialogs: {
            elements: {},
            loadData: {},
            commands: {},
        },
        initializeEventControl: {},
        util: {}
    }

    static dataTableDetails = {
        language: {
            "processing": `<i class="fa fa-spinner fa-spin fa-3x fa-fw"></i><span class="sr-only">Loading...</span>`,
            "aria": {
                "sortAscending": ": Sắp xếp thứ tự tăng dần",
                "sortDescending": ": Sắp xếp thứ tự giảm dần"
            },
            "autoFill": {
                "cancel": "Hủy",
                "fill": "Điền tất cả ô với <i>%d<\/i>",
                "fillHorizontal": "Điền theo hàng ngang",
                "fillVertical": "Điền theo hàng dọc"
            },
            "buttons": {
                "collection": "Chọn lọc <span class=\"ui-button-icon-primary ui-icon ui-icon-triangle-1-s\"><\/span>",
                "colvis": "Hiển thị theo cột",
                "colvisRestore": "Khôi phục hiển thị",
                "copy": "Sao chép",
                "copyKeys": "Nhấn Ctrl hoặc u2318 + C để sao chép bảng dữ liệu vào clipboard.<br \/><br \/>Để hủy, click vào thông báo này hoặc nhấn ESC",
                "copySuccess": {
                    "1": "Đã sao chép 1 dòng dữ liệu vào clipboard",
                    "_": "Đã sao chép %d dòng vào clipboard"
                },
                "copyTitle": "Sao chép vào clipboard",
                "pageLength": {
                    "-1": "Xem tất cả các dòng",
                    "_": "Hiển thị %d dòng",
                    "1": "Hiển thị 1 dòng"
                },
                "print": "In ấn",
                "createState": "Tạo trạng thái",
                "csv": "CSV",
                "excel": "Excel",
                "pdf": "PDF",
                "removeAllStates": "Xóa hết trạng thái",
                "removeState": "Xóa",
                "renameState": "Đổi tên",
                "savedStates": "Trạng thái đã lưu",
                "stateRestore": "Trạng thái %d",
                "updateState": "Cập nhật"
            },
            "select": {
                "cells": {
                    "1": "1 ô đang được chọn",
                    "_": "%d ô đang được chọn"
                },
                "columns": {
                    "1": "1 cột đang được chọn",
                    "_": "%d cột đang được được chọn"
                },
                "rows": {
                    "1": "1 dòng đang được chọn",
                    "_": "%d dòng đang được chọn"
                }
            },
            "searchBuilder": {
                "title": {
                    "_": "Thiết lập tìm kiếm (%d)",
                    "0": "Thiết lập tìm kiếm"
                },
                "button": {
                    "0": "Thiết lập tìm kiếm",
                    "_": "Thiết lập tìm kiếm (%d)"
                },
                "value": "Giá trị",
                "clearAll": "Xóa hết",
                "condition": "Điều kiện",
                "conditions": {
                    "date": {
                        "after": "Sau",
                        "before": "Trước",
                        "between": "Nằm giữa",
                        "empty": "Rỗng",
                        "equals": "Bằng với",
                        "not": "Không phải",
                        "notBetween": "Không nằm giữa",
                        "notEmpty": "Không rỗng"
                    },
                    "number": {
                        "between": "Nằm giữa",
                        "empty": "Rỗng",
                        "equals": "Bằng với",
                        "gt": "Lớn hơn",
                        "gte": "Lớn hơn hoặc bằng",
                        "lt": "Nhỏ hơn",
                        "lte": "Nhỏ hơn hoặc bằng",
                        "not": "Không phải",
                        "notBetween": "Không nằm giữa",
                        "notEmpty": "Không rỗng"
                    },
                    "string": {
                        "contains": "Chứa",
                        "empty": "Rỗng",
                        "endsWith": "Kết thúc bằng",
                        "equals": "Bằng",
                        "not": "Không phải",
                        "notEmpty": "Không rỗng",
                        "startsWith": "Bắt đầu với",
                        "notContains": "Không chứa",
                        "notEndsWith": "Không kết thúc với",
                        "notStartsWith": "Không bắt đầu với"
                    },
                    "array": {
                        "equals": "Bằng",
                        "empty": "Trống",
                        "contains": "Chứa",
                        "not": "Không",
                        "notEmpty": "Không được rỗng",
                        "without": "không chứa"
                    }
                },
                "logicAnd": "Và",
                "logicOr": "Hoặc",
                "add": "Thêm điều kiện",
                "data": "Dữ liệu",
                "deleteTitle": "Xóa quy tắc lọc",
                "leftTitle": "Giảm thụt lề",
                "rightTitle": "Tăng thụt lề"
            },
            "searchPanes": {
                "countFiltered": "{shown} ({total})",
                "emptyPanes": "Không có phần tìm kiếm",
                "clearMessage": "Xóa hết",
                "loadMessage": "Đang load phần tìm kiếm",
                "collapse": {
                    "0": "Phần tìm kiếm",
                    "_": "Phần tìm kiếm (%d)"
                },
                "title": "Bộ lọc đang hoạt động - %d",
                "count": "{total}",
                "collapseMessage": "Thu gọn tất cả",
                "showMessage": "Hiện tất cả"
            },
            "datetime": {
                "hours": "Giờ",
                "minutes": "Phút",
                "next": "Sau",
                "previous": "Trước",
                "seconds": "Giây",
                "amPm": [
                    "am",
                    "pm"
                ],
                "unknown": "-",
                "weekdays": [
                    "Chủ nhật"
                ],
                "months": [
                    "Tháng Một",
                    "Tháng Hai",
                    "Tháng Ba",
                    "Tháng Tư",
                    "Tháng Năm",
                    "Tháng Sáu",
                    "Tháng Bảy",
                    "Tháng Tám",
                    "Tháng Chín",
                    "Tháng Mười",
                    "Tháng Mười Một",
                    "Tháng Mười Hai"
                ]
            },
            "emptyTable": "Không có dữ liệu",
            "info": " Từ _START_ tới _END_ trên tổng _TOTAL_",
            "infoEmpty": "Hiển thị 0 tới 0 của 0 dữ liệu",
            "lengthMenu": "Hiển thị _MENU_ kết quả ",
            "loadingRecords": "Đang tải...",
            "paginate": {
                "first": "Đầu tiên",
                "last": "Cuối cùng",
                "next": "Sau",
                "previous": "Trước"
            },
            "search": "Tìm kiếm:",
            "zeroRecords": "Không tìm thấy kết quả",
            "decimal": ",",
            "editor": {
                "close": "Đóng",
                "create": {
                    "button": "Thêm",
                    "submit": "Thêm",
                    "title": "Thêm mục mới"
                },
                "edit": {
                    "button": "Sửa",
                    "submit": "Cập nhật",
                    "title": "Sửa mục"
                },
                "error": {
                    "system": "Đã xảy ra lỗi hệ thống (&lt;a target=\"\\\" rel=\"nofollow\" href=\"\\\"&gt;Thêm thông tin&lt;\/a&gt;)."
                },
                "multi": {
                    "info": "Các mục đã chọn chứa các giá trị khác nhau cho đầu vào này. Để chỉnh sửa và đặt tất cả các mục cho đầu vào này thành cùng một giá trị, hãy nhấp hoặc nhấn vào đây, nếu không chúng sẽ giữ lại các giá trị riêng lẻ của chúng.",
                    "noMulti": "Đầu vào này có thể được chỉnh sửa riêng lẻ, nhưng không phải là một phần của một nhóm.",
                    "restore": "Hoàn tác thay đổi",
                    "title": "Nhiều giá trị"
                },
                "remove": {
                    "button": "Xóa",
                    "confirm": {
                        "_": "Bạn có chắc chắn muốn xóa %d hàng không?",
                        "1": "Bạn có chắc chắn muốn xóa 1 hàng không?"
                    },
                    "submit": "Xóa",
                    "title": "Xóa"
                }
            },
            "infoFiltered": "(được lọc từ _MAX_ dữ liệu)",
            "searchPlaceholder": "Nhập tìm kiếm...",
            "stateRestore": {
                "creationModal": {
                    "button": "Thêm",
                    "columns": {
                        "search": "Tìm kiếm cột",
                        "visible": "Khả năng hiển thị cột"
                    },
                    "name": "Tên:",
                    "order": "Sắp xếp",
                    "paging": "Phân trang",
                    "scroller": "Cuộn vị trí",
                    "search": "Tìm kiếm",
                    "searchBuilder": "Trình tạo tìm kiếm",
                    "select": "Chọn",
                    "title": "Thêm trạng thái",
                    "toggleLabel": "Bao gồm:"
                },
                "duplicateError": "Trạng thái có tên này đã tồn tại.",
                "emptyError": "Tên không được để trống.",
                "emptyStates": "Không có trạng thái đã lưu",
                "removeConfirm": "Bạn có chắc chắn muốn xóa %s không?",
                "removeError": "Không xóa được trạng thái.",
                "removeJoiner": "và",
                "removeSubmit": "Xóa",
                "removeTitle": "Xóa trạng thái",
                "renameButton": "Đổi tên",
                "renameLabel": "Tên mới cho %s:",
                "renameTitle": "Đổi tên trạng thái"
            },
        },
        lengthMenu: [
            [10, 30, 50, 1000000000],
            ["10", "30", "50", "All"]
        ],
        searching: false,
        destroy: true,
        columns: [
            {data: 'id'},
            {data: 'fullName'},
            {data: 'customerCode'},
            {data: 'phoneNumber'},
            // { data: 'email' },
            // { data: 'birthday' },
            // { data: 'group.cusGrpCode' },
            {data: 'group.title'},
            // { data: 'group.discount' },
            // { data: 'gender' },
            // { data: 'description' },
            {data: 'debtTotal'},
            {data: 'spendTotal'},
            {data: 'quantityProductOrder'},
        ],
        columnDefs: [
            {
                targets: 0,
                orderable: false,
                render: (data) => {
                    return `
                            <div class="form-check form-check-sm form-check-custom form-check-solid">
                                <input class="form-check-input checkboxes" type="checkbox" value="${data}" />
                            </div>`;
                }
            },
            {
                targets: 2,
                render: (data, type, row) => {
                    return `
                                    <a href="/admin/customers/${row.id}" style="text-decoration: none" class="hover">
                                        ${row.customerCode}
                                    </a>
                                `;
                }
            },
        ],
    }

static validateTest = () => {
    $.validator.addMethod("isNotStartWithCUZN", function (value, element) {
        return this.optional(element) || /^(?!cuzn)\w+$/i.test(value);
    }, "Mã khách hàng không được có tiền tố CUZN của hệ thống");
}
}


