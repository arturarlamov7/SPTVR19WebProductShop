<%-- 
    Document   : buyProductForm
    Created on : 09.01.2021, 21:49:06
    Author     : artur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h3>Купить товар</h3>
        <form action="buyProduct" method="POST">
            <select name="productId">
                <option value="#">Выберите товар: </option>
                <c:forEach var="product" varStatus="status" items="${listProducts}">
                    <option value="${product.id}"> ${status.index + 1}. "${product.name}". ${product.count}. ${product.quantity}</option>
                </c:forEach> 
            </select>
            <br>
            <input type="submit" value="Купить">
        </form>
        <p>
            <c:forEach var="product" items="${listBuyProducts}">
                <!--список купленных товаров-->
                <div class="card" style="width: 18rem;">
                <img src="..." class="card-img-top" alt="...">
                <div class="card-body">
                  <h5 class="card-title">${product.name}</h5>
                  <p class="card-text">${product.count}</p>
                  <p class="card-text">${product.quantity}</p>
                  <a href="#" class="btn btn-primary">Купить</a>
                </div>
              </div>
            </c:forEach>
        </p>

