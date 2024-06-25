<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Lists</title>
</head>
<body>
	
	<button>
		<a href="add">Add New</a>
	</button>
	
	<button>
		<a href="../">Home</a>
	</button>
	
	<table border="1">
		<thead>
			<tr>
				<td>Id</td>
				<td>Name</td>
				<td>Description</td>
				<td>Status</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="category" items="${categories }" >
				<tr>
					<td>${category.id}</td>
					<td>${category.name}</td>
					<td>${category.description}</td>
					<td>
						<a href="update/${category.id }">Update</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>