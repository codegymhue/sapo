
const  selectBtnCat = document.querySelector('.select-btn-cat');
const  searchCatBtn = document.querySelector('#inputSearchCat')
//nhat-dev class category
// class  Category{
//     constructor(id,name) {
//         this.id = id;
//         this.name = name;
//     }
// }
// //nhat-dev obj category
// let category = new Category();

//nhat-dev show all categories
let categories;
function showAllCategories(selectedCategory) {
    return $.ajax({
        headers: {
            "accept": "application/json",
            "content-type": "application/json"
        },
        type: "GET",
        url: "http://localhost:8080/api/categories"
    })
        .done((data) => {
            categories = data;
            categories.forEach(item => {
                //nhat-dev select item and get it value
                let isSelected = item === selectedCategory ? "selected" : "";
                let str = `<li id="${item.id}" data-id="${item.id}" onclick="updateNameCat(${item.id})" class="${isSelected} catItem dropdown-item">${item.title}</li>`;
                // options.insertAdjacentHTML("beforeend", li);
                $(".showAllCategory #showAllCAt").append(str);
            });

        })
        .fail((jqXHR) => {

        })
}


//nhat-dev update name cat after selected
function updateNameCat(id ) {
   const  arrayCat = categories.filter(item => item.id === id)
    const val = arrayCat[0].title;

    console.log("is", id , val)

     selectBtnCat.firstElementChild.innerText= val;

    //add data-id on element span tag name
    $("#catProType").data("id", id);

    // $("#getIdValueCat").data("id", id);


    let idValue = $("#catProType").data("id");
    console.log(idValue);
    // $('#getIdValueCat').val(idValue);
    // console.log($('#getIdValueCat').val(idValue))


    // console.log($("#getIdValueCat").prop('data-id', id))

}
//search cat
const  searchCat = () => {
    $('#inputSearchCat').on("input", function () {
        let searchWord = $('#inputSearchCat').val();
        if(searchWord != null){
            let arr = [];
            categories.forEach(item => {
                // console.log("item", item)
                if ((item.title).toLowerCase().includes(searchWord.toLowerCase())){

                    arr.push(item)
                }

            })
            // console.log(arr)
            // $(".dropdown-menu").hide();

            $('.showAllCategory .catItem ').remove();

            // let  error = `<li class="dropdown-item catItem err">Ngu</li>`
            // if (arr !== searchWord){
            //     $(".showAllCategory").append(error);
            // }

            // render result item of searching
            $.each(arr, (i, item) => {
                // console.log(item)
                let isSelected = item === selectBtnCat.firstElementChild.innerText ? "selected" : "";
                let result =  `<li id="${item.id}" data-id="${item.id}" onclick="updateNameCat(${item.id})" class="${isSelected} catItem dropdown-item">${item.title}</li>`;
                // console.log("result", result, categories)
                $(".showAllCategory").append(result);
            })
        }else {
            $('li .err ').remove();
            $('.showAllCategory .err ').remove();
            $.each(categories, (i, item) => {
                // console.log(item)
                let isSelected = item === selectBtnCat.firstElementChild.innerText ? "selected" : "";
                let result =  `<li id="${item.id}" data-id="${item.id}" onclick="updateNameCat(${item.id})" class="${isSelected} catItem dropdown-item">${item.title}</li>`;

                // console.log("result", result, categories)

                $(".showAllCategory").append(result);
            })
        }
        // console.log(searchWord)

    })
}
//Nhat-dev show create category modal
$('#btnCreateCat').on('click', () => {
    $('#modalAddCategory').modal('show');
})



