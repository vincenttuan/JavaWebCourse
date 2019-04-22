package servlet;

import java.io.PrintWriter;
import javax.servlet.AsyncContext;

public class AsyncRequest implements Runnable {
  private AsyncContext ctx;
  private int ms;
  private static int number;
  public AsyncRequest(AsyncContext ctx, int ms) {
    this.ctx = ctx;
    this.ms = ms;
  }
  @Override
  public void run() {
    try {
      // 模擬長時間的處理
      Thread.sleep(ms);  
      PrintWriter out = ctx.getResponse().getWriter();
      out.println("久等了...XD " + (++number));
      // 這邊才真正送出回應
      ctx.complete();     
    } catch (Exception e) {
       e.printStackTrace();
    }
  }
}
