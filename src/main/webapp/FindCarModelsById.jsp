<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Find a Model by Model Id</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">
	<%-- header --%>
	<a href="UserProfile.jsp">Users Profile</a>
	<%! Users user; %>
	<% user = (Users) session.getAttribute("user"); %>
	<% if (user == null) { %>
	<a href="UserLogIn.jsp"> Log In </a>
	<% } %>
	<form action="findcarmodelbyid" method="post">
		<h1>Search for a car model by model ID</h1>
		<p>
			<label for="id">ID</label>
			<input id="id" name="id" value="${fn:escapeXml(param.id)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="findcarmodels"><a href="findcarmodels">Find a car model by brand</a></div>
	<br/>
	<h1>Matching Car Model</h1>
        <table border="1">
            <tr>
                <th>ModelId</th>
                <th>Brand</th>
                <th>Model</th>
                <th>FuelType</th>

            </tr>
             <tr>
                    <td><c:out value="${CarModels.getModelId()}" /></td>
                    <td><c:out value="${CarModels.getBrand()}" /></td>
                    <td><c:out value="${CarModels.getModel()}" /></td>
                    <td><c:out value="${CarModels.getFuelType().toString()}" /></td>

                </tr>
            
       </table>
</div>
</body>
</html>