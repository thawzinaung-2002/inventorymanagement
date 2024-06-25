<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add new Category</title>
</head>
<body>

	<form:form action="add" method="post" modelAttribute="category">
		<h2>Category Add</h2>
		<form:label path="name">Name</form:label>
		<form:input type="text" path="name" /> <br/><br/>
		<form:label type="text" path="description">Description</form:label>
		<form:textarea type="text" path="description" />
		<br/><br/>
		<input type="submit" value="Add" />
		<button><a href="lists">Cancel</a></button>
	</form:form>

</body>
</html>