<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--this is a comment  -->

<%@ taglib uri="jakarta.tags.core" prefix="c" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film by ID</title>
<%@ include file="bootstraphead.jsp" %>
</head>
<body>
  <c:if test="${! empty film}">
     Title: ${film.title }
     Description: ${film.description } 
  </c:if>
<%@ include file="bootstrapfoot.jsp" %> 
</body>
</html>