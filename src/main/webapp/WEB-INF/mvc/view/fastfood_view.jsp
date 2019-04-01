<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String[] food_titles = (String[])session.getAttribute("food_titles");
    String[] food_prices = (String[])session.getAttribute("food_prices"); 
    boolean firsttime = Boolean.parseBoolean(session.getAttribute("firsttime").toString()); 
    
%>
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
                <legend>Fastfood ShoppingCar (<%=firsttime %>)</legend>
                <table class="pure-table pure-table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>品名</th>
                            <th>數量</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for(int i=0;i<food_titles.length;i++){%>
                        <tr>
                            <td><%=i+1 %></td>
                            <td><%=food_titles[i] %></td>
                            <td><%=food_prices[i] %></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <p>
                <button type="button" class="pure-button pure-button-primary" onclick="history.back()">返回</button>
            </fieldset>
        </form>
    </body>
</html>


