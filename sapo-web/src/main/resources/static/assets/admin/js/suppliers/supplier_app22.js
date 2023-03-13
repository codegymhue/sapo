"use strict";



class SupplierApp {
    static DOMAIN_SERVER = origin;
    static SUPPLIER_API = this.DOMAIN_SERVER + "/api/suppliers";
    static EMPLOYEE_API = this.DOMAIN_SERVER + "/api/employees";
    static SUPPLIER_GROUP_API = this.DOMAIN_SERVER + "/api/supplier_groups";
    static SUPPLIER_FILTER_API =  this.SUPPLIER_API + "/filter"
    static SUPPLIER_TAGS_API =  this.SUPPLIER_API + "/tags"

    static getTbody(item, field, showStatus) {
        switch (field) {
            case "code":
                return `<td class="align-middle"><a href="/admin/suppliers/${item.id}/histories"
                            style="text-decoration: none">${SupplierApp.getTbodyValue(field, item) || ""}</a></td>`;
            case "status":
                return ` <td onclick="location.href='/admin/suppliers/${item.id}/histories'" class="align-middle">
                               <span id="showStatus" class="${showStatus}">${SupplierApp.getTbodyValue(field, item) || ""}</span> 
                        </td> `;
            default :
                return `<td onclick="location.href='/admin/suppliers/${item.id}/histories'"
                           class="align-middle">${SupplierApp.getTbodyValue(field, item) || ""}</td>`;
        }


    }

    static getThead(field) {
        let tdItem = `<th class="align-middle" nameth="code" scope="col">${SupplierApp.getTheadValue(field) || ""}</th>`;
        return tdItem;
    }

    static getTbodyValue(key, item) {
        switch (key) {
            case "code":
                return item.supplierCode;
            case "supplier_name":
                return item.fullName;
            case "supplier_group":
                return item.group == null ? "" : item.group.title;
            case "email":
                return item.email;
            case "phone_number":
                return item.phone;
            case "status":
                return item.status === "AVAILABLE" ? "Đang giao dịch" : "Ngừng giao dịch";
            case "assignee_name":
                return item.employee == null ? "" : item.employee.fullName;
            case "tax_number":
                return item.taxCode;
            case "website":
                return item.website;
            case "created_on":
                return item.createdAt === null ? "" : new Date(item.createdAt).toLocaleDateString('en-GB');
            case "modified_on":
                return item.updatedAt === null ? "" : new Date(item.updatedAt).toLocaleDateString('en-GB');
            case "fax":
                return item.fax;
        }
    }

    static getTheadValue(key) {
        switch (key) {
            case "code":
                return "Mã nhà cung cấp";
            case "supplier_name":
                return "Tên nhà cung cấp";
            case "supplier_group":
                return "Nhóm nhà cung cấp";
            case "email":
                return "Email";
            case "phone_number":
                return "Số điện thoại";
            case "status":
                return "Trạng thái";
            case "assignee_name":
                return "Nhân viên phụ trách";
            case "tax_number":
                return "Mã số thuế";
            case "website":
                return "Website";
            case "created_on":
                return "Ngày tạo";
            case "modified_on":
                return "Lần cập nhật cuối";
            case "fax":
                return "Số fax";
        }
    }

    static renderTbodyFieldsDisplays(item, fields,showStatus) {
        let str = '';
        for (let field of fields) {
            str += SupplierApp.getTbody(item, field,showStatus);
        }
        return str;
    }

    static renderTheadFieldsDisplays(fields) {
        let str = '';
        for (let field of fields) {
            str += SupplierApp.getThead(field);
        }
        return str;
    }

    static renderTbodyFieldsSupplier(item, showStatus, fields) {
        let str = `
             <tr id="tr_${item.id}" >
                <td class="align-middle">
                <label for="sup_${item.id}" style="padding: 1rem; cursor:pointer">
                    <input style="cursor:pointer" id="sup_${item.id}" class="selectCheckbox" name="sup_${item.id}" type="checkbox" value="${item.id}">
                </label>
                </td>
            ${SupplierApp.renderTbodyFieldsDisplays(item, fields, showStatus)}
            </tr>
        `;
        return str;
    }

    static renderTheadFieldsSupplier(fields) {
        let str = ` <tr id="theadList" class="show">
                            <th class="align-middle">
                                 <label for="checkAll" style="padding: 1rem; cursor:pointer">
                                     <input style="cursor:pointer" id="checkAll" type="checkbox" value=""
                                                   nameth="bulk_action"
                                                   data-bs-toggle="tooltip" data-bs-placement="top" title="Tất cả">
                                 </label>
                            </th>
                             ${SupplierApp.renderTheadFieldsDisplays(fields)}
                    </tr>
        `;
        return str;
    }

    static renderFilterTab(tab) {
        let str = `<li class="filter-tab-item" style="opacity: 1; padding-bottom: 3.5px;">
                       <a href="/admin/suppliers?savedSearchId=${tab.id}" class="filter-tab">${tab.title}</a>
                   </li>`
        return str;
    }

    static renderEmptySearchResults() {
        let str = `<div class="ui-type-container clearfix">
                        <div class="empty-search-results" illustration="next-discounts-detailed">
                            <svg class="next-icon next-icon--color-sky-darker next-icon--size-80 empty-search-results__illustration">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#next-search-reverse">
                                </use>
                            </svg>
                            <h2 class="empty-search-results__title">Không tìm thấy nhà cung cấp phù hợp với điều kiện tìm kiếm</h2>
                            <p class="empty-search-results__message">Thử thay đổi điều kiện lọc hoặc từ khóa tìm kiếm</p>
                        </div>
                </div>`
        return str;
    }

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";

        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_DELETE = "Xóa dữ liệu thành công";

        static ERROR_TITLE = "Thao tác không thành công, Tên nhà cung cấp không được để trống.";
        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

    }


    static IziToast = class {
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
    }

    static renderEmployeeOnFilter(employee) {
        let str = ` <li class="multiselect--sapo-checkbox">
                    <a tabindex="0" class="multiselect-a--sapo-checkbox">
                    <span class="sapo-checkbox">
                    <input type="checkbox" value="${employee.id}" id="${employee.id}" class="sapo-checkbox__input checkbox_employee">
                    <span class="sapo-checkbox__checkmark"></span>
                    </span>
                    <label class="checkbox multiselect-label--sapo-checkbox"
                     for="${employee.id}"> ${employee.fullName}</label>
                     </a>
                     </li>`
        return str;
    }

    static renderSupplierTagsOnFilter(tag, index) {
        let str = `  <div class="autocomplete-suggestion suggestion-product suggestion-tag"
                            style="padding:4px 8px" data-index="${index}">
                        <div class="search-product-content-refactor item-supplier" 
                            style="width:100%;">
                           <div class="search-product-content-name"><span>${tag}</span> </div>
                        </div>
                     </div>`
        return str;
    }

    static renderSupplierGroupOnFilter(supplierGroup) {
        let str = ` <li class="multiselect--sapo-checkbox">
                    <a tabindex="0" class="multiselect-a--sapo-checkbox">
                    <span class="sapo-checkbox">
                    <input type="checkbox" value="${supplierGroup.id}" id="${supplierGroup.id}" class="sapo-checkbox__input checkbox_supplier_group">
                    <span class="sapo-checkbox__checkmark"></span>
                    </span>
                    <label class="checkbox multiselect-label--sapo-checkbox"
                     for="${supplierGroup.id}"> ${supplierGroup.title}</label>
                     </a>
                     </li>`
        return str;
    }

    static renderFilterListTag(supplierFilter){


        let str = `<li class="filter-tag">
                       <span class="tag__label">
                                Trạng thái: Đang giao dịch, Ngừng giao dịch
                       </span>
                       <button type="button" class="tag__remove-button" bind-event-click="removeFilterItem('status',this)">
                           <svg class="next-icon next-icon--size-12"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#next-remove"></use> </svg>
                       </button>
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
                return ` <div>
        <div class="btn-group show"><button type="button"
                class="multiselect dropdown-toggle btn sapo-btn-blank btn-default" data-toggle="dropdown"
                style="padding:0.8rem" title="Chọn nhân viên" aria-expanded="true"><span
                    class="multiselect-selected-text">Chọn nhân viên</span><svg xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 20 20" class="icon-dropdown-select">
                    <path d="M10 16l-4-4h8l-4 4zm0-12L6 8h8l-4-4z"></path>
                </svg></button>
            <ul class="multiselect-container dropdown-menu show"
                style="width: 100%; max-height: 250px; overflow: hidden auto; position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, 36px, 0px);"
                x-placement="bottom-start">
                
                <li class="multiselect--sapo-checkbox"><a tabindex="0" class="multiselect-a--sapo-checkbox"><span
                            class="sapo-checkbox"><input type="checkbox" value="946220"
                                id="multiselect-8b6152d9-7f76-3fc2-2511-2e4b1eccb76a" class="sapo-checkbox__input"><span
                                class="sapo-checkbox__checkmark"></span></span><label
                            class="checkbox multiselect-label--sapo-checkbox"
                            for="multiselect-8b6152d9-7f76-3fc2-2511-2e4b1eccb76a"> Nguyễn Chơn Lợi</label></a></li>
            </ul>
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

    static renderStatusFilter() {
        let str = `<div class="filter-item" filter-type="status" style="width:auto;">
        <input type="hidden" class="choosed-single-id">
        <div class="select__wrapper">
            <select class="sapo-select select-change-filter" bind-event-change="changeItemFilter(this)">
                    <option value="status" selected="">Trạng thái</option>
                    <option value="assigneeIds">Nhân viên</option>
                    <option value="supplierGroupId">Nhóm nhà cung cấp</option>
                    <option value="createdOn">Ngày tạo</option>
            </select>
            <svg class="next-icon next-icon--size-16"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select-chevron"></use> </svg>
        </div>
        <div class="btn-group pull-left" data-toggle="buttons">
            <label class="btn filter-button-tag " bind-event-click="setParamFilterActive('active',this)">
                <input type="checkbox" autocomplete="off"> Đang giao dịch
            </label>
            <label class="btn filter-button-tag " bind-event-click="setParamFilterActive('disable',this)">
                <input type="checkbox" autocomplete="off"> Ngừng giao dịch
            </label>
        </div>
        <a href="javascript" bind-event-click="removeItemFilter(this, 'status')" class="remove-item-filter"><svg class="next-icon next-icon--size-20"><use xlink:href="#delete-minor"></use> </svg></a>
    </div>`
        return str;
    }

    static renderFilter(chose) {
        let str = `
<div class="filter-item" filter-type="assigneeIds" style="width: auto; display: block; order: 0;">
                    <input type="hidden" class="choosed-single-id" value="">
                    <div class="select__wrapper">
                        <select class="sapo-select select-change-filter" bind-event-change="changeItemFilter(this)">
                                <option value="status" ${chose == "status" ? "selected" : ""}>Trạng thái</option>
                                <option value="assigneeIds" ${chose == "assigneeIds" ? "selected" : ""}>Nhân viên</option>
                                <option value="supplierGroupId" ${chose == "supplierGroupId" ? "selected" : ""}>Nhóm nhà cung cấp</option>
                                <option value="createdOn" ${chose == "createdOn" ? "selected" : ""}>Ngày tạo</option>
                        </select>
                        <svg class="next-icon next-icon--size-16"> <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#select-chevron"></use> </svg>
                    </div>
                    ${this.renderSwitchcaseFilter(chose)}
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
        let value = $(".sapo-select").val();

        return [str, value]
    }


    static selectChangeFilterAll() {
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
    }
}

class SupplierFilter {
    constructor() {
        this.filter = null;
        this.statuses = [];
        this.employeeIds = [];
        this.groupIds = [];
        this.tags = [];
        this.createdFrom = null;
        this.createdTo = null;
        this.pageNo = null;
        this.pageSize = null;
    }
}











