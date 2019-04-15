package twii;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/twii/TWIIServlet")
public class TWIIServlet extends HttpServlet {
    @EJB
    private TWIIBean tWIIBean;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println(tWIIBean.query(5, 5, 1));
    }
    
}
