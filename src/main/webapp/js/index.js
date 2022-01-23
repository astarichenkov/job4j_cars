function refreshTable() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/user-ads',
        dataType: 'json'
    }).done(function (data) {
        let checkbox = document.getElementById('showAllCheckbox');
        for (let item of data) {
            if (!checkbox.checked && item.done) {
                continue;
            }
            let td = '<td></td>'
            if (item.done) {
                td = '<td>' +
                    '<svg width="3em" height="3em" viewBox="0 0 16 16" className="bi bi-check2-all" fill="#00B74A" xmlns="http://www.w3.org/2000/svg">' +
                    '<path fill-rule="evenodd" d="M12.354 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>' +
                    '<path d="M6.25 8.043l-.896-.897a.5.5 0 1 0-.708.708l.897.896.707-.707zm1 2.414l.896.897a.5.5 0 0 0 .708 0l7-7a.5.5 0 0 0-.708-.708L8.5 10.293l-.543-.543-.707.707z"/>' +
                    '</svg>' +
                    '</button>' +
                    '</td>';
            } else {
                td = '<td><button class="btn btn-light" onclick="changeStatus(this.value)" value="' + item.id + '">Выполнить</button></td>';
            }
            let user = item.author;
            let cat = "";
            // if (arrayLength !== 0) {
            //     for (let i = 0; i < arrayLength; i++) {
            //         cat = cat + item.categories[i].name
            //         if (i !== arrayLength - 1) {
            //             cat = cat + " / "
            //         }
            //     }
            // }
            // let arrayLength = item.categories.length;
            $('#table tr:last').after('<tr>' +
                '<td>' + item.id + '</td>' +
                '<td>' + item.description + '</td>' +
                '<td>' + item.created + '</td>' +
                '<td>' + user.name + '</td>' +
                '<td>' + cat + '</td>' +
                td +
                '</tr>');
        }
    }).fail(function (err) {
        console.log(err);
    });
}




$(document).ready(function () {
    refreshTable()
});

// $(document).ready(function () {
//     $.ajax({
//         type: 'GET',
//         url: 'http://localhost:8080/cars/ads',
//         dataType: 'json'
//     }).done(function (data) {
//         for (let category of data) {
//             let option = document.createElement("option");
//             option.text = category.name;
//             option.value = category.id;
//             let select = document.getElementById("cIds");
//             select.appendChild(option);
//         }
//     }).fail(function (err) {
//         console.log(err);
//     });
// });


// function checkBox() {
//     removeTable()
//     refreshTable();
// }
//
// function removeTable() {
//     $('#table td').remove();
// }
//
// function saveItem() {
//     let categories = getSelectValues();
//     $.ajax({
//         type: 'POST',
//         url: 'http://localhost:8080/todo/items',
//         data: JSON.stringify({
//             description: $('#description').val(),
//             categories: categories
//         }),
//         dataType: 'json'
//     }).done(function () {
//         removeTable();
//         refreshTable();
//     }).fail(function (err) {
//         console.log(err);
//     });
// }
//
// function getSelectValues() {
//     let select = document.getElementById("cIds")
//     let result = [];
//     let options = select && select.options;
//     let opt;
//
//     for (let i = 0, iLen = options.length; i < iLen; i++) {
//         opt = options[i];
//         if (opt.selected) {
//             let obj = {
//                 id: opt.value,
//                 name: opt.text
//             }
//             result.push(obj)
//         }
//     }
//     return result;
// }
//
// function refreshTable() {
//     $.ajax({
//         type: 'GET',
//         url: 'http://localhost:8080/todo/items',
//         dataType: 'json'
//     }).done(function (data) {
//         let checkbox = document.getElementById('showAllCheckbox');
//         for (let item of data) {
//             if (!checkbox.checked && item.done) {
//                 continue;
//             }
//             let td = '<td></td>'
//             if (item.done) {
//                 td = '<td>' +
//                     '<svg width="3em" height="3em" viewBox="0 0 16 16" className="bi bi-check2-all" fill="#00B74A" xmlns="http://www.w3.org/2000/svg">' +
//                     '<path fill-rule="evenodd" d="M12.354 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>' +
//                     '<path d="M6.25 8.043l-.896-.897a.5.5 0 1 0-.708.708l.897.896.707-.707zm1 2.414l.896.897a.5.5 0 0 0 .708 0l7-7a.5.5 0 0 0-.708-.708L8.5 10.293l-.543-.543-.707.707z"/>' +
//                     '</svg>' +
//                     '</button>' +
//                     '</td>';
//             } else {
//                 td = '<td><button class="btn btn-light" onclick="changeStatus(this.value)" value="' + item.id + '">Выполнить</button></td>';
//             }
//             let user = item.user;
//             let cat = "";
//             let arrayLength = item.categories.length;
//             if (arrayLength !== 0) {
//                 for (let i = 0; i < arrayLength; i++) {
//                     cat = cat + item.categories[i].name
//                     if (i !== arrayLength - 1) {
//                         cat = cat + " / "
//                     }
//                 }
//             }
//             $('#table tr:last').after('<tr>' +
//                 '<td>' + item.id + '</td>' +
//                 '<td>' + item.description + '</td>' +
//                 '<td>' + item.created + '</td>' +
//                 '<td>' + user.name + '</td>' +
//                 '<td>' + cat + '</td>' +
//                 td +
//                 '</tr>');
//         }
//     }).fail(function (err) {
//         console.log(err);
//     });
// }
//

//
//
function changeStatus(val) {
    // $.ajax({
    //     type: 'GET',
    //     url: 'http://localhost:8080/todo/status',
    //     data: 'id=' + val,
    //     dataType: 'text'
    // }).done(function (data) {
    //     removeTable()
    //     refreshTable();
    // }).fail(function (err) {
    //     alert(err);
    // });
}