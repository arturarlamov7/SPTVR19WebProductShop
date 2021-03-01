<%-- 
    Document   : buyProductForm
    Created on : 09.01.2021, 21:49:06
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Покупка товара</title>
    </head>
    <body>
        <h1>Выберите</h1>
        <form action="buyProduct" method="POST">
            <select name="productId">
                <option value="#">Выберите товар: </option>
                <c:forEach var="product" varStatus="status" items="${listProducts}">
                    <option value="${product.id}"> ${status.index + 1}. "${product.name}". ${product.count}. ${product.quantity}</option>
                </c:forEach> 
            </select>
            <br>
            <select name="readerId">
                <option value="#">Выберите покупателя: </option>
                <c:forEach var="person" varStatus="status" items="${listPersons}">
                    <option value="${person.id}"> ${status.index + 1}. "${person.name}". ${person.lastname}. ${person.cash}</option>
                </c:forEach> 
            </select>
            <br>
            <input type="submit" value="выдать товар покупателю">
        </form>
    </body>
</html>

