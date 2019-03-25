package rest;

import com.google.gson.Gson;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rest/*")
public class UserRest extends HttpServlet {
    UserDAO dao;
    
    @Override
    public void init() throws ServletException {
        dao = new UserDAO();
    }
    
    protected void doHandler(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("method : " + request.getMethod());
        out.println("getPathInfo() : " + request.getPathInfo());
        out.println("getParameterMap() : " + request.getParameterMap());
        out.println("getParameterMap().size() : " + request.getParameterMap().size());
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            out.println("restRequest.getId() : " + restRequest.getId());
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            int id = restRequest.getId();
            
            if(id == -1) {
                out.print(new Gson().toJson(dao.queryAll()));
            } else {
                out.print(new Gson().toJson(dao.get(id)));
            }
            
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();
        try {
            String username = request.getParameter("username");
            RestRequest restRequest = new RestRequest(request.getPathInfo());
            dao.create(username);
            out.print("Create OK");
        } catch (ServletException e) {
            e.printStackTrace();
            out.println(e.toString());
        }
        
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandler(request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doHandler(request, response);
    }

    // implement remaining HTTP actions here
    private class RestRequest {
        // Accommodate two requests, one for all resources, another for a specific
        // resource

        private Pattern regExAllPattern = Pattern.compile("/users");
        private Pattern regExIdPattern = Pattern.compile("/user/([0-9]*)");

        private int id = -1;

        public RestRequest(String pathInfo) throws ServletException {
            // regex parse pathInfo
            Matcher matcher;

            // Check for ID case first, since the All pattern would also match
            matcher = regExIdPattern.matcher(pathInfo);
            if (matcher.find()) {
                try {
                    id = Integer.parseInt(matcher.group(1));
                } catch (Exception e) {
                }
                return;
            }

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                return;
            }

            throw new ServletException("Invalid URI");
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
