<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<sql:setDataSource dataSource="jdbc/demo"/>
<c:set target="${pageContext.request}"
         property="characterEncoding" value="UTF-8"/>
<c:if test="${param.msg != null}">
    <sql:update>
        INSERT INTO t_message(name, email, msg) VALUES (?, ?, ?)
        <sql:param value="${param.name}"/>
        <sql:param value="${param.email}"/>
        <sql:param value="${param.msg}"/>
    </sql:update>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type"
                content="text/html; charset=UTF-8">
        <title>訪客留言版</title>
    </head>
    <body>
        <table style="text-align: left; width: 100%;" border="0"
               cellpadding="2" cellspacing="2">
            <tbody>
                <sql:query sql="SELECT name, email, msg FROM t_message" var="messages"/>
                <c:forEach var="message" items="${messages.rows}">
                    <tr>
                        <td>${message.name}</td>
                        <td>${message.email}</td>
                        <td>${message.msg}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
