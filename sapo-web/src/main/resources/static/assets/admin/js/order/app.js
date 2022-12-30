class LocationRegionResult{
    constructor(id,address, wardId, wardName, districtId, districtName, provinceId, provinceName) {
        this.id = id;
        this.address = address;
        this.wardId = wardId;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }
}

class LocationRegionCreate{
    constructor(id,address, wardId, wardName, districtId, districtName, provinceId, provinceName) {
        this.id = id;
        this.address = address;
        this.wardId = wardId;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }
}
class Employee {
    contructor(id, name) {
        this.id = id;
        this.name = name;
    }
}

class EmployeeResult {
    contructor(id, birthday, email, gender, name) {
        this.id = id;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.name = name;
    }
}
class ProductResult {
    constructor(id, title, image, unit, sku, retailPrice, wholesalePrice) {
            this.id = id;
            this.title = title;
            this.image = image;
            this.unit = unit;
            this.sku = sku;
            this.retailPrice = retailPrice;
            this.wholesalePrice = wholesalePrice;
    }
}

class OrderResult {
    constructor(id, employeeId, customerId, discount, description, orderCode, orderStausId, orderItemResult, listTaxItem) {
        this.id =id;
        this.employeeId = employeeId;
        this.customerId = customerId;
        this.discount = discount;
        this.description = description;
        this.orderCode = orderCode;
        this.orderStatusId =  orderStausId;
        this.orderItemResult = orderItemResult;
        this.listTaxItem = listTaxItem;
    }

}
class TaxItem{
    //{id: 1, code: 'VAT5', title: 'Thuế suất 5%', tax: 5, type: null}
    constructor(tax, listProductId) {
        this.tax = tax;
        this.listProductId  = listProductId;
    }
}

class OrderItemResult {
    constructor(id, productId, quantity ) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

}

class LocationRegion {
    constructor(address, wardId, wardName, districtId, districtName, provinceId, provinceName) {
        this.address = address;
        this.wardId = wardId;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;


    }
}
class Customer {
    constructor(id, customerCode, name, phone,customerRegionId,  customerGroup, email, birthday, status, createAt, updateAt, employeeId, deleted, locationRegionResult) {
        this.id = id;
        this.customerRegionId = customerRegionId;
        this.customerCode = customerCode;
        this.name = name;
        this.phone = phone;
        this.customerGroup = customerGroup;
        this.email = email;
        this.birthday = birthday;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.employeeId = employeeId;
        this.deleted = deleted;
        this.locationRegionResult = locationRegionResult;
    }
}

class ShippingAddress {
    constructor(id, fullName, mobile,line1, line2, email,districtId,  districtName, provinceId, provinceName, wardId, wardName, zipCode,customerId,supplierId ,isDefault) {
        this.fullName = fullName;
        this.line1 = line1;
        this.line2 = line2;
        this.mobile = mobile;
        this.email = email;
        // this.address = address;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.wardId = wardId;
        this.wardName = wardName;
        this.zipCode = zipCode;
        this.customerId = customerId;
        this.id = id;
        this.isDefault = isDefault;supplierId
        this.supplierId = supplierId;
    }
}

class ItemResult {
    constructor(id, productId, employeeId, quantity, price, sku, discount, available) {
        this.id = id;
        this.productId = productId;
        this.employeeId = employeeId;
        this.quantity = quantity;
        this.price = price;
        this.sku = sku;
        this.discount = discount;
        this.available = available;
    }
}

class Product {
    constructor(id, title, sku, categoryId, retailPrice, quantity, mass, importPrice,mediaList) {
        this.id = id;
        this.title = title;
        this.sku = sku;
        this.quantity = quantity;
        this.retailPrice = retailPrice;
        this.importPrice = importPrice;
        this.categoryId = categoryId;
        this.mass = mass;
        this.mediaList = mediaList;
    }
}

class Address {
    constructor(id,phoneNumber,email,customerId,supplierId,line1,line2,wardId,wardName,districtId,districtName,provinceId,provinceName,zipCode,isShipping,isReceiveBill) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.line1 = line1;
        this.line2 = line2;
        this.wardId = wardId;
        this.wardName = wardName;
        this.districtId = districtId;
        this.districtName = districtName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.zipCode = zipCode;
        this.isShipping = isShipping;
        this.isReceiveBill = isReceiveBill;

    }
}

class App {
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
                position: 'top-end',
                text: t,
                timer: 1500
            })
        }


        static showConfirmDelete() {
            Swal.fire({
                title: 'Bạn có chắc chắn muốn xóa sản phẩm không?',
                showDenyButton: true,
                showCancelButton: true,
                confirmButtonText: 'Xóa',
                denyButtonText: `Không!`,
            })
        }
    }

    static IziToast = class  {
        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                message: m,
                timer: 2000,

            });
        }
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                message: m,
                timer: 2000,
            });
        }
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Success',
                position: 'topRight',
                message: m,
                timer: 2000,
            });
        }
    }
}