package cc.openhome;

import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener()
public class WebInitListener implements ServletContextListener {
    private List<AsyncContext> asyncs = new ArrayList<AsyncContext>();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("asyncs", asyncs);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep((int) (Math.random() * 10000));
                        double num = Math.random() * 10;
                        synchronized (asyncs) {
                            for (AsyncContext ctx : asyncs) {
                                ctx.getResponse().getWriter().println(num);
                                ctx.complete();
                            }
                            asyncs.clear();
                        } 
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}