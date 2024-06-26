<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form:form action="doupdate" method="post" modelAttribute="product">
		<h2>Update Product</h2>
		<form:label path="lot">Lot No </form:label>
		<form:input type="text" path="lot" readonly="true"/><br/><br/>
		<form:label path="name">Name</form:label>
		<form:input type="text" path="name" readonly="true"/><br/><br/>
		<form:label path="quantity">Quantity</form:label>
		<form:input type="text" path="quantity" /> <br/><br/>
		<form:label path="price">Price</form:label>
		<form:input type="number" path="price" /> <br/><br/>
		<form:label path="uom">UoM</form:label>
		<form:select path="uom">
			<form:options items="${uom }" path="uom" />
		</form:select><br/><br/>
		<form:label path="category">Category</form:label>
		<form:select path="category">
			<form:options items="${categories }" path="category"/>
		</form:select>
		<br/><br/>
		<form:label path="expired">Expired</form:label>
		<form:input type="date" path="expired" /> <br/><br/>
		<input type="submit" value="Update" /> 
		<button>
			<a href="../lists">Home</a>
		</button>
	</form:form>

</body>
</html>