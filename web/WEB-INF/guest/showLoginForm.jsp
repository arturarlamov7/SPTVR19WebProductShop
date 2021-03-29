<%-- 
    Document   : addPersonForm
    Created on : 07.01.2021, 12:53:29
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h3>Введите логин и пароль </h3>
        <p>${info}</p>
        <form action="login" method="POST">
            Логин: <input type="text" name="login" value="${login}"><br> 
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" name="submit" value="Войти"><br>
            <p><a href="createPerson">Регистрация</a></p>
        </form>
