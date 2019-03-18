<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%! 
    public int add(int x, int y) {
        return x + y;
    }

    class Lotto {
        public int getNumber() {
            return 777;
        }
    }
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1><%=new Date() %></h1>
        <h1><%=add(10, 20) %></h1>
        <%
            int x = add(10, 20);
            if( x % 2 == 0) {
                out.print("偶數");
            } else {
                out.print("奇數");
            }
        %>
        
        <%=new Lotto().getNumber() %>
        
    </body>
</html>
