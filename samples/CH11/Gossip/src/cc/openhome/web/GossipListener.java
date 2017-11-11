package cc.openhome.web;

import java.io.*;
import java.util.Properties;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import cc.openhome.model.*;

@WebListener
public class GossipListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)
                           initContext.lookup("java:/comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/gossip");
            ServletContext context = sce.getServletContext();
            
            Properties props = new Properties();
            props.load(context.getResourceAsStream("/WEB-INF/mail.properties"));
            
            UserService userService = new UserService(
                    new AccountDAOJdbcImpl(dataSource), new BlahDAOJdbcImpl(dataSource), new GmailCarrier(props));
            
            userService.setTemplate(getHtmlTemplate(context, props));
            
            context.setAttribute("userService", userService);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getHtmlTemplate(ServletContext context, Properties props) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            context.getResourceAsStream(
                                    props.getProperty("cc.openhome.template")), "UTF-8"));
            StringBuilder template = new StringBuilder();
            String text = null;
            while((text = reader.readLine()) != null) {
                template.append(text);
            }
            return template.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {}
}
