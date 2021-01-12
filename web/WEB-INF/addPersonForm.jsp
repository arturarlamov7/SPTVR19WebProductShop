<%-- 
    Document   : addPersonForm
    Created on : 07.01.2021, 12:53:29
    Author     : artur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление покупателя</title>
    </head>
    <body>
        <h1>Добавить покупателя</h1>
        <form action="createPerson" method="POST">
            Имя покупателя: <input type="text" name="name" value="${name}"><br> 
            Фамилия: <input type="text" name="lastname" value="${lastname}"><br>
            Номер телефона: <input type="text" name="phone" value="${phone}"><br>
            Бюджет: <input type="text" name="cash" value="${cash}"><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
