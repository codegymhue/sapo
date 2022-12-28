(() => {
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "get",
        "url": "http://localhost:8080/api/employees/findAll"
    })
    .done((data) => {
        $.each(data, (i, item) => {
            $("#employee").append(`<option value="${item.name}">${item.name}</option>`);
        });
    })
    .fail((jqXHR) => {
        console.log("An error occurred, can not get employees list: " + jqXHR.toString());
    });
})();

(() => {
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "get",
        "url": "http://localhost:8080/api/admin/payment_methods"
    })
    .done((data) => {
        $.each(data, (i, item) => {
            $("#payment_method").append(`<option value="${item.id}">${item.title}</option>`);
        });
    })
    .fail((jqXHR) => {
        console.log("An error occurred, can not get payment methods list: " + jqXHR.toString());
    });
})();

const findAllProvince = () => {
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "get",
        "url": "https://vapi.vnappmob.com/api/province/"
    })
    .done((data) => {
        $.each(data.results, (i, item) => {
            $("#province").append(`<option value="${item.province_id}">${item.province_name}</option>`);
        });

        $("#district").empty();
        $("#ward").empty();
    })
    .fail((jqXHR) => {
        console.log("An error occurred, can not get provinces list: " + jqXHR.toString());
    });
}

const findAllDistrictByProvinceId = (provinceId) => {
    $("#district").empty();
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "get",
        "url": "https://vapi.vnappmob.com/api/province/district/" + provinceId
    })
    .done((data) => {
        $.each(data.results, (i, item) => {
            $("#district").append(`<option value="${item.district_id}">${item.district_name}</option>`);
        });
    })
    .fail((jqXHR) => {
        console.log("An error occurred, can not get districts list: " + jqXHR.toString());
    });
}

const findAllWardByDistrictId = (districtId) => {
    $("#ward").empty();
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "get",
        "url": "https://vapi.vnappmob.com/api/province/ward/" + districtId
    })
    .done((data) => {
        $.each(data.results, (i, item) => {
            $("#ward").append(`<option value="${item.ward_id}">${item.ward_name}</option>`);
        });
    })
    .fail((jqXHR) => {
        console.log("An error occurred, can not get wards list: " + jqXHR.toString());
    });
}

findAllProvince().then(() => {
   findAllDistrictByProvinceId($("#province").val()).then(() => {
       findAllWardByDistrictId($("#district").val());
   });
});

$("#province").on("change", () => {
    $("#district").empty();
    findAllDistrictByProvinceId($("#province").val()).done(() => {
        findAllWardByDistrictId($("#district").val());
    });
    $("#district").on("change", () => {
        findAllWardByDistrictId($("#district").val());
    });
});