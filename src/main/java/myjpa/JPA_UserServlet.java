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
        String method = req.getParameter("method");
        if(method == null) method = "";
        switch(method) {
            case "save": // ?method=save&name=Joe&age=28
                String name = req.getParameter("name");
                int age = Integer.parseInt(req.getParameter("age"));
                User user = new User();
                user.setName(name);
                user.setAge(age);
                controller.save(user);
                out.print("save ok");
                break;
            case "update": // ?method=update&id=1&name=Joe&age=28
                break;
            case "delete": // ?method=delete&id=1
                break;
            case "findById": // ?method=findById&id=1
                break;
            case "queryByAge": // ?method=queryByAge&age=20
                break;                
            default:
                out.print(gson.toJson(controller.queryAll()));
        }
        
    
    }
    
    

}
