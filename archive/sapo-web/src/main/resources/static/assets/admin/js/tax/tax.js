
const  selectBtnTax = document.querySelector('.select-btn-tax');
const  selectBtnTaxOut = document.querySelector('.select-btn-tax-out');
let taxes;
function showAllTaxes(selectedTax) {
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/taxes"
    })
        .done((data) => {
            taxes = data;
            taxes.forEach(item => {
                //nhat-dev select item and get it value
                let isSelected = item === selectedTax ? "selected" : "";
                let str = `<li id="ul1_${item.id}" data-id="${item.id}" onclick="updateNameTax(${item.id})" class="${isSelected} catItem dropdown-item">${item.title}</li>`;
                let strOut = `<li id="ul2_${item.id}" data-id="${item.id}" onclick="updateNameTaxOut(${item.id})" class="${isSelected} catItem dropdown-item">${item.title}</li>`;

                // options.insertAdjacentHTML("beforeend", li);
                $(".showAllTax #showALlTax").append(str);

                $(".showAllTaxOut #showALlTaxOut").append(strOut);

            });

        })
        .fail((jqXHR) => {

        })
}
//nhat-dev update name cat after selected
function updateNameTax(id ) {
    const  arrayTax = taxes.filter(item => item.id === id)
    const val = arrayTax[0].title;

    console.log("is", id , val)

    selectBtnTax.firstElementChild.innerText= val;

    //add data-id on element span tag name
    $("#taxProType").data("id", id);

    let idValue = $("#taxProType").data("id");
    console.log(idValue);
}
function updateNameTaxOut(idOut ) {
    const  arrayTaxOut = taxes.filter(item => item.id === idOut)
    const val = arrayTaxOut[0].title;

    console.log("is", idOut , val)

    selectBtnTaxOut.firstElementChild.innerText= val;

    //add data-id on element span tag name
    $("#taxProTypeOut").data("id", idOut);

    let idValue = $("#taxProTypeOut").data("id");
    console.log(idValue);
}

//Nhat-dev show create category modal
$('.btnCreateTax').on('click', () => {
    $('#modalAddTax').modal('show');
})


