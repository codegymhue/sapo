let supplier = new Supplier();
let locationRegionCreate = new LocationRegionCreate();

let suppliers = [];

function showListSupplier() {
    $.ajax({
        type: "GET", contentType: 'application/json', url: `${location.origin}/api/admin/suppliers`
    })
        .done((data) => {
            console.log(data)
            $(".searchSupplier").removeClass('d-none');
            $(".contentSupplier div").remove();
            $.each(data, (i, supplier) => {
                let result = `
                     <div class="collection-select clearfix single-suggest-select " id="item-suggest-suppliers-182236815" 
                     onclick="showSupplierInfo(${supplier.id})">
                        <a class="pull-left">
                           <img style="width:30px;float:left;padding-top:3px"
                               src="https://4ae.mysapogo.com/v2/images/customper-noavatar.png" >
                                 <div style="width:calc(100% - 30px);float:left;padding-left:10px">
                                      <p>${supplier.name}</p>
                                      <p>${supplier.phone}</p>
                                 </div>
                        </a>
                     </div>
                `;
                $(".contentSupplier").append(result)
            })

            handleCloseListSupplier();
        })
        .fail((jqXHR) => {
            console.log(jqXHR);
        })
}

function handleCloseListSupplier() {
    $(document).on("click", () => {
        // $(".contentCustomer div").empty();
        $(".searchSupplier").addClass('d-none');
    })
}

function getAllProvinces() {
    return $.ajax({
        headers: {
            "accept": "application/json", "content-type": "application/json"
        }, type: "GET", url: "https://vapi.vnappmob.com/api/province/"
    })
        .done((data) => {
            console.log(data)
            $.each(data.results, (i, item) => {
                let str = `<option value="${item.province_id}">${item.province_name}</option>`;
                $("#province").append(str);
            });
        })
        .fail((jqXHR) => {

        })
}

function getAllDistrictsByProvinceId(provinceId) {
    $("#district").empty();
    return $.ajax({
        headers: {
            "accept": "application/json", "content-type": "application/json"
        }, type: "GET", url: "https://vapi.vnappmob.com/api/province/district/" + provinceId
    })
        .done((data) => {
            console.log(data);
            if (data.results.length === 0) {
                let str = `<option value="0">Chọn Quận/Huyện</option>`;
                $("#district").append(str);
            } else {
                $.each(data.results, (i, item) => {
                    let str = ` <option value="${item.district_id}">${item.district_name}</option>`;
                    $("#district").append(str);
                })

            }
        })
        .fail((jqXHR) => {
        })
}

function getAllWardsByDistrictId(districtId) {
    $("#ward").empty();
    return $.ajax({
        headers: {
            "accept": "application/json", "content-type": "application/json"
        }, type: "GET", url: "https://vapi.vnappmob.com/api/province/ward/" + districtId
    })
        .done((data) => {
            console.log("districtId", districtId)
            if (data.results.length === 0) {
                let str = `<option value="0">Chọn Phường/Xã</option>`;
                $("#ward").append(str);
            } else {
                $.each(data.results, (i, item) => {
                    let str = `<option value="${item.ward_id}">${item.ward_name}</option>
                                `;
                    $("#ward").append(str);
                });
            }
        })
        .fail((jqXHR) => {
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


function showSupplierInfo(idSupplier) {

    $("#idSupplier").val(idSupplier);
    let result = {};

    suppliers.forEach((item) => {
        if (item.id == idSupplier) {
            result = item;
            return false;
        }
    })
    // console.log(result);
    let str = `
                         <div class="info-suppliers">
                            <div class="flex w-50 float-left">
                                <i class="fa fa-user flex green-sapo" style="font-size:18px"></i>
                                <a target="_blank" id="link-supplier" class="flex">
                                    <h4 class="ml-3 mb-3 green-sapo flex">
                                    ${result.name}
                                    </h4>
                                </a>
                                <a class="close ml-2 ">
                                    <svg class="next-icon next-icon--size-14 next-icon--slate-lighter"
                                    onclick="handleRemove()">
                                        <use xmlns:xlink="http://www.w3.org/1999/xlink"
                                             xlink:href="#next-remove"></use>
                                    </svg>
                                </a>
                            </div>
                            <div class="flex" style="float:right;">
                                <div class="debt-customer">
                                    <span>Công nợ hiện tại: </span><b id="supplier-debt"></b>
                                </div>
                            </div>

                            <div class="info-supplier-body page-info-body--sub mt-2 pb-0">
                                <div class="row mb-3">
                                    <div class="col-6">
                                        <label class="text-uppercase font-weight-500 mb-2">
                                            Địa chỉ xuất hàng
                                        </label>
                                        <div class="content mb-2" id="supplierAddress">
    
                                            <div class="w100">--</div>
                                            <div class="w100">0989989789</div>
                                            <div class="w100">22 Hai Bà Trưng</div>
                                            <div class="w100">
                                                <span>Phường Phúc Xá - </span>
                                                <span>Quận Ba Đình</span>
                                                <span> - Hà Nội</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <label class="text-uppercase font-weight-500 mb-2">
                                            Địa chỉ xuất hoá đơn
                                        </label>
                                        <div class="content mb-2" id="supplierAddress">
    
                                        <div class="w100">--</div>
                                        <div class="w100">0989989789</div>
                                        <div class="w100">22 Hai Bà Trưng</div>
                                        <div class="w100">
                                            <span>Phường Phúc Xá - </span>
                                            <span>Quận Ba Đình</span>
                                            <span> - Hà Nội</span>
                                        </div>
                                    </div>
<!--                                        <a href="javascript:" class="btn-show-list-address"-->
<!--                                           id="btn-show-list-address-billing" data-toggle="popover"-->
<!--                                           data-original-title="" title="">Thay đổi địa chỉ</a>-->
                                        <div class="popover-address-wrap" id="popover-address-billing-wrap">
                                        </div>

                                    </div>
                                </div>
                                <div class="row mb-3 ">

                                    <div class="col-6">
                                        <label class="text-uppercase font-weight-500 mb-2">
                                            Email
                                        </label>
                                        <div class="content mb-2">
                                            <input type="email" class="sapo-textbox" data-toggle="tooltip"
                                                   data-original-title="" value="fx@gmail.com">
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
    `;

    $("#show_supplier_info").html(str);

}

function handleRemove(){
    let str = `<div class="page-info-body" style="display: inline-block;" id="show_supplier_info">
                        <div class="search-layout-common search-supplier"  id="search-supplier-wrap" onclick="showListSupplier()">
                            <i class="fa fa-search" style=""></i>
                            <input type="text"
                                   class="sapo-textbox search select--a w-100 mb-0 " id="search-supplier"
                                   placeholder="Tìm kiếm nhà cung cấp theo SĐT, tên, mã nhà cung cấp ... (F4)"

                                   autocomplete="off" data-original-title="" title="">

<!--                            modal show list supplier-->

                            <div class="filter-body--suggest d-none searchSupplier" >
                                <div class="popover fade show bs-popover-top " role="tooltip" id="popover276985"
                                     x-placement="top"
                                     style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(0px, -182px, 0px);">
                                    <div class="arrow" style="left: 331px;"></div>
                                    <div class="popover-body">
                                        <svg class="next-icon next-icon--no-nudge load-filter"
                                             style="margin: 10px 0px; display: none;">

                                        </svg>
                                        <div class="dropdown-filter" style="">
                                            <div class="single-suggest-result newscroll">
                                                <button data-bs-toggle="modal" data-bs-target="#create_supplier" class="collection-select clearfix single-suggest-select"
                                                        style="width: 100%; border: none">
                                                    <a class="pull-left">
                                                        <img style="width:30px;float:left;padding-top:3px"
                                                             src="https://4ae.mysapogo.com/v2/images/create_customer.svg">
                                                        <div  style="width:calc(100% - 30px);float:left;padding:6px 0 0 10px">
                                                            <p>Thêm mới nhà cung cấp</p>
                                                        </div>
                                                    </a>
                                                </button>
                                                <div type="button" class="collection-select clearfix single-suggest-select contentSupplier"
                                                     id="item-suggest-suppliers-182236815" style="background-color: white">


                                                </div>
                                            </div>
                                            <div class="dropdown-paginate pull-right text-right" style="margin:0">
                                                <a class="btn-prev btn-page btn btn-default disabled">
                        <span class="page-prev">
                            <svg role="img" class="next-icon next-icon--rotate-270 next-icon--size-16">

                            </svg>
                        </span>
                                                </a>
                                                <a class="btn-next btn-page btn btn-default disabled">
                        <span class="page-next">
                            <svg role="img" class="next-icon next-icon--rotate-90 next-icon--size-16">
                                <use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#arrow-detailed"></use>
                            </svg>
                        </span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <a class="modal_source display-popUp" type="text/html" style="display: none"
                               define="{showDisplayPopUp: new Components.Modal(this)}"></a>
                        </div>
<!--                        modal info supplier-->

                    </div>`;
    $("#show_supplier_info").html(str);

}