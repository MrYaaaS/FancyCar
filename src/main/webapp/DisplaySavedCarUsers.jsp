<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>Users saved this car</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">


<%-- header --%>
<a href="UserProfile.jsp">User Profile</a>
<%! Users user; %>
<% user = (Users) session.getAttribute("user"); %>
<% if (user == null) { %>
<a href="UserLogIn.jsp"> Log In </a>
<% } %>

<h1>Users Saved This Car</h1>
<table border="1">
    <tr>
        <th>UserName</th>
        <th>FirstName</th>
        <th>LastName</th>
        <th>Email</th>
        <th>Phone</th>
    </tr>
    <c:forEach items="${users}" var="users" >
        <tr>
            <td><c:out value="${users.getUserName()}" /></td>
            <td><c:out value="${users.getFirstName()}" /></td>
            <td><c:out value="${users.getLastName()}" /></td>
            <td><c:out value="${users.getEmail()}" /></td>
            <td><c:out value="${users.getPhone()}" /></td>
        </tr>
    </c:forEach>
</table>

<h2>Save This Car</h2>
<form action="usersavedcars" method="post">
    <p>
        <input type="hidden" id="vin" name="vin" value=<c:out value="${vin}" />>
        <input type="submit" value="Save">
    </p>
</form>
</div>
</body>
</html>
