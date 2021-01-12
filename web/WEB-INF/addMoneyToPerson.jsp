<%-- 
    Document   : addMoneyToPerson
    Created on : 10.01.2021, 11:37:42
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пополнить баланс покупателя</title>
        
    </head>
    <body>
        <h1>Пополнить баланс покупателя</h1>
        <p>${info}</p>
        <form action="addMoney" method="POST">
        <h2>Список покупателей</h2>
        <select name="personId" multiple="true">
            <option value="">выберите покупателя:</option>
            <c:forEach var="person" items="${listPersons}">
                <option value="${person.id}">${person.id}. Имя: ${person.name} ${person.lastname}. Деньги: ${person.cash}$</option>
            </c:forEach>
        </select><br>
        Сколько денег?: <input type="number" name="money" min="1" placeholder="количество">
        <input type="submit" value="пополнить кошелек">
        </form>
    </body>
</html>
