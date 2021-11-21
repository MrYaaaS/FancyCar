<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Model</title>
</head>
<body>
	<form action="findcarmodels" method="post">
		<h1>Search for a car model by brand</h1>
		<p>
			<label for="brand">Brand</label>
			<input id="brand" name="brand" value="${fn:escapeXml(param.brand)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="findcarmodelbyid"><a href="findcarmodelbyid">Find a car model by model ID</a></div>
	<br/>
	<h1>Matching Car Models</h1>
        <table border="1">
            <tr>
                <th>ModelId</th>
                <th>Brand</th>
                <th>Model</th>
                <th>FuelType</th>

            </tr>
            <c:forEach items="${CarModels}" var="CarModels" >
                <tr>
                    <td><c:out value="${CarModels.getModelId()}" /></td>
                    <td><c:out value="${CarModels.getBrand()}" /></td>
                    <td><c:out value="${CarModels.getModel()}" /></td>
                    <td><c:out value="${CarModels.getFuelType().toString()}" /></td>

                </tr>
            </c:forEach>
       </table>
</body>
</html>