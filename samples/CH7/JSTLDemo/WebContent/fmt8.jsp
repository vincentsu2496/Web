<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <fmt:formatNumber value="12345.678"/><br>
        <fmt:formatNumber value="12345.678" type="currency"/><br>
        <fmt:formatNumber value="12345.678" type="currency" currencySymbol="新台幣"/><br>
        <fmt:formatNumber value="12345.678" type="percent"/><br>
        <fmt:formatNumber value="12345.678" pattern="#,#00.0#"/>
    </body>
</html>