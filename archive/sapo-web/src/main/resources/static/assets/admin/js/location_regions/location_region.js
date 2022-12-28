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
        try {
            $("#province").append(`<option value="" disabled selected>Chọn tỉnh thành phố</option>`);
            $.each(data.results, (i, item) => $("#province").append(`<option value="${item.province_id}">${item.province_name}</option>`));
            $("#district").empty();
        } catch (exception) {
            console.log("An exception occurred: " + exception);
        }
    })
    .fail(() => console.log("An error occurred, can't get province list API"));
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
        try {
            $("#district").append(`<option value="" disabled selected>Chọn quận huyện</option>`);
            $.each(data.results, (i, item) => $("#district").append(`<option value="${item.district_id}">${item.district_name}</option>`));
            $("#ward").empty();
        } catch (exception) {
            console.log("An exception occurred: " + exception);
        }
    })
    .fail(() => console.log("An error occurred, can't get district list API"));
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
        try {
            $("#ward").append(`<option value="" disabled selected>Chọn phường xã</option>`);
            $.each(data.results, (i, item) => $("#ward").append(`<option value="${item.ward_id}">${item.ward_name}</option>`));
        } catch (exception) {
            console.log("An exception occurred: " + exception);
        }
    })
    .fail(() => console.log("An error occurred, can't get ward list API"));
}

findAllProvince().then(() => {
    findAllDistrictByProvinceId($("#province").val()).then(() => {
        findAllWardByDistrictId($("#district").val());
    });
});

$("#province").on("change", () => {
    // $("#district").empty();
    findAllDistrictByProvinceId($("#province").val()).done(() => {
        findAllWardByDistrictId($("#district").val());
    });
    $("#district").on("change", () => {
        findAllWardByDistrictId($("#district").val());
    });
});



