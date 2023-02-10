"use strict";

class App {
    static DOMAIN_SERVER = "http://localhost:8080";
    static PRODUCT_API = this.DOMAIN_SERVER + "/api/admin/suppliers";
    static EMPLOYEE_API = this.DOMAIN_SERVER + "/api/employees";


    static renderRowSupplier(item, showStatus) {
        let str = `
             <tr id="tr_${item.id}" >
                <td class="align-middle">
                <label for="${item.id}" style="padding: 1rem; cursor:pointer">
                    <input style="cursor:pointer" id ="${item.id}" class="selectCheckbox"  name="options[]" type="checkbox" value="${item.id}">
                </label>
                </td>
                
                 <td class="align-middle"><a href="/admin/suppliers/${item.id}/histories" style="text-decoration: none">${item.supplierCode}</a></td>
                <td class="align-middle">${item.name || ""}</td>
                <td class="align-middle">${item.email || ""}</td>
                <td class="align-middle">${item.phone || ""}</td>
                 <td class="align-middle">
                    <span id="showStatus" class="${showStatus}">${item.status === "AVAILABLE" ? "Đang giao dịch" : "Ngừng giao dịch"}</span> 
                </td>   
<!--                <td class="align-middle text-end ">${new Intl.NumberFormat('de-DE').format(item.available)}</td>-->
              <td class="align-middle">${item.employee.fullName || ""}</td>
               
                <td class="align-middle">${item.createAt === null ? "" : new Date(item.createAt).toLocaleDateString('en-GB')}</td>
                <td class="align-middle">${item.updateAt === null ? "" : new Date(item.updateAt).toLocaleDateString('en-GB')}</td>
            </tr>
        `;
        return str;
    }


    static IziToast = class  {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Thêm thành công',
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
    static renderEmployeeOnFilter(employee) {
        let str = `<li class="multiselect--sapo-checkbox">
                        <a tabindex="0" class="multiselect-a--sapo-checkbox">
                            <span class="sapo-checkbox">
                                <input type="checkbox" value="${employee.id}" id="${employee.id}" class="sapo-checkbox__input">
                                <span class="sapo-checkbox__checkmark"></span>
                            </span>
                            <label class="checkbox multiselect-label--sapo-checkbox" for="${employee.id}">${employee.fullName}</label>
                        </a>
                   </li>`
        return str;
    }

    static renderSwitchcaseFilter(value) {
        switch (value) {
            case "status":
                return `<div class="btn-group pull-left" data-toggle="buttons">
                        <label class="btn filter-button-tag " >
                            <input type="checkbox" autocomplete="off"> Đang giao dịch
                        </label>
                        <label class="btn filter-button-tag " >
                            <input type="checkbox" autocomplete="off"> Ngừng giao dịch
                        </label>
                    </div>`;
                break;
            case "assigneeIds":
                return `<div class="btn-group pull-left">
                        <div>
                            <select multiple="multiple" id="assigneeIds" class="form-control margin-select" style="min-width: 300px; height: 36px; visibility: hidden; display: none;" placeholder="Chọn nhân viên">
                                    <option value="946220">Nguyễn Chơn Lợi</option>
                            </select><div class="btn-group"><button type="button" class="multiselect dropdown-toggle btn sapo-btn-blank btn-default" data-toggle="dropdown" style="padding:0.8rem" title="Chọn nhân viên"><span class="multiselect-selected-text">Chọn nhân viên</span><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="icon-dropdown-select"><path d="M10 16l-4-4h8l-4 4zm0-12L6 8h8l-4-4z"></path></svg></button><ul class="multiselect-container dropdown-menu" style="width: 100%; max-height: 250px; overflow: hidden auto;"><li><a tabindex="0"><label class="checkbox"><input type="checkbox" value="946220"> Nguyễn Chơn Lợi</label></a></li></ul></div>
                        </div>
                    </div>`;
                break;
            case "supplierGroupId":
                return `<div class="autocomplete-suggestion-holder float-left position-relative" style="max-width:600px">
                        <div class="w100" id="search-supplierGroupId-container">
                        </div>
                        <div class="hidden">
                            <div class="bootstrap-tagsinput"><input type="text" placeholder=""></div><input type="hidden" class="sapo-textbox choosed-single-id" id="supplierGroupId" value="" style="display: none;">
                        </div>
                        <div class="select__wrapper" style="margin:0;width:300px;" id="search-suggest-variant">
                            <a class="sapo-select select--a select-suggest" style="text-align:center" href="javascript:" data-original-title="" title="">Chọn nhóm nhà cung cấp</a>
                            <svg class="next-icon next-icon--size-16"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select-chevron"></use> </svg>
                            <div class="filter-body--variantIds filter-body--suggest">

                            </div>
                        </div>
                    </div>`;
                break;
            case "createdOn":
                return `<div class="input-group input-daterange pull-left" style="width: 300px;">
                        <input type="text" class="form-control from-date input-date sapo-textbox choosed-single-id" style="width: 120px;" name="createMin" value="" placeholder="Từ ngày" autocomplete="off">
                        <div class="input-group-append"><span class="input-group-text"></span></div>
                        <input type="text" class="form-control to-date input-date sapo-textbox choosed-single-id" style="width: 120px;" name="createMax" value="" placeholder="Đến ngày" autocomplete="off">
                    </div>`;
                break;
        }

    }

    static renderFilter(value) {
        let str = `
<div class="filter-item" filter-type="assigneeIds" style="width: auto; display: block; order: 0;">
                    <input type="hidden" class="choosed-single-id" value="">
                    <div class="select__wrapper">
                        <select class="sapo-select select-change-filter" bind-event-change="changeItemFilter(this)">
                                <option value="status" ${value == "status" ? "selected" : ""}>Trạng thái</option>
                                <option value="assigneeIds" ${value == "assigneeIds" ? "selected" : ""}>Nhân viên</option>
                                <option value="supplierGroupId" ${value == "supplierGroupId" ? "selected" : ""}>Nhóm nhà cung cấp</option>
                                <option value="createdOn" ${value == "createdOn" ? "selected" : ""}>Ngày tạo</option>
                        </select>
                        <svg class="next-icon next-icon--size-16"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select-chevron"></use> </svg>
                    </div>
                    ${this.renderSwitchcaseFilter(value)}
                    <a href="javascript" bind-event-click="removeItemFilter(this, 'assigneeIds')" class="remove-item-filter"><svg class="next-icon next-icon--size-20"><use xlink:href="#delete-minor"></use> </svg></a>
                </div> <br>
                 `
        return str;
    }

    static renderSelectWrapper() {
        let str = `<div class="select__wrapper">
                                <select class="sapo-select select-change-filter--all" >
                                    <option value="">Chọn điều kiện lọc</option>
                                    <option value="status">Trạng thái</option>
                                    <option value="assigneeIds">Nhân viên</option>
                                    <option value="supplierGroupId">Nhóm nhà cung cấp</option>
                                    <option value="createdOn">Ngày tạo</option>
                                </select>
                                <svg class="next-icon next-icon--size-16">
                                    <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select-chevron"></use>
                                </svg>
                    </div>`
        let value = $(".sapo-select").val()

        return [str, value]
    }
}
}


class Supplier {
    constructor(id, supplierCode, name, email, phone, status, description, employeeId, employee,
                paymentMethodId, paymentMethod, supGroupResult, supGroupTitle, createAt, updateAt) {
        this.id = id;
        this.supplierCode = supplierCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.description = description;
        this.employeeId = employeeId;
        this.employee = employee;
        this.paymentMethodId = paymentMethodId;
        this.paymentMethod = paymentMethod;
        this.supGroupResult = supGroupResult;
        this.supGroupTitle = supGroupTitle;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}

class SupplierFilter {
    constructor() {
        this.filter = null;
        this.statuses = [];
        this.employeeIds = [];
        this.supplierGroupId = [];
        this.createdFrom = null;
        this.createdTo = null;
    }
}











