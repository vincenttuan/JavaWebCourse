package mvc.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vo.Fastfood;
import vo.Food;

@WebServlet("/mvc/controller/Fastfood_JSTL_Controller")
public class Fastfood_JSTL_Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;chartset=utf-8");
        
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(false);
        if(session == null) {
            out.println("請先消費");
        } else {
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/mvc/view/fastfood_view_jstl.jsp");
           rd.forward(req, resp);
        }
        //out.print(session.getAttribute("fastfood"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;chartset=utf-8");
        
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession(true);
        Fastfood fastfood = new Fastfood();
        if(session.isNew()) {
            out.println("您是第一次進來");
            fastfood.setFirsttime(true);
        }
        
        String[] food_titles = req.getParameterValues("food_title");
        String[] food_prices = req.getParameterValues("food_price");
        for(int i=0;i<food_titles.length;i++) {
            fastfood.addFood(new Food(food_titles[i], food_prices[i]));
        }
        
        session.setAttribute("fastfood", fastfood);
        //out.print(session.getAttribute("fastfood"));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/mvc/view/fastfood_view_jstl.jsp");
        rd.forward(req, resp);
    }

}
