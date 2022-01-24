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
        <div class="custom-control custom-checkbox">
          <input type="checkbox" class="custom-control-input" id="showAllCheckbox" onclick="checkBox()"
                 checked>
          <label class="custom-control-label" for="showAllCheckbox">Показать все</label>
        </div>
        <table class="table" id="table">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Описание</th>
            <th scope="col">Дата</th>
            <th scope="col">Автор</th>
            <th scope="col">Категория</th>
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