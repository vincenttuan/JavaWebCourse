package mvc.controller;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.model.BMIModel;

@WebServlet("/mvc/controller/BMIController")
public class BMIController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double height = Double.parseDouble(req.getParameter("height"));
        double weight = Double.parseDouble(req.getParameter("weight"));
        
        // Model
        BMIModel bm = new BMIModel(height, weight);
        
        // bm 轉 json
        String json = new Gson().toJson(bm);
        // View
        req.setAttribute("bmi", bm);
        req.setAttribute("json", json);
        // 傳導到指定目的
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/mvc/view/bmi_view.jsp");
        rd.forward(req, resp);
        
    }
    
}
