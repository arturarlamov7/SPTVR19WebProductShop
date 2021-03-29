<%-- 
    Document   : addProductForm
    Created on : 04.01.2021, 12:01:07
    Author     : artur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <body>
        <h3>Добавить товар</h3>
        <form action="createProduct" method="POST">
            Название товара: <input type="text" name="name" value="${name}"><br> 
            Цена товара: <input type="text" name="count" value="${count}"><br>
            Количество: <input type="text" name="quantity" value="${quantity}"><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>

