<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
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
				<td>Name</td>
				<td>Price</td>
				<td>Category</td>
				<td>Quantity</td>
				<td>UoM</td>
				<td>Expired Date</td>
				<td>Lot No</td>
				<td>Status</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products }" >
				<tr>
					<td>${product.name}</td>
					<td>${product.price } </td>
					<td>${product.category }</td>
					<td>${product.quantity}</td>
					<td>${product.uom }</td>
					<td>${product.expired }</td>
					<td>${product.lot }</td>
					<td>
						<a href="update/${product.lot }">Update</a>|
						<!-- <a href="delete/${product.lot }">Delete</a> -->
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>