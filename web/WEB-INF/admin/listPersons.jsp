<%-- 
    Document   : listPersons
    Created on : 09.01.2021, 21:37:50
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <body>
        <h3>Список покупателей:</h3>
        <select name="personId" multiple="true">
            <option value="#">Выберите покупателя: </option>
            <c:forEach var="person" varStatus="status" items="${listPersons}">
            <option value="${person.id}"> ${status.index + 1}. "${person.name}". ${person.lastname}. ${person.phone}</option>
            </c:forEach> 
        </select>
