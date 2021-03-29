<%-- 
    Document   : editProductForm
    Created on : 27.02.2021, 9:46:08
    Author     : artur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <h3>Изменить атрибуты товара</h3>
        <p>${info}</p>
        <form action="editProduct" method="POST">
            <input type="hidden" name="productId" value="${product.id}"><br>
            Название товара: <input type="text" name="name" value="${product.name}"><br>
            Цена товара: <input type="text" name="author" value="${product.count}"><br>
            Количество: <input type="text" name="publishedYear" value="${product.quantity}"><br>
            <input type="submit" name="submit" value="Изменить"><br>
        </form>

