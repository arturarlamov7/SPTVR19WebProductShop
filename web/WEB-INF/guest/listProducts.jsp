<%-- 
    Document   : listProducts
    Created on : 09.01.2021, 21:27:15
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h3>Список товаров магазина:</h3>
<form action="editProductForm" method="POST">
    <select name="productId" multiple="true">
        <option value="">Выберите товар: </option>
        <c:forEach var="product" varStatus="status" items="${listProducts}">
            <option value="${product.id}"> ${status.index + 1}. "${product.name}". ${product.count}. ${product.quantity}</option>
        </c:forEach> 
    </select>
    <input type="submit" value="Изменить выбранный товар">
</form>

