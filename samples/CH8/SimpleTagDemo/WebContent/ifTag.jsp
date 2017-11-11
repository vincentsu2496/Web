<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://openhome.cc/jstl/fake" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type"
                content="text/html; charset=UTF-8">
        <title>自訂 if 標籤</title>
    </head>
    <body>
        <f:if test="${param.password == '123456'}">
            你的秘密資料在此！
        </f:if>
    </body>
</html> 