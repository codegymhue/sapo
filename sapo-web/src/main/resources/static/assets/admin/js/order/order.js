class SaleOrderItemParam {
    constructor(price, quantity, productId, discount) {
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.discount = discount;
    }
};

let saleOrderItems = [];
let saleOrder = {
    fullName: undefined,
    mobile: undefined,
    line1: undefined,
    line2: undefined,
    city: undefined,
    province: undefined,
    zipCode: undefined,
    customerId: undefined,
    orderCode: undefined,
    description: undefined,
    discount: undefined,
    saleOrderItems: saleOrderItems
}
let product = new Product();
let customer = new Customer();
let shippingAddress = new ShippingAddress();
let products;
let customers;
let taxMap;
let items;
const taxSaleOrderMap = new Map();


function showListCustomer() {
    function show(data) {
        $(".searchCustomer").removeClass('d-none');
        $(".contentCustomer div").remove();
        $.each(data, (i, customer) => {

            let result = `
                <div class="MuiBox-root jss4978 InfiniteScroll-MenuItem focus-key-event showInfo" onclick="showCustomerInfo(${customer.id})"
                     data-id="${customer.id}" tabindex="0">
                    <li class="MuiButtonBase-root MuiListItem-root MuiMenuItem-root jss2894 MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button"
                        tabindex="-1" role="menuitem" aria-disabled="false">
                        <svg class="MuiSvgIcon-root icon" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 3c1.66 0 3 1.34 3 3s-1.34 3-3 3-3-1.34-3-3 1.34-3 3-3zm0 14.2c-2.5 0-4.71-1.28-6-3.22.03-1.99 4-3.08 6-3.08 1.99 0 5.97 1.09 6 3.08-1.29 1.94-3.5 3.22-6 3.22z"></path>
                        </svg>
                        <div class="MuiBox-root jss4983"><p
                                class="MuiTypography-root MuiTypography-body1 MuiTypography-noWrap">${customer.fullName}</p>
                                <h6 class="MuiTypography-root MuiTypography-subtitle1 MuiTypography-noWrap">${customer.phoneNumber}</h6></div>
                        <span class="MuiTouchRipple-root"></span>
                    </li>
                </div>
                `;
            $(".contentCustomer").append(result)
        })
    }

    if (customers !== undefined && customers.length > 0) {
        setTimeout(() => {
            show(customers);
        }, 100)
        return;
    }
}

const searchCustomer = () => {
    // console.log("searchCustomer");
    $("#myInput").on("input", function () {
        let search = $(this).val();
        let results = [];
        customers.forEach((item) => {
            if (((item.name).toLowerCase()).includes(search.toLowerCase()) || ((item.phoneNumber).toLowerCase()).includes(search.toLowerCase())) {
                results.push(item);
            }
        })
        $(".searchCustomer").removeClass('d-none');
        $(".contentCustomer div").remove();
        $.each(results, (i, customer) => {
            let result = `
                <div class="MuiBox-root jss4978 InfiniteScroll-MenuItem focus-key-event showInfo"  onclick="showCustomerInfo(${customer.id})"
                     data-id="${customer.id}" tabindex="0">
                    <li class="MuiButtonBase-root MuiListItem-root MuiMenuItem-root jss2894 MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button"
                        tabindex="-1" role="menuitem" aria-disabled="false">
                        <svg class="MuiSvgIcon-root icon" focusable="false" viewBox="0 0 24 24" aria-hidden="true">
                            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 3c1.66 0 3 1.34 3 3s-1.34 3-3 3-3-1.34-3-3 1.34-3 3-3zm0 14.2c-2.5 0-4.71-1.28-6-3.22.03-1.99 4-3.08 6-3.08 1.99 0 5.97 1.09 6 3.08-1.29 1.94-3.5 3.22-6 3.22z"></path>
                        </svg>
                        <div class="MuiBox-root jss4983"><p
                                class="MuiTypography-root MuiTypography-body1 MuiTypography-noWrap">${customer.fullName}</p>
                                <h6 class="MuiTypography-root MuiTypography-subtitle1 MuiTypography-noWrap">${customer.phoneNumber}</h6></div>
                        <span class="MuiTouchRipple-root"></span>
                    </li>
                </div>
                `;
            $(".contentCustomer").append(result);
        })
    })
}

const searchProduct = () => {
    $("#input_product").on("input", function () {
        let search = $(this).val();
        let results = [];
        products.forEach((item) => {
            // console.log(item)
            if (((item.title).toLowerCase()).includes(search.toLowerCase()) || ((item.sku).toLowerCase()).includes(search.toLowerCase())
                || ((item.barCode).toLowerCase()).includes(search.toLowerCase())) {
                results.push(item);
            }
            searchProduct();

        })
        $(".searchProduct").removeClass('d-none');
        $(".contentProduct div").remove();
        $.each(results, (i, product) => {
            let result = `
                      <div class="MuiBox-root jss3941 InfiniteScroll-MenuItem focus-key-event"
                    onclick="showProductInfo(${product.id})" data-id="${product.id}" tabindex="0">
                    <li class="MuiButtonBase-root MuiListItem-root MuiMenuItem-root jss1259 MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button" tabindex="-1" role="menuitem" aria-disabled="false">
                        <img class="jss1260" src="${product.image}" alt="">
                            <div class="MuiBox-root jss3946">
                                <div class="MuiBox-root jss3947">
                                    <p class="MuiTypography-root MuiTypography-body1" style="white-space: break-spaces;">${product.title} - ${product.barCode} - ${product.description} </p>
                                        <p class="MuiTypography-root MuiTypography-body2" style="line-height: 16px; display: flex;">
                                        <span class="MuiTypography-root MuiTypography-body2" style="color: rgb(163, 168, 175); line-height: 16px;"> ${product.sku} </span>
                                        <span class="MuiTypography-root jss1258 MuiTypography-body2 MuiTypography-colorPrimary" title="Mặc định">${product.description}</span>
                                    </p>
                                </div>
                                <div class="MuiBox-root jss3953">
                                    <h6 class="MuiTypography-root MuiTypography-h6">${product.retailPrice} 
</h6>
                                        <p class="MuiTypography-root MuiTypography-body1" style="margin-top: 4px;">
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(163, 168, 175);">Tồn:  </span>
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(0, 136, 255);">${product.quantity}</span>
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(163, 168, 175);">&nbsp;| Có thể bán: </span>
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(0, 136, 255);">${product.available}</span>
                                        </p>
                                </div>
                            </div>
                        <span class="MuiTouchRipple-root"></span>
                    </li>
                </div>    
                `;
            $(".contentProduct").append(result);
        })
    })
}


function showCustomerInfo(idCustomer) {
    // $("#MuiBox-list-customer").addClass("hide");
    $("#idCustomer").val(idCustomer);
    let result = customer = customers.find(({id}) => id === idCustomer);
    let shippingAddress = result.shippingAddress;
    let fullShippingAddress = "";
    if (shippingAddress != null) {
        if (shippingAddress.line1 != null)
            fullShippingAddress = `${shippingAddress.line1}, `;
        if (shippingAddress.line2 != null)
            fullShippingAddress += `${shippingAddress.line2}, `;
        if (shippingAddress.wardName != null)
            fullShippingAddress += `${shippingAddress.wardName}, `;
        if (shippingAddress.districtName != null)
            fullShippingAddress += `${shippingAddress.districtName}, `;
        if (shippingAddress.provinceName != null)
            fullShippingAddress += `${shippingAddress.provinceName}`;
        saleOrder.customerId = customer.id;
        saleOrder.fullName = customer.fullName;
        saleOrder.mobile = customer.phone;
        saleOrder.email = customer.email;
        saleOrder.line1 = shippingAddress.line1;
        saleOrder.line2 = shippingAddress.line2
        saleOrder.ward = shippingAddress.wardName;
        saleOrder.city = shippingAddress.districtName;
        saleOrder.province = shippingAddress.provinceName;
        saleOrder.zipCode = shippingAddress.zipCode;
    }

    let billAddress = result.billAddress;
    let fullBillAddress = "";
    if (billAddress != null) {
        if (billAddress.line1 != null)
            fullBillAddress = `${billAddress.line1}, `;
        if (billAddress.line2 != null)
            fullBillAddress += `${billAddress.line2}, `;
        if (billAddress.wardName != null)
            fullBillAddress += `${billAddress.wardName}, `;
        if (billAddress.districtName != null)
            fullBillAddress += `${billAddress.districtName}, `;
        if (billAddress.provinceName != null)
            fullBillAddress += `${billAddress.provinceName}`;
    }

    let str = `<div class="MuiPaper-root  jss938 MuiPaper-elevation1 MuiPaper-rounded" id="closed_customer_info">
        <div class="MuiBox-root jss985">
        
            <div class="MuiBox-root jss3887 jss939">
            
                <div class="MuiBox-root jss3888">
                    <h6 class="MuiTypography-root MuiTypography-h6" style="display: flex; align-items: center;"><svg
                            class="MuiSvgIcon-root" focusable="false" viewBox="0 0 24 24" aria-hidden="true"
                            style="color: rgb(163, 168, 175); font-size: 22px; margin-right: 10px;">
                            <path
                                d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 3c1.66 0 3 1.34 3 3s-1.34 3-3 3-3-1.34-3-3 1.34-3 3-3zm0 14.2c-2.5 0-4.71-1.28-6-3.22.03-1.99 4-3.08 6-3.08 1.99 0 5.97 1.09 6 3.08-1.29 1.94-3.5 3.22-6 3.22z">
                            </path>
                        </svg>Thông tin khách hàng</h6>
                    <div class="MuiBox-root jss3889"><a target="_blank" href="/admin/customers/181966855"
                            style="text-decoration: none; display: inline-flex; align-items: center;">
                            <h6 class="MuiTypography-root MuiTypography-h6 MuiTypography-colorPrimary"
                                title="Denise Zimmerman">${result.fullName}</h6>
                            <h6 class="MuiTypography-root MuiTypography-h6 MuiTypography-colorTextPrimary">
                                &nbsp;-&nbsp;${result.phoneNumber}</h6>
                        </a>
                        <button
                            class="MuiButtonBase-root MuiIconButton-root jss948 MuiIconButton-colorSecondary MuiIconButton-sizeSmall"
                            tabindex="0" 
                            onclick="handleRemove()"
                            type="button">
                            <span class="MuiIconButton-label">
                            <svg viewBox="0 0 24 24"
                                    fill="none" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                    font-size="20">
                                    <path
                                        d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12 19 6.41Z"
                                        fill="currentColor"></path>
                                </svg>
                                </span>
                                <span class="MuiTouchRipple-root"></span>
                                </button>
                                </div>
                </div>
            </div>
            <hr/>
            <div class="MuiBox-root jss3895 jss942">
                <div class="MuiBox-root jss3896 jss952">
                    <div class="MuiBox-root jss3897">
                        <div class="MuiBox-root jss3898 jss944">
                                <div class="btn-group">
                                <p class="MuiTypography-root jss941 MuiTypography-body2">Địa chỉ giao hàng</p>
                      <button style="height: 10px" class="MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary " type="button" id="defaultDropdown" data-bs-toggle="dropdown" data-bs-auto-close="true" aria-expanded="false">
                        <span class="MuiButton-label">Thay đổi</span>
                      </button>
                      <ul class="dropdown-menu" aria-labelledby="defaultDropdown" style="width: 450px">
                        <li>
                        <p class="dropdown-item" href="#" style="font-weight: bold">Thay Đổi Địa Chỉ 
                        <button style="float: right;height: 10px" data-bs-toggle="modal" data-bs-target="#create_order_address"
                         class="MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary ">Thêm Mới</button>
                        </li></p>
                        <li>
                        <button style="float: right;height: 10px" onclick="editCustomer()" class="MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary ">sửa</button>
                        <div class="MuiBox-root jss3900" style="padding-left: 20px;">
                                <p class="MuiTypography-root MuiTypography-body2 my-2" style="font-weight: bold">${result.phoneNumber}</p>
                                <p class="MuiTypography-root MuiTypography-body2">${fullShippingAddress}</p>
                            </div>
                        </li>
                     
                      </ul>
                    </div>
                            <div class="MuiBox-root jss3900">
                                <p class="MuiTypography-root MuiTypography-body2">${result.phoneNumber}</p>
                                <p class="MuiTypography-root MuiTypography-body2">${fullShippingAddress}</p>
                            </div>
                        </div>
                        <div class="MuiBox-root jss3901 jss945 jss947">
                            <div class="MuiBox-root jss3911 jss3902">
                                <li class="MuiListItem-root MuiListItem-gutters" style="padding: 5px 8px;">
                                    <div class="MuiListItemText-root">
                                        <p class="MuiTypography-root jss3903 MuiTypography-body1">Nợ phải thu</p>
                                    </div>
                                    <div class="MuiListItemText-root">
                                        <p
                                            class="MuiTypography-root jss3910 MuiTypography-body1 MuiTypography-colorError MuiTypography-alignRight">
                                            0</p>
                                    </div>
                                </li>
                                <li class="MuiListItem-root MuiListItem-gutters"
                                    style="cursor: pointer; padding: 5px 8px;">
                                    <div class="MuiListItemText-root">
                                        <p class="MuiTypography-root jss3903 MuiTypography-body1">Tổng chi tiêu (0 đơn)
                                        </p>
                                    </div>
                                    <div class="MuiListItemText-root">
                                        <p
                                            class="MuiTypography-root jss3910 MuiTypography-body1 MuiTypography-colorPrimary MuiTypography-alignRight">
                                             0</p>
                                    </div>
                                </li>
                                
                                    <li class="MuiListItem-root MuiListItem-gutters"
                                        style="cursor: pointer; padding: 5px 8px;">
                                        <div class="MuiListItemText-root">
                                            <p class="MuiTypography-root jss3903 MuiTypography-body1">Trả hàng (0 sản
                                                phẩm)</p>
                                        </div>
                                        <div class="MuiListItemText-root">
                                            <p
                                                class="MuiTypography-root jss3910 MuiTypography-body1 MuiTypography-colorError MuiTypography-alignRight">
                                                0</p>
                                        </div>
                                    </li>
                                    <li class="MuiListItem-root MuiListItem-gutters"
                                        style="cursor: pointer; padding: 5px 8px;">
                                        <div class="MuiListItemText-root">
                                            <p class="MuiTypography-root jss3903 MuiTypography-body1">Giao hàng thất bại
                                                (0 đơn)</p>
                                        </div>
                                        <div class="MuiListItemText-root">
                                            <p
                                                class="MuiTypography-root jss3910 MuiTypography-body1 MuiTypography-colorError MuiTypography-alignRight">
                                                0</p>
                                        </div>
                                    </li>
                                
                            </div>
                        </div>
                    </div>
                    <div class="MuiBox-root jss4275">
                        <div class="MuiBox-root jss4276 jss944">
                            <div class="MuiBox-root jss4277">
                                <p class="MuiTypography-root jss941 MuiTypography-body2">Địa chỉ nhận hóa đơn</p>
                                 <button style="height: 10px" class="MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary " type="button" id="defaultDropdown" data-bs-toggle="dropdown" data-bs-auto-close="true" aria-expanded="false">
                        <span class="MuiButton-label">Thay đổi</span>
                      </button>
                      <ul class="dropdown-menu" aria-labelledby="defaultDropdown" style="width: 450px">
                        <li>
                        <p class="dropdown-item" href="#" style="font-weight: bold">Thay Đổi Địa Chỉ 
                        <button style="float: right;height: 10px" data-bs-toggle="modal" data-bs-target="#create_order_customer"
                         class="MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary ">Thêm Mới</button>
                        </li></p>
                        <li>
                        <button style="float: right;height: 10px" onclick="editCustomer()" class="MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary ">sửa</button>
                        <div class="MuiBox-root jss3900" style="padding-left: 20px;">
                                <p class="MuiTypography-root MuiTypography-body2" style="font-weight: bold">${result.phoneNumber}</p>
                                <p class="MuiTypography-root MuiTypography-body2">${fullShippingAddress}</p>
                            </div>
                        </li>
                     
                      </ul>
                            </div>

                            <div class="MuiBox-root jss4278">
                                <p class="MuiTypography-root MuiTypography-body2">${result.phoneNumber}</p>
                                <p class="MuiTypography-root MuiTypography-body2">${fullBillAddress}</p>
                            </div>
                        </div>
                        <div class="MuiBox-root jss4279 jss945">
                            <div class="MuiBox-root jss4280">
                                <p class="MuiTypography-root jss941 MuiTypography-body2">Liên hệ</p>
                            </div>
                            <p class="MuiTypography-root MuiTypography-body2" style="color: rgb(163, 168, 175);">Chưa có
                                thông tin liên hệ</p>
                            <div class="MuiFormControl-root jss1315 MuiFormControl-fullWidth"
                                style="padding-top: 16px;">
                                <div class="MuiFormControl-root MuiTextField-root jss1317 MuiFormControl-fullWidth">
                                    <div
                                        class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-fullWidth MuiInputBase-formControl">
                                        <input aria-invalid="false" id="557fa869-3d86-41db-933d-de2fc27a08c6"
                                            placeholder="Email" type="text" aria-label="Without label" maxlength="200"
                                            class="MuiInputBase-input MuiOutlinedInput-input"
                                            value=${result.email} >
                                        <fieldset aria-hidden="true" class="jss1037 MuiOutlinedInput-notchedOutline"
                                            style="padding-left: 8px;">
                                            <legend class="jss1038" style="width: 0.01px;"><span>​</span></legend>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`;

    $("#show_customer_info").html(str);

}


function handleCloseListCustomers() {
    $(document).on("click", () => {
        $(".searchCustomer").addClass('d-none');
    })
}

function getAllItem() {
    return $.ajax({
        type: "GET",
        contentType: 'application/json',
        url: `${location.origin}/api/items/variants`
    })
        .done((data) => {
            items = data;
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function showListProducts() {
    // console.log("data: ", data);
    let renderProducts = (data) => {
        console.log("data: ", data);
        $(".searchProduct").removeClass('d-none');
        $(".searchProduct").removeClass('hide').addClass('show');

        $(".contentProduct div").remove();

        $.each(data, (i, product) => {
            let result = `             
                    <div class="MuiBox-root jss3941 InfiniteScroll-MenuItem focus-key-event"
                    onclick="showProductInfo(${product.id})" data-id="${product.id}" tabindex="0">
                    <li class="MuiButtonBase-root MuiListItem-root MuiMenuItem-root jss1259 MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button" tabindex="-1" role="menuitem" aria-disabled="false">
                        <img class="jss1260" src="${product.mediaList[0].fileUrl}" alt="">
                            <div class="MuiBox-root jss3946">
                                <div class="MuiBox-root jss3947">
                                    <p class="MuiTypography-root MuiTypography-body1" style="white-space: break-spaces;">${product.title} - ${product.barCode} </p>
                                        <p class="MuiTypography-root MuiTypography-body2" style="line-height: 16px; display: flex;">
                                    
                                        <span class="MuiTypography-root MuiTypography-body2" style="color: rgb(163, 168, 175); line-height: 16px;"> ${product.sku} </span>
                                        <span class="MuiTypography-root jss1258 MuiTypography-body2 MuiTypography-colorPrimary" title="Mặc định">${product.description}</span>
                                    </p>
                                </div>
                                <div class="MuiBox-root jss3953">
                                    <h6 class="MuiTypography-root MuiTypography-h6">${(product.retailPrice).formatVND()}</h6>
                                        <p class="MuiTypography-root MuiTypography-body1" style="margin-top: 4px;">
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(163, 168, 175);">Tồn:  </span>
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(0, 136, 255);">${product.totalInventory}</span>
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(163, 168, 175);">&nbsp;| Có thể bán: </span>
                                            <span class="MuiTypography-root MuiTypography-body1" style="color: rgb(0, 136, 255);">${product.availableInventory}</span>
                                        </p>
                                </div>
                            </div>
                        <span class="MuiTouchRipple-root"></span>
                    </li>
                </div>                       
                `;
            $(".contentProduct").append(result)
        })

        handleCloseListProducts();
    }
    setTimeout(() => {
        renderProducts(products);
    }, 100)

}

function handleCloseListProducts() {
    $(document).on("click", () => {
        // $(".contentCustomer div").empty();
        $(".searchProduct").addClass('d-none');
    })
}

function getAllProvinces() {
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "https://vapi.vnappmob.com/api/province/"
    })
        .done((data) => {
            if (data.results.length === 0) {
                let str = `<option value="0">Chọn Tỉnh/Thành Phố</option>`;
                $("#province").append(str);
            } else {
                $.each(data.results, (i, item) => {
                    let str = `<option value="${item.province_id}">${item.province_name}</option>`;
                    $("#province").append(str);
                    $('#provinceUpdate').append(str);
                });
            }

        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function getAllDistrictsByProvinceId(provinceId) {
    $("#district").empty();
    $("#districtUpdate").empty();
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "https://vapi.vnappmob.com/api/province/district/" + provinceId
    })
        .done((data) => {
            if (data.results.length === 0) {
                let str = `<option value="0">Chọn Quận/Huyện</option>`;
                $("#district").append(str);
            } else {
                $.each(data.results, (i, item) => {
                    let str = ` <option value="${item.district_id}">${item.district_name}</option>`;
                    $("#district").append(str);
                    $('#districtUpdate').append(str);
                })

            }
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function getAllWardsByDistrictId(districtId) {
    $("#ward").empty();
    $('#wardUpdate').empty();
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "https://vapi.vnappmob.com/api/province/ward/" + districtId
    })
        .done((data) => {
            // $('#ward').html('');
            // $('#wardUpdate').html('');

            if (data.results.length === 0) {
                let str = `<option value="0">Chọn Phường/Xã</option>`;
                $("#ward").append(str);

            } else {
                $.each(data.results, (i, item) => {
                    let str = `<option value="${item.ward_id}">${item.ward_name}</option>`;
                    $("#ward").append(str);
                    $('#wardUpdate').append(str);
                });
            }
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

getAllProvinces().then(() => {
    let provinceId = $("#province").val();

    getAllDistrictsByProvinceId(provinceId).then(() => {

        let districtId = $("#district").val();

        getAllWardsByDistrictId(districtId);
    });
});
$("#province").on('change', () => {
    let provinceId = $("#province").val();
    getAllDistrictsByProvinceId(provinceId).done(() => {
        let districtId = $("#district").val();
        getAllWardsByDistrictId(districtId);
    })

    $("#district").on('change', () => {
        let districtId = $("#district").val();
        getAllWardsByDistrictId(districtId);
    })
});

$("#provinceUpdate").on("change", () => {
    let provinceId = $("#provinceUpdate").val();
    getAllDistrictsByProvinceId(provinceId).then(() => {
        let districtId = $("#districtUpdate").val();
        getAllWardsByDistrictId(districtId);
    })
})

$("#districtUpdate").on("change", () => {
    let districtId = $("#districtUpdate").val();
    getAllWardsByDistrictId(districtId);
});

function getEmployeeById(id) {
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: `${location.origin}/api/employees/` + id
    })
        .done((data) => {
            employeeResult = data;
            $.each(data, (i, item) => {
                let str = `<option value="${item.id}">${item.name}</option>`;
                $("#selectEmployee").append(str);
                $("#selectEmployeeUpdate").append(str);
            });
            // }
        })
        .fail((jqXHR) => {
            console.log(jqXHR)
        })
}


function doCreateCustomer() {
    $('#btnCreateCustomer').on('click', () => {
        customer.fullName = $('#nameCreate').val();
        customer.code = $('#codeCreate').val();
        customer.phoneNumber = $('#phoneCreate').val();

        customer.createShippingAddressParam = shippingAddress;
        shippingAddress.line1 = $("#addressCreate").val();
        shippingAddress.provinceId = $('#province').val();
        shippingAddress.provinceName = $('#province :selected').text();
        shippingAddress.districtId = $('#district').val();
        shippingAddress.districtName = $('#district :selected').text();
        shippingAddress.wardId = $('#ward').val();
        shippingAddress.wardName = $('#ward :selected').text();
        customer.employeeId = $('#selectEmployee').val();

        $.ajax({
            "headers": {
                "accept": "application/json",
                "content-type": "application/json"
            },
            "type": "POST",
            "url": `${location.origin}/api/customers/create`,
            "data": JSON.stringify(customer)
        })
            .done((data) => {
                customer = data;
                customer.createShippingAddressParam = shippingAddress;
                $("#create_order_customer").modal("hide");
                App.IziToast.showSuccessAlert("Thêm khách hàng thành công!");
                searchCustomer();
                $('#nameCreate').val("");
                $('#codeCreate').val("");
                $('#phoneCreate').val("");
                $('#emailCreate').val("");
                $('#addressCreate').val("");
                $('#province').val("0").change();
                $('#district').val("0").change();
                $('#ward').val("0").change();
                $('#selectEmployee').val("0").change();
            })
            .fail((jqXHR) => {
                console.log(jqXHR)
            })
    });

}

doCreateCustomer();

function handleRemove() {
    let str = `<div class="MuiPaper-root  jss18028 MuiPaper-elevation1 MuiPaper-rounded">
                <div class="MuiBox-root jss18075 customer-info">
                    <div class="MuiBox-root jss18076 create-order-step2 jss18040">
                        <div class="MuiBox-root jss18077">
                            <h6 class="MuiTypography-root MuiTypography-h6">Thông tin khách
                                hàng</h6>
                        </div>
                        <div class="jss18078 SI-root">
                            <form autocomplete="off" class="MuiPaper-root jss18080 SearchBox MuiPaper-elevation1 MuiPaper-rounded">
                                <div class="MuiFormControl-root MuiTextField-root jss18081 MuiFormControl-fullWidth autocomplete">
                                    <div class="MuiInputBase-root MuiOutlinedInput-root MuiInputBase-fullWidth MuiInputBase-formControl MuiInputBase-adornedStart MuiOutlinedInput-adornedStart">
                                        <div class="MuiInputAdornment-root MuiInputAdornment-positionStart">
                                            <svg class="MuiSvgIcon-root" focusable="false"
                                                 viewBox="0 0 24 24" color="#A3A8AF"
                                                 aria-hidden="true"
                                                 style="width: 24px; height: 24px;">
                                                <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
                                            </svg>
                                        </div>
                                        <input aria-invalid="false"
                                               id="myInput"
                                               name="myInput"
                                               placeholder="Tìm theo tên, SĐT, mã khách hàng ... (F4)"
                                               type="text"
                                               onclick="showListCustomer()"
                                               class="MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputAdornedStart MuiOutlinedInput-inputAdornedStart"
                                               value="">
                                        <fieldset aria-hidden="true"
                                                  class="jss18127 MuiOutlinedInput-notchedOutline"
                                                  style="padding-left: 8px;">
                                            <legend class="jss18128" style="width: 0.01px;">
                                                <span>​</span></legend>
                                        </fieldset>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="MuiBox-root jss18132 jss18039">
                            <p class="MuiTypography-root MuiTypography-body1"
                               style="color: rgb(163, 168, 175); margin-top: 16px;">
                                Chưa có thông tin khách hàng</p>
                        </div>
                    </div>
                </div>
            </div>`;
    $("#show_customer_info").html(str);
    $("#MuiBox-list-customer").removeClass("hide");
    searchCustomer();
}

function getCustomerById(idCustomer) {
    return $.ajax({
        headers: {
            accept: "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: `${location.origin}/api/customers/` + idCustomer
    })
        .done((data) => {
            customer = data;
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function getAllEmployees() {
    return $.ajax({
        headers: {
            accept: "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: `${location.origin}/api/employees`
    })
        .done((data) => {
            employeeResult = data;
            $.each(data, (i, item) => {
                let str = `<option value="${item.id}">${item.name}</option>`;
                $("#selectEmployee").append(str);
                $("#selectEmployeeUpdate").append(str);
            })

        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function editCustomer() {
    let id = $("#idCustomer").val();
    $('#idCustomerUpdate').val(customer.id);
    $("#nameUpdate").val(customer.fullName)
    $('#phoneUpdate').val(customer.phoneNumber);
    $('#addressUpdate').val(customer.addresses[0].line1);
    $('#provinceUpdate').val(customer.addresses[0].provinceId);
    getAllDistrictsByProvinceId(customer.addresses[0].provinceId).then(() => {
        $("#districtUpdate").val(customer.addresses[0].districtId);
        getAllWardsByDistrictId(customer.addresses[0].districtId).then(() => {
            $("#wardUpdate").val(customer.addresses[0].wardId);


        })
    })
    $("#codeUpdate").val(customer.customerCode);
    $("#selectEmployeeUpdate").val(customer.employeeId);

    $("#update_order_customer").modal("show");
}



function showProductInfo(productId) {
    renderSaleOrderItem(productId, "plus");
    renderSaleOrder();
}

function removeProduct(id) {
    if (id === undefined) {
        $("#divNoInfo").removeClass('hide').addClass('show');
        $("#vat_tax").remove();
    }
    $("#tr_" + id).remove();
}

function discountProduct(event) {
    let productId = event.target.parentElement.getAttribute("data-product-discount-id");
    let str = `
                <div class="MuiPaper-root jss1041 MuiPaper-elevation3 MuiPaper-rounded hidden_discount"
                style="width: 190px;position: absolute;margin-left: -37px;"
                data-popper-reference-hidden="false" data-popper-escaped="false" data-popper-placement="bottom">
                <div id="arrow" style="position: absolute;left: 0px;transform: translate(95px, 0px);"></div>
                <div class="MuiBox-root jss4417">
                    <div class="MuiBox-root jss4418">
                        <div class="MuiBox-root jss4419">
                            <div class="MuiToggleButtonGroup-root jss1283" role="group"><button
                                    class="MuiButtonBase-root MuiToggleButton-root MuiToggleButtonGroup-grouped Mui-selected" id="value_discount_${productId}" 
                                    tabindex="0" type="button" value="VALUE" aria-pressed="true"><span
                                        class="MuiToggleButton-label">Giá trị</span><span
                                        class="MuiTouchRipple-root"></span></button>
                                        <button
                                    class="MuiButtonBase-root MuiToggleButton-root MuiToggleButtonGroup-grouped" tabindex="0" id="rate_discount_${productId}"
                                    type="button" value="PERCENT" aria-pressed="false"><span
                                        class="MuiToggleButton-label">%</span><span class="MuiTouchRipple-root"></span></button>
                            </div>
                            <div class="MuiFormControl-root jss4241 jss4243 jss1284" style="width: 92px; margin-left: 7px;">
                                <div class="MuiFormControl-root MuiTextField-root jss4244" inputmode="numeric">
                                    <div
                                        class="MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl">
                                        <input id="discount_product_input" aria-invalid="false" autocomplete="off" name="c2d2892e-316b-4ed0-aee6-1744e98c2a78"
                                            type="text" class="MuiInputBase-input MuiInput-input" value="0"
                                            style="width: 100%; text-align: right;">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>   
    `;
    $(`#td-discount-${productId}`).append(str);
}

const addQuantity = (productId) => {
    renderSaleOrderItem(productId, "plus",0);
    renderSaleOrder();
}

const editQuantity = (id) =>{
    let valueNumber = document.querySelector(`#quantity_product_${id}`).value;
    renderSaleOrderItem(id, "change",valueNumber);
    renderSaleOrder();
}


function minusQuantity(productId) {
    renderSaleOrderItem(productId, "minus",0);
    renderSaleOrder();
    let saleOrderItem = saleOrderItems.find(saleOrderItem => saleOrderItem.productId === productId);
    if (saleOrderItem.quantity === 0) {
        Swal.fire({
            text: "Bạn chắc chắn muốn bỏ sản phẩm này ra khỏi đơn hàng không?",
            icon: 'warning',
            showCancelButton: true,
            cancelButtonText: "Hủy",
            confirmButtonColor: '#d33',
            confirmButtonText: 'Đồng ý'
        }).then((result) => {
            if (result.isConfirmed) {
                deleteProduct(productId);
            }
        })
    }

}

function deleteProduct(productId) {
    $("#tr_" + productId).remove();
    iziToast.success({
        title: 'OK',
        position: 'bottomLeft',
        timeout: 1500,
        message: 'Xóa sản phẩm khỏi đơn hàng thành công!'
    });
    $(`#tax_${productId}`).remove();
    saleOrderItems.splice(saleOrderItems.findIndex(saleOrderItem => saleOrderItem.productId === productId), 1);
    renderSaleOrder();
}

const switchDiscountOrder = (value) => {
    $(`[value=${value}]`).addClass("Mui-selected");
    $(`[value=${value === "PERCENT" ? "VALUE" : "PERCENT"}]`).removeClass("Mui-selected")

}

const switchDiscountOrderItem = (event) => {
    const classList = [...event.target.parentElement.classList] || [];
    const muiSelecteds = event.target.parentElement.parentElement.children;
    const muis = [...muiSelecteds];
    if (classList.includes("Mui-selected")) {
        return;
    }
    muis.forEach((mui, index) => {
        if (mui) {
            mui.classList.remove("Mui-selected");
        }
    })
    event.target.parentElement.classList.add("Mui-selected");
}

const formatDiscount = (event, productId, retailPrice) => {
    // $("#discount_product_input").on('keydown', function () {
    //     //     var n = parseInt($(this).val().replace(/\D/g, ''), 10);
    //     //     $(this).val(n.toLocaleString());
    //     // });
    event.target.value = event.target.value.replace(/[^0-9]/g, '');
    const btnValueSelectors = event.target.parentElement.parentElement.parentElement.parentElement.children[0].children;
    const btnValue = [...btnValueSelectors];
    btnValue.forEach((mui, index) => {
        const classLists = [...mui.classList];
        if (classLists.includes("Mui-selected")) {
            const valueInput = event.target.value;
            const quantity = document.querySelector(`#quantity_product_${productId}`).value;
            const total = retailPrice * quantity;
            let totalAfterDiscount, percentValue, discount;
            if (mui.value === "VALUE") {
                totalAfterDiscount = (retailPrice - valueInput) * quantity;
                percentValue = (valueInput * 100) / total;
                discount = total - totalAfterDiscount;
            } else {
                discount = total * (valueInput / 100);
                percentValue = valueInput;
                totalAfterDiscount = total - discount;
            }

            let saleOrderItem = saleOrderItems.find(saleOrderItem => saleOrderItem.productId === productId);
            saleOrderItem.discount = discount;
            renderAmountOrderItem(productId);
            renderSaleOrder();
            $(`#discount_value_${productId}`).text(discount.formatVND());
            $(`#percent_value_${productId}`).text(percentValue + "%");
        }
    })

}


function renderAmountOrderItem(productId) {
    let saleOrderItem = saleOrderItems.find(saleOrderItem => saleOrderItem.productId === productId);
    let amount = (saleOrderItem.price * saleOrderItem.quantity) - saleOrderItem.discount;
    $(`#amount_product_${productId}`).text(amount.formatVND());
}

function renderSaleOrderItem(productId, operator, valueNumber) {
    productId = parseInt(productId);
    let saleOrderItem = saleOrderItems.find(saleOrderItem => saleOrderItem.productId === productId);
    if (saleOrderItem !== undefined) {
        if (valueNumber !== 0)
            saleOrderItem.quantity = valueNumber;
        if (operator === "minus")
            saleOrderItem.quantity -= 1;
        if (operator === "plus")
            saleOrderItem.quantity++;

        $(`#quantity_product_${productId}`).val(saleOrderItem.quantity);
        renderAmountOrderItem(productId);
    } else {
        let result = products.find(product => product.id === productId)
        let saleOrderItem = new SaleOrderItemParam(result.retailPrice, 1, result.id, 0)
        saleOrderItems.push(saleOrderItem);
        let htmlOrderItem = `
        <tr id="tr_${result.id}" class="MuiTableRow-root jss3894 jss3905 isNormalLineItem">
            <td class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter align-items-center">${result.id}</td>
            <td class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter align-items-center"><a
                    class="MuiTypography-root MuiLink-root MuiLink-underlineNone MuiTypography-colorPrimary align-items-center"
                    target="_blank" href="/admin/products/118801409/variants/185370765"><img class="jss3898"
                        src=${result.mediaList[0].fileUrl} alt="Sản phẩm"></a></td>
            <td class="MuiTableCell-root MuiTableCell-body align-items-center">
                <div class="MuiBox-root jss4053 jss3895 ">
                    <div class="MuiBox-root jss4054">
                        <div class="MuiBox-root jss4055">
                            <p class="MuiTypography-root MuiTypography-body1">${result.title}<button class="MuiButtonBase-root MuiIconButton-root" tabindex="0"
                                    type="button"
                                    style="padding: 0; margin-left: 6px; height: fit-content;"><span
                                        class="MuiIconButton-label"><svg viewBox="0 0 16 16" fill="none"
                                            xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            font-size="16"
                                            style="font-size: 16px; margin-top: -5px; cursor: pointer; color: rgb(0, 136, 255);">
                                            <path
                                                d="M7.4 5v1.2h1.2V5H7.4ZM7.4 8.6V11h1.8V9.8h-.6V7.4H6.8v1.2h.6Z"
                                                fill="currentColor"></path>
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                d="M8 2C4.688 2 2 4.688 2 8s2.688 6 6 6 6-2.688 6-6-2.688-6-6-6ZM3.2 8c0 2.646 2.154 4.8 4.8 4.8s4.8-2.154 4.8-4.8S10.646 3.2 8 3.2A4.806 4.806 0 0 0 3.2 8Z"
                                                fill="currentColor"></path>
                                        </svg></span><span class="MuiTouchRipple-root"></span></button></p>
                        </div>
                        <h6 class="MuiTypography-root MuiTypography-subtitle2 MuiTypography-colorTextSecondary"
                            style="font-weight: normal; margin: 2px 0;">${result.description}</h6><a
                            class="MuiTypography-root MuiLink-root MuiLink-underlineNone MuiTypography-colorPrimary"
                            target="_blank" href="/admin/products/118801409/variants/185370765"><span
                                class="MuiTypography-root MuiTypography-body1 MuiTypography-colorPrimary">${result.sku}</span></a><button
                            class="MuiButtonBase-root MuiButton-root MuiButton-text jss3896 btn-hover MuiButton-textPrimary MuiButton-textSizeSmall MuiButton-sizeSmall"
                            tabindex="0" type="button"><span class="MuiButton-label"><span
                                    class="MuiButton-startIcon MuiButton-iconSizeSmall"><svg viewBox="0 0 24 24"
                                        fill="none" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                                        font-size="20" color="primary">
                                        <path
                                            d="M19 3H5c-1.103 0-2 .897-2 2v16l4.8-3.6A2 2 0 0 1 9 17h10c1.103 0 2-.897 2-2V5c0-1.103-.897-2-2-2Zm0 12H8.334a2 2 0 0 0-1.2.4L5 17V5h14v10Z"
                                            fill="#0088FF"></path>
                                        <path d="M9 9h6v2H9V9Z" fill="#0088FF"></path>
                                    </svg></span>Ghi chú</span><span
                                class="MuiTouchRipple-root"></span></button>
                    </div>
                </div>
            </td>
            <td class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignCenter "
                style="width: 105px; padding-left: 0px; padding-right: 0px;">
                <div class="MuiBox-root jss4056 jss3910"><button 
                        class="MuiButtonBase-root MuiIconButton-root jss3912 icon-btn btn-subtract auto-hidden" onclick="minusQuantity(${result.id})"
                         id="minusQuantity_${result.id}"
                        tabindex="0" type="button"><span class="MuiIconButton-label"><svg
                                class="MuiSvgIcon-root MuiSvgIcon-fontSizeSmall" focusable="false"
                                viewBox="0 0 24 24" aria-hidden="true">
                                <path
                                    d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm5 11H7v-2h10v2z">
                                </path>
                            </svg></span><span class="MuiTouchRipple-root"></span></button>
                    <div class="MuiFormControl-root jss3914 jss3916 jss3911">
                        <div class="MuiFormControl-root MuiTextField-root jss3917" inputmode="numeric">
                            <div
                                class="MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl">
                                <input aria-invalid="false" autocomplete="off"
                                    oninput='editQuantity(${result.id})'
                                    name="" type="text"
                                    class="MuiInputBase-input MuiInput-input" value="1" min="1"
                                    style="text-align: center; width: 100%;"
                                    id="quantity_product_${result.id}">
                                </div>
                        </div>
                    </div><button
                        class="MuiButtonBase-root MuiIconButton-root jss3912 icon-btn btn-add auto-hidden" onclick="addQuantity(${result.id})"
                         id="addQuantity_${result.id}"
                        tabindex="0" type="button"><span class="MuiIconButton-label"><svg
                                class="MuiSvgIcon-root MuiSvgIcon-fontSizeSmall" focusable="false"
                                viewBox="0 0 24 24" aria-hidden="true">
                                <path
                                    d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm5 11h-4v4h-2v-4H7v-2h4V7h2v4h4v2z">
                                </path>
                            </svg></span><span class="MuiTouchRipple-root"></span></button>
                </div>
            </td>
            <td class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignRight align-items-center">
                <div class="MuiFormControl-root jss3914 jss3916" style="padding-top: 0px;">
                    <div class="MuiFormControl-root MuiTextField-root jss3917" inputmode="numeric"
                        data-for="tooltipTax-415457da-d825-4964-912f-10804128db81">
                        <div
                            class="MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl">
                            <input aria-invalid="false" autocomplete="off"
                                id="retailPrice_${result.id}"
                                name="" type="text"
                                class="MuiInputBase-input MuiInput-input" value=${result.retailPrice.formatVND()}
                                                                 
                                style="width: 100%; text-align: right"
                                ></div>
                    </div>
                </div>
            </td>
            <td id="td-discount-${result.id}" class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignRight">
                <button data-product-discount-id="${result.id}"
                    class="MuiButtonBase-root MuiButton-root MuiButton-text jss3901 btn-discount dropdown-toggle" 
                    tabindex="0" 
                        data-bs-auto-close="outside" 
                    type="button"
                         id="dropdownMenuButton1_${result.id}" data-bs-toggle="dropdown" aria-expanded="false"
                    >
                    <span class="MuiButton-label" id="discount_value_${result.id}" style="font-weight: 400;">0</span>
                        <p class="MuiTypography-root discount_rate_line_item MuiTypography-body1" id="percent_value_${result.id}"
                            style="color: rgb(255, 77, 77); font-size: 12px;">    
                        </p>
                </button>
              <div  aria-labelledby="dropdownMenuButton1" class="dropdown-menu MuiPaper-root jss1041 MuiPaper-elevation3 MuiPaper-rounded hidden_discount"
                style="width: 190px;position: absolute;margin-left: -37px;"
                data-popper-reference-hidden="false" data-popper-escaped="false" data-popper-placement="bottom">
                <div id="arrow" style="position: absolute;left: 0px;transform: translate(95px, 0px);"></div>
                <div class="MuiBox-root jss4417">
                    <div class="MuiBox-root jss4418">
                        <div class="MuiBox-root jss4419">
                            <div class="MuiToggleButtonGroup-root jss1283" role="group">
                            <button onclick="switchDiscountOrderItem(event)"
                                    class="MuiButtonBase-root MuiToggleButton-root MuiToggleButtonGroup-grouped Mui-selected "
                                    tabindex="0" type="button" value="VALUE" aria-pressed="true">
                                    <span class="MuiToggleButton-label">Giá trị</span>
                                    <span class="MuiTouchRipple-root"></span>
                            </button>
                            <button onclick="switchDiscountOrderItem(event)"
                                    class="MuiButtonBase-root MuiToggleButton-root MuiToggleButtonGroup-grouped" tabindex="0"
                                    type="button" value="PERCENT" aria-pressed="false"><span
                                    class="MuiToggleButton-label">%</span><span class="MuiTouchRipple-root"></span>
                            </button>
                            </div>
                            <div class="MuiFormControl-root jss4241 jss4243 jss1284" style="width: 92px; margin-left: 7px;">
                                <div class="MuiFormControl-root MuiTextField-root jss4244" inputmode="numeric">
                                    <div
                                        class="MuiInputBase-root MuiInput-root MuiInput-underline MuiInputBase-formControl MuiInput-formControl">
                                        <input id="discount_product_input_${result.id}"
                                               aria-invalid="false" 
                                               autocomplete="off" 
                                               name="numonly"
                                               type="text" 
                                               class="discount-item MuiInputBase-input MuiInput-input" value="0"
                                               onkeyup="formatDiscount(event,${result.id},${result.retailPrice})"
                                               style="width: 100%; text-align: right;">
                                    </div>
                                             <!--oninput="onInputDiscount(event,${result.id},${result.retailPrice})"-->
                                </div>
                            </div>
                        </div>
                    </div>   
                 </td>
            <td class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignRight " >
                <span id="amount_product_${result.id}" data-value="${product.retailPrice}" >${result.retailPrice.formatVND()}
                </span>
            </td>
            <td class="MuiTableCell-root MuiTableCell-body MuiTableCell-alignRight " style="padding-left: 0px;">
                <button
                    class="MuiButtonBase-root MuiIconButton-root MuiIconButton-colorSecondary MuiIconButton-sizeSmall"
                    tabindex="0" type="button">
                    <span class="MuiIconButton-label"><svg viewBox="0 0 24 24"
                            onclick="deleteProduct(${result.id})"
                            fill="none" xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                            font-size="20">
                            <path
                                d="M19 6.41 17.59 5 12 10.59 6.41 5 5 6.41 10.59 12 5 17.59 6.41 19 12 13.41 17.59 19 19 17.59 13.41 12 19 6.41Z"
                                fill="currentColor"></path>
                        </svg></span><span class="MuiTouchRipple-root"></span></button></td>
        </tr>
    `;
        $(".discount-item").on('input', function (e) {
            $(this).val($(this).val().replace(/[^0-9]/g, ''));
        });
        $("#tbProduct tbody").prepend(htmlOrderItem);
        $("#divNoInfo").addClass("hide");
        $("#divTbProduct").removeClass("hide");
    }
}

function renderSaleOrderTax() {
    taxSaleOrderMap.clear();
    saleOrderItems.forEach(saleOrderItem => {
        let p = products.find(product => product.id === saleOrderItem.productId);
        if (p.applyTax === undefined || p.applyTax === false)
            return;
        let totalAmount = p.retailPrice * saleOrderItem.quantity;
        totalAmount -= saleOrderItem.discount;
        p.taxSaleList.forEach((t) => {
            let taxAmount = (t.tax / 100) * totalAmount;
            if (taxSaleOrderMap.get(t.id) === undefined)
                taxSaleOrderMap.set(t.id, taxAmount);
            else {
                taxSaleOrderMap.set(t.id, taxSaleOrderMap.get(t.id) + taxAmount);
            }
        })

    });
    let taxListHtml = '';
    for (const [taxId, amount] of taxSaleOrderMap.entries()) {
        let tax = taxMap.get(taxId);
        taxListHtml += `
              <div>
                    <div class="MuiListItemText-root" style="float:left">                                        
                        <span class="MuiTypography-root MuiListItemText-primary MuiTypography-body1 MuiTypography-displayBlock">VAT (${tax.tax}%)</span>
                    </div>
                    <div class="MuiListItemText-root" style="float:right">
                        <span class="MuiTypography-root MuiListItemText-primary MuiTypography-body1 MuiTypography-alignRight MuiTypography-displayBlock">${amount.formatVND()}</span>
                    </div>
                    <div style="clear: both"></div>
              </div>`;
    }
    $("#ul_vat_tax").empty();
    $("#ul_vat_tax").prepend(taxListHtml);
}

function renderSaleOrder() {
    renderSaleOrderTax();
    let total = 0;
    let totalQuantity = 0;
    let totalTax = 0;
    let totalDiscount = 0;
    saleOrderItems.forEach(saleOrderItem => {
        total += saleOrderItem.quantity * saleOrderItem.price;
        totalQuantity += saleOrderItem.quantity;
        totalDiscount += saleOrderItem.discount;
    });

    for (const [taxId, amount] of taxSaleOrderMap.entries()) {
        totalTax += amount;
    }
    let subtotal = total - totalDiscount;
    let grandTotal = subtotal + totalTax;


    if (saleOrderItems.length === 0)
        $("#divNoInfo").removeClass("hide");
    $("#subtotal").text(subtotal.formatVND());
    $("#quantity_products").text("Tổng tiền (" + totalQuantity + " sản phẩm)");
    $("#grandTotal").text(grandTotal.formatVND());
    $("#totalPaid").text(grandTotal.formatVND());
}

function getProductList() {
    if (products !== undefined && products.length > 0) {
        return;
    }
    $.ajax({
        type: "GET",
        url: `${location.origin}/api/products`
    })
        .done((data) => {
            products = data;
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function getTaxList() {
    if (taxMap !== undefined) {
        return;
    }
    $.ajax({
        type: "GET",
        url: `${location.origin}/api/taxes`
    })
        .done((data) => {
            taxMap = new Map()
            data.forEach((t) => {
                taxMap.set(t.id, t);
            });
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function getCustomerList() {
    if (customers !== undefined && customers.length > 0) {
        return;
    }
    $.ajax({
        type: "GET",
        url: `${location.origin}/api/customers`
    })
        .done((data) => {
            customers = data;
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function numberOnly() {
    $("input[name='numonly']").on('input', function (e) {
        $(this).val($(this).val().replace(/[^0-9]/g, ''));
    });
}

function showAllCategory() {
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/categories"
    })
        .done((data) => {
            $("#categoryCreate").empty();

            $.each(data, (i, item) => {
                let str = `<option value="${item.id}">${item.title}</option>`;
                $(".showAllCategory").append(str);
            });

        })
        .fail((jqXHR) => {
            console.log("Error");
        })
}
function showAllEmployee(){
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/employees"
    })
        .done((data) => {
            $("#employeeCreate").empty();

            $.each(data, (i, item) => {
                let str = `<option value="${item.id}">${item.fullName}</option>`;
                $(".showAllEmployee").append(str);
            });

        })
        .fail((jqXHR) => {
            console.log("Error");
        })
}

function doCreateProductShort() {
    product.id = 0;
    product.title = $('#titleCreate').val();
    product.sku = $('#skuCreate').val();
    product.quantity = $('#inventoryCreate').val();
    product.retailPrice = $('#retailPriceCreate').val();
    product.importPrice = $('#importPriceCreate').val();
    product.categoryId = $('#categoryCreate').val();
    product.mass = $('#massCreate').val();

    $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json",
        },
        type: "POST",
        url: "http://localhost:8080/api/products/create-short",
        data: JSON.stringify(product),
    })
        .done((data) => {
            // getProductList();
            $('#create_order_product').modal("hide");
            $('#modalProductShort')[0].reset();
            App.SweetAlert.showSuccessAlert("Thêm thành công sản phẩm!");
        })
        .fail((jqXHR) => {
            console.log(jqXHR.message)
            App.SweetAlert.showErrorAlert("Thêm không được");
        })

}

$("#btnCreateProductShort").on("click", function () {
    doCreateProductShort();
})

$(".btn_create_order").on("click", () => {
    $.ajax({
        "headers": {
            "content-type": "application/json"
        },
        "type": "POST",
        "url": `${location.origin}/api/orders`,
        "data": JSON.stringify(saleOrder)
    })
        .done((data) => {
            App.IziToast.showSuccessAlert("Tạo đơn hàng thành công!");
            location.href = `${location.origin}/admin/orders/${data.id}`;
        })
        .fail((jqXHR) => {
            App.IziToast.showErrorAlert("Không đủ số lượng cho đơn hàng, vui lòng kiểm tra lại");
        })
});

$("#discount_product_input").on('keyup', function () {
    var n = parseInt($(this).val().replace(/\D/g, ''), 10);
    $(this).val(n.toLocaleString());
});

$(() => {
    getCustomerList();
    getTaxList();
    getProductList();
    getAllItem();
    getAllEmployees();
    searchCustomer();
    handleCloseListCustomers()
    handleCloseListProducts();
    searchProduct();
    showAllCategory();
    showAllEmployee();
})




