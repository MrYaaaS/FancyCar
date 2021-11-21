<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>Create User Account</title>
</head>
<body>

<%! Users user; %>
<% user = (Users) session.getAttribute("user"); %>
<% if (user == null) { %>
    <form action="users" method="post">
        <p>
            <label for="username">Username</label>
            <input id="username" name="username" value="${fn:escapeXml(param.username)}">
        </p>
        <p>
            <label for="password">Password</label>
            <input id="password" name="password" value="${fn:escapeXml(param.password)}">
        </p>
        <p>
            <label for="firstname">FirstName</label>
            <input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">
        </p>
        <p>
            <label for="lastname">LastName</label>
            <input id="lastname" name="lastname" value="${fn:escapeXml(param.lastname)}">
        </p>
        <p>
            <label for="email">Email</label>
            <input id="email" name="email" value="${fn:escapeXml(param.email)}">
        </p>
        <p>
            <label for="phone">Phone Number</label>
            <input id="phone" name="phone" value="${fn:escapeXml(param.phone)}">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="resultMessage"><b>${messages.result}</b></span>
        </p>
    </form>
<% } else { %>
    <p>You've already logged in as <a href="UserProfile.jsp"><%= user.getFirstName() %></a>.</p>
<% } %>


</body>
</html>
