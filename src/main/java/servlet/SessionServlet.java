package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/SessionServlet")
public class SessionServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.getWriter().print("create Session ");
        if(session.isNew()) {
            System.out.println("New:" + session.getId());
        }
        resp.getWriter().print("id:" + session.getId());
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            System.out.println("Del:" + session.getId());
            session.invalidate();
            resp.getWriter().print("destroy Session");
        }
    }
    
}
