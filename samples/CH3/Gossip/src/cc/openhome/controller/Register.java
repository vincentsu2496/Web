package cc.openhome.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register.do")
public class Register extends HttpServlet {
    private final String USERS = "c:/workspace/Gossip/users";
    private final String SUCCESS_VIEW = "success.view";
    private final String ERROR_VIEW = "error.view";

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmedPasswd = request.getParameter("confirmedPasswd");

        List<String> errors = new ArrayList<String>();
        if (isInvalidEmail(email)) {
            errors.add("未填寫郵件或郵件格式不正確");
        }
        if (isInvalidUsername(username)) {
            errors.add("使用者名稱為空或已存在");
        }
        if (isInvalidPassword(password, confirmedPasswd)) {
            errors.add("請確認密碼符合格式並再度確認密碼");
        }
        String resultPage = ERROR_VIEW;
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
        } else {
            resultPage = SUCCESS_VIEW;
            createUserData(email, username, password);
        }

        request.getRequestDispatcher(resultPage).forward(request, response);
    }

    private boolean isInvalidEmail(String email) {
        return email == null
                || !email.matches("^[_a-z0-9-]+([.]"
                        + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    }

    private boolean isInvalidUsername(String username) {
        for (String file : new File(USERS).list()) {
            if (file.equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidPassword(String password, String confirmedPasswd) {
        return password == null || password.length() < 6
                || password.length() > 16 || !password.equals(confirmedPasswd);
    }

    private void createUserData(String email, String username, String password)
            throws IOException {
        File userhome = new File(USERS + "/" + username);
        userhome.mkdir();
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(userhome + "/profile"));
        writer.write(email + "\t" + password);
        writer.close();
    }
}
