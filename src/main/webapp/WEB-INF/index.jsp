<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<%@ include file="bootstraphead.jsp"%>
<title>SDPT Films</title>
</head>
<body>
	<form action="findById.do">
		<input type="text" name="id">
		<input type="submit" name="doThis" value="Find a film by ID">
	</form>
<%@ include file="bootstrapfoot.jsp" %> 	
</body>
</html>