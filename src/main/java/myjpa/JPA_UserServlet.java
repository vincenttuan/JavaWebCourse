package myjpa;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myjpa/JPA_UserServlet")
public class JPA_UserServlet extends HttpServlet {
    private JPA_UserController controller = new JPA_UserController();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter out = resp.getWriter();
        
        out.print(gson.toJson(controller.queryAll()));
    
    }
    
    

}
