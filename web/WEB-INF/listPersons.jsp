<%-- 
    Document   : listPersons
    Created on : 09.01.2021, 21:37:50
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Список покупателей</title>
    </head>
    <body>
        <h1>Список покупателей:</h1>
        <select name="personId" multiple="true">
            <option value="#">Выберите покупателя: </option>
            <c:forEach var="person" varStatus="status" items="${listPersons}">
            <option value="${person.id}"> ${status.index + 1}. "${person.name}". ${person.lastname}. ${person.phone}</option>
            </c:forEach> 
        </select>
    </body>
</html>