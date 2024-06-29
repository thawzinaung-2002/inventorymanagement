<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Lists</title>
</head>
<body>

	<button>
		<a href="add">Add New</a>
	</button>
	
	<button>
		<a href="../">Home</a>
	</button>
	
	<div>
		<c:forEach var="product" items="${products }">
			<div>
				${product.name }
				<a href="more/${product.id }">see more</a>
			</div>
		</c:forEach>
	</div>

</body>
</html>