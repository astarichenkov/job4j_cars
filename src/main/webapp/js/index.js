function refreshTable() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/ads',
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
            // let user = item.author;
            let user = "";
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
                // '<td>' + item.id + '</td>' +
                '<td>' + item.description +
                // <div>
                //
                // </div>

                '</td>' +
                '<td>' + item.date + '</td>' +
                '<td>' + item.phone + '</td>' +
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

