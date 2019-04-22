package servlet;

import java.io.IOException;
import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AsyncIOServlet", urlPatterns = {"/servlet/async_io.do"}, asyncSupported = true)
public class AsyncIOServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        //設定AsyncContext處理非同步請求
        final AsyncContext acontext = request.startAsync();
        final ServletInputStream input = request.getInputStream();

        input.setReadListener(new ReadListener() {
            byte buffer[] = new byte[4 * 1024];
            StringBuilder sbuilder = new StringBuilder();

            @Override
            public void onDataAvailable() {
                try {
                    do {
                        int length = input.read(buffer);
                        sbuilder.append(new String(buffer, 0, length));
                        System.out.println(sbuilder);
                    } while (input.isReady());
                } catch (IOException ex) {
                }
            }

            @Override
            public void onAllDataRead() {
                try {
                    //AsyncContext 處理Response
                    acontext.getResponse().getWriter().write("...read ok...");
                } catch (IOException ex) {
                }

                //完成非同步的請求
                acontext.complete();
            }

            @Override
            public void onError(Throwable t) {
            }
        });
    }
}
