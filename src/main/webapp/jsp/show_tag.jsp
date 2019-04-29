<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="my" uri="/WEB-INF/tlds/mytld"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Tag</title>
    </head>
    <body>
        <h1><my:report y="2018" /></h1>
        <h1><my:bmi h="170" w="60" /></h1>
        <h1>
            <my:search keyword="s">
                she sell sea shell on the sea shore
            </my:search>
        </h1>
    </body>
</html>
