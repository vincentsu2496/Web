<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="messageService" class="cc.openhome.MessageService"/>
<html>
    <head>
        <meta http-equiv="Content-Type"
                    content="text/html; charset=UTF-8">
        <title>留言版</title>
    </head>
    <body>
        <table style="text-align: left; width: 100%;" border="1">
            <tr>
                <td>名稱</td><td>訊息</td>
            </tr>
            <c:forEach var="message" items="${messageService.messages}">
                <tr>
                    <td>${message.name}</td><td>${message.text}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>  