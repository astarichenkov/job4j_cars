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

    <script>
        $(document).ready(function () {
            clearOptionsFast("marksList")
            fillMarks();
            fillModels(1)
        });

        function clearOptionsFast(id) {
            var selectObj = document.getElementById(id);
            var selectParentNode = selectObj.parentNode;
            var newSelectObj = selectObj.cloneNode(false); // Make a shallow copy
            selectParentNode.replaceChild(newSelectObj, selectObj);
            return newSelectObj;
        }

        function fillModels(value) {
            console.log(value)
            clearOptionsFast("modelsList");
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/models?id=' + value,
                dataType: 'json'
            }).done(function (data) {
                for (let i = 0; i < data.length; i++) {
                    let el = data[i];
                    let option = document.createElement("option");
                    option.text = el.name;
                    option.value = el.id;
                    let select = document.getElementById("modelsList");
                    select.appendChild(option);
                }
            }).fail(function (err) {
                console.log(err);
            });
        }

        function fillMarks() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/marks',
                dataType: 'json'
            }).done(function (data) {
                for (let i = 0; i < data.length; i++) {
                    let el = data[i];
                    let option = document.createElement("option");
                    option.text = el.name;
                    option.value = el.id;
                    let select = document.getElementById("marksList");
                    select.appendChild(option);
                }
            }).fail(function (err) {
                console.log(err);
            });
        }


        function sout(value) {
            alert(value);
            clearOptionsFast("modelsList");
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
                        <select class="form-control" id="marksList" name="mark" onchange="fillModels(this.value)">
<%--                            <option></option>--%>
                        </select>

                        <label>Модель автомобиля</label>
                        <select class="form-control" id="modelsList" name="model">
                            <option></option>
                        </select>

                        <label>Тип кузова</label>
                        <select class="form-control" id="bodiesList" name="bodytype" onchange="fillModels(this.value)">
                            <option>123</option>
                            <option>456</option>
                        </select>

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