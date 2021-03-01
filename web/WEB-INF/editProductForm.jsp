<%-- 
    Document   : editProductForm
    Created on : 27.02.2021, 9:46:08
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Изменение товара </title>
    </head>
    <body>
        <h1>Изменить атрибуты товара</h1>
        <p>${info}</p>
        <form action="editProduct" method="POST">
            <input type="hidden" name="productId" value="${product.id}"><br>
            Название товара: <input type="text" name="name" value="${product.name}"><br>
            Цена товара: <input type="text" name="author" value="${product.count}"><br>
            Количество: <input type="text" name="publishedYear" value="${product.quantity}"><br>
            <input type="submit" name="submit" value="Изменить"><br>
        </form>
    </body>
</html>
