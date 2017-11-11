package cc.openhome;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name="AsyncNumServlet", urlPatterns={"/asyncNum.do"},
             asyncSupported = true)
public class AsyncNumServlet extends HttpServlet {
    private List<AsyncContext>  asyncs;
    
    @Override
    public void init() throws ServletException {
        asyncs = (List<AsyncContext>) getServletContext()
                                        .getAttribute("asyncs");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                                 HttpServletResponse response)
    throws ServletException, IOException {
        AsyncContext ctx = request.startAsync();
        synchronized(asyncs) {
            asyncs.add(ctx);
        }
    } 
}