<%-- 
    Document   : addProductForm
    Created on : 04.01.2021, 12:01:07
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление товара</title>
    </head>
    <body>
        <h1>Добавить товар</h1>
        <form action="createProduct" method="POST">
            Название товара: <input type="text" name="name" value="${name}"><br> 
            Цена товара: <input type="text" name="count" value="${count}"><br>
            Количество: <input type="text" name="quantity" value="${quantity}"><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
