package cc.openhome.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cc.openhome.model.Account;
import cc.openhome.model.UserService;

@WebServlet(
        urlPatterns={"/password.do"},
        initParams={
            @WebInitParam(name = "SUCCESS_VIEW", value = "ok.jsp"),
            @WebInitParam(name = "ERROR_VIEW", value = "forget.jsp")
        }
    )
public class Password extends HttpServlet {
    private String SUCCESS_VIEW;
    private String ERROR_VIEW;

    @Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    UserService userService = (UserService) getServletContext().getAttribute("userService");
	    Account acct = new Account();
	    acct.setName(name);
	    acct.setEmail(email);
	    String page = null;
	    if(userService.sendPasswordTo(acct)) {
	        page = SUCCESS_VIEW;
	        request.setAttribute("name", name);
	        request.setAttribute("email", email);
	    }
	    else {
	        page = ERROR_VIEW;
	        request.setAttribute("error", "使用者名稱不存在或郵件不符合");
	    }
	    request.getRequestDispatcher(page).forward(request, response);
	}
}
