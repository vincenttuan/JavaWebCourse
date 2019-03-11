package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/BMIServlet")
public class BMIServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String height = req.getParameter("height");
        String weight = req.getParameter("weight");
        double h = Double.parseDouble(height);
        double w = Double.parseDouble(weight);
        double bmi = w / Math.pow(h/100, 2);
        resp.getWriter().print(bmi);
    }
    
}
