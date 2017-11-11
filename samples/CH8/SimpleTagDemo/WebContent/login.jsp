<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://openhome.cc/jstl/fake"%>
<jsp:useBean id="user" class="cc.openhome.User"  />
<jsp:setProperty name="user" property="*" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type"
               content="text/html; charset=UTF-8">
        <title>登入頁面</title>
    </head>
    <body>
        <f:choose>
            <f:when test="${user.valid}">
                <h1>${user.name}登入成功</h1>
            </f:when>
            <f:otherwise>
                 <h1>登入失敗</h1>
            </f:otherwise>
        </f:choose>
    </body>
</html>
