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

    <%--    <script src="./js/index.js"></script>--%>
    <script>

        $(document).ready(function () {
            refreshTable()
        });

        function refreshTable() {
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/cars/ads?id=' + id,
                dataType: 'json'
            }).done(function (data) {
                for (let item of data) {
                    let car = item.car;
                    document.getElementById("mark").innerHTML = '<span class="item-params-label">Марка автомобиля: <span>' + car.mark.name;
                    // document.getElementById("mark").innerHTML = car.mark.name;
                    document.getElementById("model").innerHTML = car.model.name;
                    document.getElementById("mileage").innerHTML = car.mileage;
                    document.getElementById("price").innerHTML = item.price;
                    document.getElementById("isBroken").innerHTML = car.isBroken;
                    document.getElementById("city").innerHTML = item.city.name;
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
            <div class="card-header">
                <%--                <% if (id == null) { %>--%>
                <%--                Новый кандидат.--%>
                <%--                <% } else { %>--%>
                <%--                Редактирование кандидата.--%>
                <%--                <% } %>--%>
                Просмотр объявления
            </div>
            <div class=item-view-block>
                <div class="item-params">
                    <ul class="list-group">
                        <li class="list-group-item"  id="mark">
<%--                                <span class="item-params-label">--%>
<%--                                    Марка автомобиля:--%>
<%--                                </span>--%>
                        </li>
                        <li class="list-group-item">
                                <span class="item-params-label" id="model">
                                    Модель:
                                </span>
                        </li>
<%--                        <li class="item-params-list-item">--%>
<%--                                <span class="item-params-label" id="description">--%>
<%--                                    Описане--%>
<%--                                </span>--%>
<%--                        </li>--%>
                        <li class="list-group-item">
                                <span class="item-params-label" id="mileage">
                                    Пробег:
                                </span>
                        </li>
                        <li class="list-group-item">
                                <span class="item-params-label" id="price">
                                    Цена:
                                </span>
                        </li>
                        <li class="list-group-item">
                                <span class="item-params-label" id="isBroken">
                                    Состояние:
                                </span>
                        </li>
                        <li class="list-group-item">
                                <span class="item-params-label" id="city">
                                    Город:
                                </span>
                        </li>
                    </ul>
                </div>
            </div>


            <%--            </div>--%>
            <%--            <div class="card-body">--%>
            <%--                &lt;%&ndash;                <form action="" method="post">&ndash;%&gt;--%>
            <%--                <div class="form-group">--%>

            <%--                    <label>Марка</label>--%>
            <%--                    <input type="text" class="form-control" name="mark" id="mark">--%>

            <%--                    <label>Модель</label>--%>
            <%--                    <input type="text" class="form-control" name="model" id="model">--%>

            <%--                    <label>Описание</label>--%>
            <%--                    <input type="text" class="form-control" name="description" id="description">--%>

            <%--                    <label>Пробег</label>--%>
            <%--                    <input type="text" class="form-control" name="mileage" id="mileage">--%>

            <%--                    <label>Цена</label>--%>
            <%--                    <input type="text" class="form-control" name="price" id="price">--%>

            <%--                    <label>Город</label>--%>
            <%--                    <select class="form-control" name="city" id="city">--%>
            <%--                        <option>--%>
            <%--                            &lt;%&ndash;                                <%=candidate.getCity()%>&ndash;%&gt;--%>
            <%--                        </option>--%>
            <%--                    </select>--%>

            <%--                    <label>Дата объявления</label>--%>
            <%--                    <input type="text" class="form-control" name="date" id="date">--%>

            <%--                    &lt;%&ndash;                        <img src="${pageContext.request.contextPath}/photo-download" width="100px" height="100px"/>&ndash;%&gt;--%>


            <%--                </div>--%>
            <%--                &lt;%&ndash;                    <button type="submit" class="btn btn-primary">Сохранить</button>&ndash;%&gt;--%>
            <%--                &lt;%&ndash;                </form>&ndash;%&gt;--%>
            <%--            </div>--%>
            <div uk-lightbox="animation: fade">
                <a href="${pageContext.request.contextPath}/photo-download?id=${pageContext.request.getParameter("id")}"
                   data-lightbox="image-1"
                   data-title="My caption">
                    <img style="max-width: 80%"
                         src="${pageContext.request.contextPath}/photo-download?id=${pageContext.request.getParameter("id")}"/>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>