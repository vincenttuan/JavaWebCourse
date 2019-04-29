<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="my" uri="/WEB-INF/tlds/mytld"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my2" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
        <title>JSP Tag</title>
    </head>
    <body style="padding: 20px">
        <h1><my:report y="2018" /></h1>
        <h1><my:bmi h="170" w="60" /></h1>
        <h1>
            <my:search keyword="s">
                she sell sea shell on the sea shore
            </my:search>
        </h1>
        <h1><my:stock symbol="1101.tw"/><h1>
        <h1><my:stock symbol="${param.symbol}"/><h1>
        <my2:table_2 />        
    </body>
</html>
