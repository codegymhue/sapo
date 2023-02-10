"use strict";

class App{
    static DOMAIN_SERVER = "http://localhost:8080";
    static PRODUCT_API = this.DOMAIN_SERVER + "/api/admin/suppliers";



    static renderRowSupplier(item, showStatus) {
        let str = `
             <tr id="tr_${item.id}" >
                <td class="align-middle">
                <label for="${item.id}" style="padding: 1rem; cursor:pointer">
                    <input style="cursor:pointer" id ="${item.id}" class="selectCheckbox"  name="options[]" type="checkbox" value="${item.id}">
                </label>
                </td>
                 <td class="align-middle"><a href="/admin/supplier/${item.id}" style="text-decoration: none">${item.supplierCode}</a></td>
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
    }
}

class Supplier {
    constructor( id,  supplierCode,  name,  email,  phone,  status,  description,  employeeId,  employee,
                 paymentMethodId,  paymentMethod,  supGroupResult,  supGroupTitle,  createAt,  updateAt) {
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






