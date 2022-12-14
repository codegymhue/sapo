const deleteById = (supplierId) => {
    $("#ward").empty();
    return $.ajax({
        "headers": {
            "accept": "application/json",
            "content-type": "application/json"
        },
        "type": "delete",
        "url": "http://localhost:8080/api/admin/suppliers/" + supplierId + "/delete"
    })
    .done(() => {
        window.location.href="/admin/suppliers";
    })
    .fail((jqXHR) => {
        console.log("An error occurred, can not delete supplier with id: " + supplierId);
    });
}

$("#btn-supplier-delete").on("click", () => {
    deleteById($("#btn-supplier-delete").val());
});