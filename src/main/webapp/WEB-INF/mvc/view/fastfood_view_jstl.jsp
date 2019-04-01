<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
    JSTL 標準標籤庫
    http://www.runoob.com/jsp/jsp-jstl.html
-->
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <title>Fastfood ShoppingCar</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="padding: 10px">
        <form class="pure-form" method="post" action="/JavaWebCourse/servlet/BMIServlet">
            <fieldset>
                <legend>Fastfood ShoppingCar (${sessionScope.fastfood.firsttime })</legend>
                <font size=1>session id = ${pageContext.session.id}</font><p>
                <table class="pure-table pure-table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>品名</th>
                            <th>數量</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="food" items="${sessionScope.fastfood.foods}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${food.title}</td>
                            <td>${food.price}</td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p>
                <button type="button" class="pure-button pure-button-primary" onclick="history.back()">返回</button>
            </fieldset>
        </form>
    </body>
</html>


