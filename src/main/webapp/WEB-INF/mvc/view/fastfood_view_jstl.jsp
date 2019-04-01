<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <legend>Fastfood ShoppingCar JSTL (${sessionScope.fastfood.firsttime })</legend>
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
                        <c:set value="0" var="sum" />
                        <c:set value="60" var="price" />
                        <c:forEach var="food" items="${sessionScope.fastfood.foods}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${food.title}</td>
                            <td align="right">${food.amount}</td>
                        </tr>
                        <c:set value="${sum + food.amount}" var="sum" />
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr style="border:3px #cccccc solid;">
                            <th colspan="2">小計</th>
                            <th align="right">
                                ${sum}
                            </th>
                        </tr>
                        <tr style="border:3px #cccccc solid;">
                            <th colspan="2">每樣 $<c:out value="${price}" /></th>
                            <th align="right">
                                <fmt:formatNumber value = "${sum * price}" type = "currency"/>
                            </th>
                        </tr>
                    </tfoot>
                </table>
                <p>
                <button type="button" class="pure-button pure-button-primary" onclick="history.back()">返回</button>
            </fieldset>
        </form>
    </body>
</html>


