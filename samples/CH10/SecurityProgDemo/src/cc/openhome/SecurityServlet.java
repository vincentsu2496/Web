package cc.openhome;

import java.io.*;
import java.security.AccessControlException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name="SecurityServlet", urlPatterns={"/security"})
public class SecurityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("其它使用者就可以看到的資料一");
        try {
            request.authenticate(response);
            out.println("必須驗證過使用者才可以看到的資料");
        }
        catch(AccessControlException ex) {
            ex.printStackTrace();
        }
        out.println("其它使用者就可以看到的資料二");
        
    } 
}