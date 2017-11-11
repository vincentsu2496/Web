package cc.openhome;

import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.do")
public class Login extends HttpServlet {
    private Map<String, String> users;

    public Login() {
        users = new HashMap<String, String>();
        users.put("caterpillar", "123456");
        users.put("momor", "98765");
        users.put("hamimi", "13579");
    }

    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
                              throws ServletException, IOException {
        String name = request.getParameter("name");
        String passwd = request.getParameter("passwd");

       String page = "form.html";
       if(users.containsKey(name) && users.get(name).equals(passwd)) {
           User user = new User(name);
           request.getSession().setAttribute("user", user);
           page = "welcome.view";
       }
       response.sendRedirect(page);
    } 
}
