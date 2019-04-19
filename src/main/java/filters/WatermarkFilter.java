package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/jsp/report/*")
public class WatermarkFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain)
                         throws IOException, ServletException {

        MyResponse myResp = new MyResponse((HttpServletResponse)resp);
        chain.doFilter(req, myResp);
        String html = myResp.getResult();
        html = html.replaceAll("<body", "<body background=\"../../files/watermark.png\"");
        resp.getWriter().println(html);
    }

    @Override
    public void destroy() {
    }

}
