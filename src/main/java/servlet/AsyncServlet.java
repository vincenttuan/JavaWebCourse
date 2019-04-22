package servlet;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncServlet", urlPatterns = {"/servlet/async.do"}, asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    // 執行緒池
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        response.setContentType("text/html; charset=UTF8");
        int ms = Integer.parseInt(request.getParameter("ms"));

        AsyncContext ctx = request.startAsync();
        executorService.submit(new AsyncRequest(ctx, ms));

    }

    @Override
    public void destroy() {
        executorService.shutdown();
    }
}
