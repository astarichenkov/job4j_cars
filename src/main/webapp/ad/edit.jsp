<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.cars.model.Ad" %>
<%@ page import="ru.job4j.cars.model.Car" %>
<%@ page import="ru.job4j.cars.model.City" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.cars.model.AdRepository" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="../favicon.ico" type="image/x-icon">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <%--    <script>--%>
    <%--        $(document).ready(function () {--%>
    <%--            $.ajax({--%>
    <%--                type: 'GET',--%>
    <%--                url: 'http://localhost:8080/cars/basic',--%>
    <%--                dataType: 'json'--%>
    <%--            }).done(function (data) {--%>
    <%--                let cities = data.cities--%>
    <%--                for (let city of cities) {--%>
    <%--                    let option = document.createElement("option");--%>
    <%--                    option.text = city.name;--%>
    <%--                    option.value = city.name;--%>
    <%--                    let select = document.getElementById("citiesList");--%>
    <%--                    select.appendChild(option);--%>
    <%--                }--%>
    <%--                let bodies = data.bodies--%>
    <%--                for (let body of bodies) {--%>
    <%--                    let option = document.createElement("option");--%>
    <%--                    option.text = body.name;--%>
    <%--                    option.value = body.name;--%>
    <%--                    let select = document.getElementById("bodiesList");--%>
    <%--                    select.appendChild(option);--%>
    <%--                }--%>
    <%--            }).fail(function (err) {--%>
    <%--                console.log(err);--%>
    <%--            });--%>
    <%--        });--%>
    <%--    </script>--%>

    <script>
        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/basic',
                dataType: 'json'
            }).done(function (data) {
                let idList = ['modelsList', 'marksList', 'bodiesList', 'citiesList']
                let dataList = [data.models, data.marks, data.bodies, data.cities]

                for (let i = 0; i < idList.length; i++) {
                    for (let j = 0; j < dataList[i].length; j++) {
                        let option = document.createElement("option");
                        let el = dataList[i][j]
                        option.text = el.name;
                        option.value = el.name;
                        let select = document.getElementById(idList[i].toString());
                        select.appendChild(option);
                    }
                }
            }).fail(function (err) {
                console.log(err);
            });
        });



        function send() {
            let student = {
                    "id": 1,
                    "created": "Jan 9, 2022, 12:46:16 AM",
                    "price": 2000,
                    "description": "Продается ауди 123",
                    "photoId": 2,
                    "isSold": false,
                    "city": {
                        "id": 1,
                        "name": "Нижний Новгород"
                    },
                    "author": {
                        "id": 1,
                        "name": "Ivan",
                        "password": "",
                        "phone": "+7951234455"
                    },
                    "car": {
                        "id": 2,
                        "year": 0,
                        "mileage": 0,
                        "isBroken": false,
                        "engine": {
                            "id": 2,
                            "power": 249
                        },
                        "bodyType": {
                            "id": 2,
                            "name": "Hatchback"
                        },
                        "model": {
                            "id": 2,
                            "name": "A4"
                        },
                        "mark": {
                            "id": 1,
                            "name": "Audi"
                        },
                        "drivers": [
                            {
                                "id": 1,
                                "name": "Oleg"
                            },
                            {
                                "id": 3,
                                "name": "Ilya"
                            }
                        ]
                    }
                };
            let st = JSON.stringify(student);
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/cars/ads',
                data: st,


                // data: JSON.stringify({
                //     name: $('#name').val(),
                //     description: $('#description').val(),
                //     mark: $('#marksList').val(),
                //     model: $('#modelsList').val(),
                //     bodyType: $('#bodiesList').val(),
                //     power: $('#power').val(),
                //     price: $('#price').val(),
                //     city: $('#city').val(),
                // }),
                dataType: 'json'
            }).done(function (data) {
                <%--$('#emailList li:last').append(`<li>${data.name}</li>`)--%>
            }).fail(function (err) {
                console.log(err);
            });
        }
    </script>

    <title>Добавить объявление</title>
</head>
<body>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Создать объявление.
            </div>
            <div class="card-body">
                <form method="post">
                    <div class="form-group">
                        <label>Название объявления</label>
                        <input type="text" class="form-control" name="name" id="name">

                        <label>Описание</label>
                        <input type="text" class="form-control" name="description" id="description">
                        <br>
                        <label>Марка автомобиля</label>
                        <select class="form-control" id="marksList" name="mark"></select>

                        <label>Модель автомобиля</label>
                        <select class="form-control" id="modelsList" name="model"></select>

                        <label>Тип кузова</label>
                        <select class="form-control" id="bodiesList" name="bodytype"></select>

                        <label>Мощность двигателя</label>
                        <input type="text" class="form-control" name="power" id="power">

                        <label>Цена</label>
                        <input type="text" class="form-control" name="price" id="price">

                        <label>Город</label>
                        <select class="form-control" id="citiesList" name="city"></select>
                    </div>
                    <button type="submit" class="btn btn-primary" onclick="send()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>