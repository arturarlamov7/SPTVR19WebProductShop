<%-- 
    Document   : addPersonForm
    Created on : 07.01.2021, 12:53:29
    Author     : artur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h3>Добавить покупателя</h3>
        <form action="createPerson" method="POST">
            Имя покупателя: <input type="text" name="name" value="${name}"><br> 
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Номер телефона: <input type="text" name="phone" value="${phone}"><br>
            Бюджет: <input type="text" name="cash" value="${cash}"><br>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>
