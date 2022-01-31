<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<%@ page import="ru.job4j.cars.model.Ad" %>
<%@ page import="ru.job4j.cars.model.Car" %>
<%@ page import="ru.job4j.cars.model.City" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.cars.repository.AdRepository" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="icon" href="favicon.ico" type="image/x-icon">

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
            clearOptions("marksList")
            fillMarks();
            fillModels(1);
            fillBodies();
            fillCities()
        });

        function clearOptions(id) {
            let selectObj = document.getElementById(id);
            let selectParentNode = selectObj.parentNode;
            let newSelectObj = selectObj.cloneNode(false);
            selectParentNode.replaceChild(newSelectObj, selectObj);
            return newSelectObj;
        }

        function fillOptions(url, id) {
            $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json'
            }).done(function (data) {
                for (let i = 0; i < data.length; i++) {
                    let el = data[i];
                    let option = document.createElement("option");
                    option.text = el.name;
                    option.value = el.id;
                    let select = document.getElementById(id);
                    select.appendChild(option);
                }
            }).fail(function (err) {
                console.log(err);
            });
        }

        function fillBodies() {
            let url = 'http://localhost:8080/cars/bodies';
            let id = "bodiesList"
            fillOptions(url, id);
        }

        function fillMarks() {
            let url = 'http://localhost:8080/cars/marks';
            let id = "marksList"
            fillOptions(url, id);
        }

        function fillModels(value) {
            clearOptions("modelsList");
            let url = 'http://localhost:8080/cars/models?id=' + value;
            let id = "modelsList"
            fillOptions(url, id);
        }

        function fillCities() {
            let url = 'http://localhost:8080/cars/cities';
            let id = "citiesList"
            fillOptions(url, id);
        }

        function saveAd() {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/cars/ads',
                data: JSON.stringify({
                    name: $('#name').val(),
                    description: $('#description').val(),
                    mark: $('#marksList').val(),
                    model: $('#modelsList').val(),
                    body: $('#bodiesList').val(),
                    power: $('#power').val(),
                    year: $('#year').val(),
                    mileage: $('#mileage').val(),
                    isBroken: $('#isBroken').is(':checked'),
                    price: $('#price').val(),
                    city: $('#citiesList').val(),
                }),
                dataType: 'text',
                success: function (data) {
                    window.location.href = "http://localhost:8080/cars/upload.jsp?id=" + data;
                }
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
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="edit.jsp">Добавить объявление</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="user-ads.jsp">Мои объявления</a>
            </li>
            <c:if test="${user != null}">
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">
                        <c:out value="${user.name}"/> | Выйти
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>

<div class="container pt-3">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Создать объявление.
            </div>
            <div class="card-body">
                <form action="" method="post" enctype="application/x-www-form-urlencoded">
                    <%--                <form action="${pageContext.request.contextPath}/photo-upload.do" method="post" enctype="application/x-www-form-urlencoded">--%>
                    <%--                <form action="${pageContext.request.contextPath}/ads" method="post" enctype="multipart/form-data">--%>
                    <div class="form-group">

                        <label>Описание</label>
                        <input type="text" class="form-control" name="description" id="description" value="TEST">
                        <br>
                        <label>Марка автомобиля</label>
                        <select class="form-control" id="marksList" name="mark" value="1"
                                onchange="fillModels(this.value)"></select>

                        <label>Модель автомобиля</label>
                        <select class="form-control" id="modelsList" name="model" value="1"></select>

                        <label>Тип кузова</label>
                        <select class="form-control" id="bodiesList" name="body" onchange="" value="1"></select>

                        <label>Мощность двигателя</label>
                        <input type="text" class="form-control" name="power" id="power" value="177">

                        <label>Год выпуска</label>
                        <input type="text" class="form-control" name="year" id="year" value="2000">

                        <label>Пробег</label>
                        <input type="text" class="form-control" name="mileage" id="mileage" value="273000">

                        <label>Битая</label>
                        <input type="checkbox" class="form-control" name="isBroken" id="isBroken">

                        <label>Цена</label>
                        <input type="text" class="form-control" name="price" id="price" value="120000">

                        <label>Город</label>
                        <select class="form-control" id="citiesList" name="city" value="1"></select>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="saveAd()">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>