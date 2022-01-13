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
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/basic',
                dataType: 'json'
            }).done(function (data) {
                let cities = data.cities
                for (let city of cities) {
                    let option = document.createElement("option");
                    option.text = city.name;
                    option.value = city.name;
                    let select = document.getElementById("citiesList");
                    select.appendChild(option);
                }
            }).fail(function (err) {
                console.log(err);
            });
        });
    </script>


    <title>Добавить объявление</title>
</head>
<body>

<%
    List<City> cities = AdRepository.instOf().getAllCities();
%>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Создать объявление.
            </div>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <label>Название объявления</label>
                        <input type="text" class="form-control">
                        <label>Описание</label>
                        <input type="text" class="form-control">
                        <br>
                        <label>Марка автомобиля</label>
                        <input type="text" class="form-control">
                        <label>Модель автомобиля</label>
                        <input type="text" class="form-control">
                        <label>Тип кузова</label>
                        <input type="text" class="form-control">
                        <label>Мощность двигателя</label>
                        <input type="text" class="form-control">
                        <label>Цена</label>
                        <input type="text" class="form-control">
                        <label>Город</label>
                        <select class="form-control" id="citiesList" name="city">


                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>