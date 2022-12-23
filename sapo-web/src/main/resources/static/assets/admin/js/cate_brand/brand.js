
const  selectBtnBrand = document.querySelector('.select-btn-brand');
const  searchBrandBtn = document.querySelector('#inputSearchBrand')
//nhat-dev class brand
// class  Brand{
//     constructor(id,name) {
//         this.id = id;
//         this.name = name;
//     }
// }


//nhat-dev obj brand
// let brand = new Brand();

//show all brands
let brands;
function showAllBrands(selectedBrand) {
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/brands"
    })
        .done((data) => {
            brands = data;
            brands.forEach(item => {
                let isSelected = item === selectedBrand ? "selected" : "";
                let str = `<li id="${item.id}" data-id="${item.id}" onclick="updateNameBrand(${item.id})" class="${isSelected} brandItem dropdown-item">${item.title}</li>`;
                // options.insertAdjacentHTML("beforeend", li);
                $(".showAllBrand #showAllBrand").append(str);

            });

        })
        .fail((jqXHR) => {

        })
}

//update name cat
function updateNameBrand(id ) {
    const  arrayBrand = brands.filter(item => item.id === id)
    const val = arrayBrand[0].title;

    console.log("is", id , val)

    selectBtnBrand.firstElementChild.innerText= val;
    //add data-id on element span tag name
    $("#brandProType").data("id", id);

    let idValue = $("#brandProType").data("id");
    console.log(idValue)


    // console.log($("#getIdValueCat").prop('data-id', id))

}
//search cat
const  searchBrand = () => {
    $('#inputSearchBrand').on("input", function () {
        let searchWord = $('#inputSearchBrand').val();
        if(searchWord != null){
            let arr = [];
            brands.forEach(item => {
                console.log("item", item)
                if ((item.title).toLowerCase().includes(searchWord.toLowerCase())){

                    arr.push(item)
                }

            })
            console.log(arr)
            // $(".dropdown-menu").hide();
            $('.showAllBrand .brandItem ').remove();
            // render result item of searching
            $.each(arr, (i, item) => {
                console.log(item)
                let isSelected = item === selectBtnBrand.firstElementChild.innerText ? "selected" : "";
                let result =  `<li id="${item.id}" data-id="${item.id}" onclick="updateNameBrand(${item.id})" class="${isSelected} brandItem dropdown-item">${item.title}</li>`;
                console.log("result", result, brands)

                $(".showAllBrand").append(result);
            })
        }else {
            $('.showAllBrand .brandItem ').remove();
            $.each(brands, (i, item) => {
                console.log(item)
                let isSelected = item === selectBtnBrand.firstElementChild.innerText ? "selected" : "";
                let result =  `<li id="${item.id}" data-id="${item.id}" onclick="updateNameBrand(${item.id})" class="${isSelected} brandItem dropdown-item">${item.title}</li>`;

                console.log("result", result, brands)

                $(".showAllBrand").append(result);
            })
        }
        console.log(searchWord)

    })
}

//Nhat-dev show create brand modal

$('#btnCreateBrand').on('click', () => {
    $('#modalAddBrand').modal('show');
})



