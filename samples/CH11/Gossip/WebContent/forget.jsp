<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
                      "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" 
              content="text/html; charset=UTF-8">
        <title>忘記密碼？</title>
    </head>
    <body>
        <c:if test="${requestScope.error != null}">
            <div style='color: rgb(255, 0, 0);'>${requestScope.error}</div>
        </c:if>
        <form action="password.do" method="post">
        使用者名稱：<input type="text" name="name" value="${param.name}"/><br>
        使用者郵件：<input type="text" name="email" value="${param.email}"/><br><br>
            <input type="submit" value="送出"/>
        </form>
    </body>
</html>