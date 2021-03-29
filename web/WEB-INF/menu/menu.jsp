<%-- 
    Document   : menu
    Created on : 29.03.2021, 9:19:29
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">Магазин</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="listProducts">Список товаров</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="buyProductForm">Купить товар</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="addMoney">Пополнить баланс</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="addProduct">Добавить товар</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="listPersons">Список покупателей</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="adminPanel">Панель администратора</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="showLoginForm">Войти</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="logout">Выйти</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

