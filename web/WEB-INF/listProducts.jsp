<%-- 
    Document   : listProducts
    Created on : 09.01.2021, 21:27:15
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список Товаров</title>
    </head>
    <body>
        <h1>Список товаро магазина:</h1>  
        <select name="productId" multiple="true">
            <option value="#">Выберите книгу: </option>
            <c:forEach var="product" varStatus="status" items="${listProducts}">
            <option value="${product.id}"> ${status.index + 1}. "${product.name}". ${product.count}. ${product.quantity}</option>
            </c:forEach> 
        </select>
    </body>
</html>
