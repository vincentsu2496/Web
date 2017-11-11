<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <c:import var="xml" url="bookmarks.xml" charEncoding="UTF-8"/>
    <x:parse var="bookmarks" doc="${xml}"/>
    <x:choose>
        <x:when select="$bookmarks//bookmark[@id=$param:id]/title">
            <x:out select="$bookmarks//bookmark[@id=$param:id]/title"/>        
        </x:when>
        <x:otherwise>
                               指定的書籤 id = ${param.id} 不存在
        </x:otherwise>
    </x:choose>
    
    
  
    </body>
</html>