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
    <script src="./js/index.js"></script>
    <script>
        function refreshTable() {
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/user-ads',
                dataType: 'json'
            }).done(function (data) {
                for (let item of data) {
                    if (item.isSold) {
                        td = '<td>' +
                            '<img src="./img/sold.jpg" height="100px">' +
                            '</td>'
                    } else {
                        td = '<td><button class="btn btn-light" onclick="changeStatus(this.value)" value="' + item.id + '">Выполнить</button></td>';
                    }
                    let user = item.author;
                    let cat = "";


                    let img = '<img src="http://localhost:8080/cars/photo-download?id=' + item.id + '" height="200px;">';
                    // let img2 = document.createElement('img');
                    // img2.style.filter = "grayscale(100%)";
                    // img2.setAttribute('src', 'http://localhost:8080/cars/photo-download?id=' + item.id);


                    if (item.isSold) {
                        img = '<img src="http://localhost:8080/cars/photo-download?id=' + item.id + '" height="200px;" style = "filter: grayscale(1)">';
                    }
                    $('#table tr:last').after('<tr>' +
                        '<td>' + item.id + '</td>' +
                        '<td>' + item.description + '</td>' +
                        '<td>' + item.created + '</td>' +
                        // '<td>           </td>' +
                        '<td>' + img + '</td>' +
                        // '<td>' + cat + '</td>' +
                        td +
                        '</tr>');
                }
            }).fail(function (err) {
                console.log(err);
            });
        }

        function changeStatus(val) {
            let isSold = confirm("Снять объявление с публикации?");
            if (!isSold) {
                return
            }
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/status?id=' + val,
                data: 'id=' + val,
                dataType: 'text'
            }).done(function (data) {
                removeTable()
                refreshTable();
            }).fail(function (err) {
                alert(err);
            });
        }

        function removeTable() {
            $('#table td').remove();
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
            <div class="card-header">
                Задания
            </div>
            <div class="card-body">
                <table class="table" id="table">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Описание</th>
                        <th scope="col">Дата</th>
                        <%--            <th scope="col">Автор</th>--%>
                        <th scope="col">Фото</th>
                        <th scope="col">Статус</th>
                    </tr>
                    <tr>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>