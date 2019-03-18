package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/AuthCodeConfirmServlet")
public class AuthCodeConfirmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String myAuthCode = req.getParameter("myAuthCode");
        String authCode = req.getSession().getAttribute("authCode").toString();
        PrintWriter out = resp.getWriter();
        
        out.print(myAuthCode.equalsIgnoreCase(authCode)?"true":"false");
        
    }
    
}
