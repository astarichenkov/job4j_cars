<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/main.css">
    <link rel="icon" href="./favicon.ico" type="image/x-icon">
    <link href="./css/ad-view.css" rel="stylesheet"/>
    <link href="./lightbox2/css/lightbox.css" rel="stylesheet"/>


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
    <script src="./lightbox2/js/lightbox.js"></script>

    <script>

        $(document).ready(function () {
            refreshTable()
        });

        function showPhone(value) {
            let btn = document.getElementById("phone");
            btn.innerHTML = value;
        }

        function refreshTable() {
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/ads?id=' + id,
                dataType: 'json'
            }).done(function (data) {
                for (let item of data) {
                    // let car = item.car;
                    let isBroken = 'нет'
                    if (item.isBroken) {
                        isBroken = 'да';
                    }
                    document.getElementById("c-header").innerHTML = item.mark + ' ' + item.model + ', ' + item.price + ' ₽';
                    document.getElementById("mark").innerHTML = '<span class="item-params-label">Марка автомобиля: </span>' + item.mark;
                    document.getElementById("model").innerHTML = '<span class="item-params-label">Модель: </span>' + item.model;
                    document.getElementById("year").innerHTML = '<span class="item-params-label">Год выпуска: </span>' + item.year;
                    document.getElementById("power").innerHTML = '<span class="item-params-label">Мощность двигателя: </span>' + item.power;
                    document.getElementById("mileage").innerHTML = '<span class="item-params-label">Пробег: </span>' + item.mileage + ' км';
                    document.getElementById("price").innerHTML = '<span class="item-params-label">Цена: </span>' + item.price + ' ₽';
                    document.getElementById("isBroken").innerHTML = '<span class="item-params-label">Битый / не на ходу: </span>' + isBroken;
                    document.getElementById("city").innerHTML = '<span class="item-params-label">Город: </span>' + item.city;
                    document.getElementById("description").innerText = item.description;
                    let btn = document.getElementById("phone");
                    btn.value = item.phone;
                }
            }).fail(function (err) {
                console.log(err);
            });
        }
    </script>


    <title>Car sales</title>
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
            <div class="card-header" id="c-header">
                Просмотр объявления
            </div>
            <div class=item-view-block>
                <div class="item-params">
                    <ul class="list-group">
                        <li class="list-group-item" id="mark"></li>
                        <li class="list-group-item" id="model"></li>
                        <li class="list-group-item" id="year"></li>
                        <li class="list-group-item" id="power"></li>
                        <li class="list-group-item" id="mileage"></li>
                        <li class="list-group-item" id="price"></li>
                        <li class="list-group-item" id="isBroken"></li>
                        <li class="list-group-item" id="city"></li>
                        <li class="list-group-item">
                            <button class="btn btn-success" id="phone" onclick="showPhone(this.value)">Позвонить</button>
                        </li>
                    </ul>
                </div>
                <div class="car-photo" uk-lightbox="animation: fade">
                    <a href="${pageContext.request.contextPath}/photo-download?id=${pageContext.request.getParameter("id")}"
                       data-lightbox="image-1"
                       data-title="My caption">
                        <img style="max-width: 100%"
                             src="${pageContext.request.contextPath}/photo-download?id=${pageContext.request.getParameter("id")}"/>
                    </a>
                </div>
            </div>
            <div class="card-header" id="description-header">
                Описание
            </div>
            <p class="description" id="description"></p>

        </div>
        <p></p>
    </div>
</div>
</div>

</body>
</html>