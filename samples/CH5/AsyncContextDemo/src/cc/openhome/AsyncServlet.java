package cc.openhome;

import java.io.*;
import java.util.concurrent.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name="AsyncServlet", urlPatterns={"/async.do"},
            asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    protected void doGet(HttpServletRequest request,
                                 HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF8");
        AsyncContext ctx = request.startAsync();
        executorService.submit(new AsyncRequest(ctx));
    } 

    @Override
    public void destroy() {
         executorService.shutdown();
    }    
}