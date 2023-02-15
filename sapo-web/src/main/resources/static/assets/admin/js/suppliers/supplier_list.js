"use strict";

(() => {
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "GET",
        "url": App.PRODUCT_API

    })
    .done((data) => {
        $.each(data, (i, item) => {
            $("tbody").append(`
                <tr>
                    <td class="select">
                        <div class="next-input-wrapper">
                            <input type="checkbox" class="bulk-action-item next-checkbox" value="${item.id}"/>
                            <span class="next-checkbox--styled">
                                <svg class="checkmark next-icon next-icon--size-10">
                                    <use xlink:href="#next-checkmark-thick" xmlns:xlink="http://www.w3.org/1999/xlink"/>
                                </svg>
                            </span>
                        </div>
                    </td>
                    <td>
                        <a href="/admin/suppliers/${item.id}/histories" title="${item.supplierCode}">${item.supplierCode}</a>
                    </td>
                    <td>
                        <span title="${item.name}">${item.name}</span>
                    </td>
                    <td>
                        <span title="${item.email}">${item.email}</span>
                    </td>
                    <td>
                        <span title="${item.phone}">${item.phone}</span>
                    </td>
                    <td>
                        <span class="${item.supplierStatus === 'Đang giao dịch' ? 'text-success' : 'text-danger'}" title="${item.supplierStatus}">${item.supplierStatus}</span>
                    </td>
                    <td>
                        <span title="${item.name}">${item.name}</span>
                    </td>
                    <td>
                        <span title="${item.createAt}">${item.createdAt}</span>
                    </td>
                    <td>
                        <span title="${item.updateAt}">${item.updatedAt}</span>
                    </td>
                </tr>
            `);
        });
    })
    .fail(() => console.log("An error occurred, can't get supplier list!"));
})();