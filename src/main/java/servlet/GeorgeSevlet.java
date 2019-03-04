package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GeorgeSevlet extends GenericServlet{

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        Set<Integer> lotto = new TreeSet<>();
        while(lotto.size() < 6) {
            int number = new Random().nextInt(46) + 1;
            lotto.add(number);
        }
        out.print("Lotto number = " + lotto);
    }

    
}
