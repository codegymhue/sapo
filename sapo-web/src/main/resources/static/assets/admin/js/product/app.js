class App {
    static DOMAIN_API = "http://localhost:8080";
    static BASE_URL_Product = this.DOMAIN_API + "/api/products";
    static BASE_URL_Categories = this.DOMAIN_API + "/api/categories";
    static BASE_URL_Brands = this.DOMAIN_API + "/api/brands";
    static BASE_URL_PRODUCT = this.DOMAIN_API + "/api/products";
    static URL_CREATE_PRODUCT = this.BASE_URL_Product + "/create";
    static URL_UPDATE_PRODUCT = this.BASE_URL_Product + "/update";
    static BASE_URL_VARIANT = this.DOMAIN_API + "/api/variants"


    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";

        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_DELETE = "Xóa dữ liệu thành công";

        static ERROR_TITLE = "Thao tác không thành công, Tên sản phẩm không được để trống.";
        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

    }

    static IziToast = class {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'Thành công: ',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
        static showFilterAlert(m) {
            iziToast.success({
                title: 'Lưu bộ lọc thành công: ',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }

        static showErrorAlert(m) {
            iziToast.error({
                title: 'Lỗi: ',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
    }

    static renderRowProduct(item, showStatus) {
        let str = `
             <tr id="tr_${item.id}" >
                <td class="align-middle">
                <label for="${item.id}" style="padding: 1rem; cursor:pointer">
                    <input style="cursor:pointer" id ="${item.id}" class="selectCheckbox"  name="options[]" type="checkbox" value="${item.id}">
                </label>
                </td>
                <td class="align-middle"><img width="50px" height="40px" src=${item.image || "/assets/admin/images/no-image.jpg"} alt="image"></td>
                <td class="align-middle"><a href="/admin/product/${item.id}" style="text-decoration: none">${item.title}</a></td>
                <td class="align-middle">${item.category.title || ""}</td>
                <td class="align-middle">${item.brand.title || ""}</td>
                <td class="align-middle text-end ">${new Intl.NumberFormat('de-DE').format(item.available)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.inventory)}</td>
                <td class="align-middle">
                    <span id="showStatus" class="${showStatus}">${item.status === "AVAILABLE" ? "Đang giao dịch" : "Ngừng giao dịch"}</span> 
                </td>
                <td class="align-middle">${item.createAt === null ? "" : new Date(item.createAt).toLocaleDateString('en-GB')}</td>
                <td class="align-middle">${item.updateAt === null ? "" : new Date(item.updateAt).toLocaleDateString('en-GB')}</td>
            </tr>
        `;
        return str;
    }

    static renderRowProductVariant(item, showStatus){
        let str = `
             <tr id="tr_${item.id}" >
                <td class="align-middle sticky-col first-col" style="background-color: white !important">
                     <label for="${item.id}" style="padding: 1rem; cursor:pointer">
                        <input id ="${item.id}" style="cursor:pointer" class="selectCheckbox"  name="options[]" type="checkbox" value="${item.id}">
                    </label>
                </td>
                <td class="align-middle sticky-col second-col" style="background-color: white !important"><img width="50px" height="40px" src=${item.image || "/assets/admin/images/no-image.jpg"} alt="image"></td>
                <td class="align-middle sticky-col third-col" style="background-color: white !important">
                    <a href="/admin/product/${item.id}" style="text-decoration: none">${item.title}</a>
                    <br>
                    <span>${item.sku}</span>
                </td>
                <td class="align-middle">${item.barCode}</td>
                <td class="align-middle">${item.category.title || ""}</td>
                <td class="align-middle">${item.brand.title || ""}</td>
                <td class="align-middle">
                    <span id="showStatus" class="${showStatus}">${item.status === "AVAILABLE" ? "Đang giao dịch" : "Ngừng giao dịch"}</span> 
                </td>
                <td class="align-middle">${ item.createAt === null ? "" : new Date(item.createAt).toLocaleDateString('en-GB')}</td>
                <td class="align-middle">${item.updateAt === null ? "" : new Date(item.updateAt).toLocaleDateString('en-GB')}</td>
                <td class="align-middle">${item.applyTax === true ? "Có" : "Không"}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.retailPrice)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.importPrice)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.wholesalePrice)}</td>
                <td class="align-middle text-end ">${new Intl.NumberFormat('de-DE').format(item.available)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.inventory)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.inTransit)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.shipping)}</td>
                <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.trading)}</td>
            </tr>
        `;
        return str;
    }

    static renderRowProductInventory(item) {
        let str = `
        <tr>
            <td>${item.sku}</td>
            <td>
                <a href="/admin/product/${item.id}" style="text-decoration: none">${item.title}</a>
            </td>
            <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.retailPrice)}</td>
            <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.inventory)}</td>
            <td class="align-middle text-end ">${new Intl.NumberFormat('de-DE').format(item.available)}</td>
            <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.trading)}</td>
            <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.trading)}</td>
            <td class="align-middle text-end">${new Intl.NumberFormat('de-DE').format(item.shipping)}</td>
        </tr>
        `;
        return str;
    }

}

class Image {
    constructor(cloudId, fileName, fileFolder, fileUrl, fileType) {
        this.cloudId = cloudId;
        this.fileName = fileName;
        this.fileFolder = fileFolder;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }
}

class Product {
    constructor(id, title, enableSell,
                description, unit, mass,
                sku, barCode, quantity,
                retailPrice, importPrice,
                wholesalePrice, categoryId,
                brandId, applyTax, image,
                taxInclusive, taxList,
                costPrice, enableVariant) {
        this.id = id;
        this.title = title;
        this.enableSell = enableSell;
        this.description = description;
        this.unit = unit;
        this.sku = sku;
        this.barCode = barCode;
        this.mass = mass;
        this.retailPrice = retailPrice;
        this.importPrice = importPrice;
        this.wholesalePrice = wholesalePrice;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.applyTax = applyTax;
        this.taxInclusive = taxInclusive;
        this.taxList = taxList;
        this.mediaList = image;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.enableVariant = enableVariant;
    }
}

class ProductItemPage {
    constructor(id, image, title, brand, category, available, inventory, status, createAt, updateAt) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.brand = brand;
        this.category = category;
        this.available = available;
        this.inventory = inventory;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}

class ProductVariant {
    constructor(id, image, title, sku, barCode, category,
                brand, status, createAt, updateAt, applyTax, retailPrice,
                importPrice,wholesalePrice, available, inventory, trading, inTransit, shipping ) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.sku = sku;
        this.barCode = barCode;
        this.category = category;
        this.brand = brand;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.applyTax = applyTax;
        this.retailPrice = retailPrice;
        this.importPrice = importPrice;
        this.wholesalePrice = wholesalePrice;
        this.available = available;
        this.available = available;
        this.inventory = inventory;
        this.trading = trading;
        this.inTransit = inTransit;
        this.shipping = shipping;
    }
}

class ProductTaxParam {
    constructor(taxId, taxType) {
        this.taxId = taxId;
        this.taxType = taxType;
    }
}

class Category {
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }
}

class Brand {
    constructor(id, title) {
        this.id = id;
        this.title = title;
    }
}
class Tax{
    constructor(id,code,title,tax) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.tax = tax;
    }
}


