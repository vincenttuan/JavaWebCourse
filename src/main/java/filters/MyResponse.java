package filters;

import java.io.CharArrayWriter;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponse extends HttpServletResponseWrapper {

    private PrintWriter out;
    private CharArrayWriter bufferedWriter;

    public MyResponse(HttpServletResponse response) {
        super(response);
        bufferedWriter = new CharArrayWriter();
        out = new PrintWriter(bufferedWriter);
    }

    @Override
    public PrintWriter getWriter() {
        return out;
    }

    public String getResult() {
        return bufferedWriter.toString();
    }
}
