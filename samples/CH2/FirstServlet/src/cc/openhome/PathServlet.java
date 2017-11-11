package cc.openhome;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/servlet/*")
public class PathServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Pattern</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(req.getRequestURI() + "<br>");
        out.println(req.getContextPath() + "<br>");
        out.println(req.getServletPath() + "<br>");
        out.println(req.getPathInfo());
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}