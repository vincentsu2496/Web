package cc.openhome.web;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cc.openhome.model.AccountDAOFileImpl;
import cc.openhome.model.BlahDAOFileImpl;
import cc.openhome.model.UserService;
import cc.openhome.model.UserService;

@WebListener
public class GossipListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String USERS = sce.getServletContext().getInitParameter("USERS");
        context.setAttribute("userService", new UserService(
                USERS, new AccountDAOFileImpl(USERS), new BlahDAOFileImpl(USERS)));
    }

    public void contextDestroyed(ServletContextEvent sce) {}
}
