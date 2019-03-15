package servlet.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.Util;

@WebServlet("/servlet/upload/UploadServlet")
@MultipartConfig(
                location="c:/temp/",
                 fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;chartset=UTF-8");
        PrintWriter out = resp.getWriter();
        
        String title = "";
        String fileName = "";
        for (Part part : req.getParts()) {
            switch (part.getName()) {
                case "title":
                    title = Util.getValue(part.getInputStream());
                    break;
                case "upload":
                    fileName = File.createTempFile("Mclaren", ".jpg").getName();
                    part.write(fileName);
                    break;
            }
        }
        
        String json = "{\"title\":\"%s\", \"fileName\":\"%s\"}";
        json = String.format(json, title, fileName);
        out.println(json);
        
    }
    
}
