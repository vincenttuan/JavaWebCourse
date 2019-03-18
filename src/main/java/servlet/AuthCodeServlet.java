package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Util;

@WebServlet("/servlet/AuthCodeServlet")
public class AuthCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取得驗證碼
        String authCode = String.format("%04d", new Random().nextInt(1000));
        System.out.println("authCode = " + authCode);
        
        //將驗證碼保留在session中，便於以後驗證
        req.getSession().setAttribute("authCode", authCode);  
        
        try {
            //發送/寫入圖片資料  
            ImageIO.write(Util.getAuthImg(authCode), "JPEG", resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
