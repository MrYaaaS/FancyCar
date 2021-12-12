<%--<%@ page import="fancycar.model.Users" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>

<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1"%>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash; header &ndash;%&gt;--%>
<%--<a href="index.jsp">Home Page</a>--%>
<%--<%! Users user; %>--%>
<%--<% user = (Users) session.getAttribute("user"); %>--%>
<%--<% if (user == null) { %>--%>
<%--<a href="UserLogIn.jsp"> Log In </a>--%>
<%--<% } else { %>--%>
<%--<a href="UserProfile.jsp"> Welcome, <%= user.getFirstName() %> </a>--%>
<%--<% } %>--%>

<%--<h1>--%>
<%--    <%= "Welcome to Fancy Car" %>--%>
<%--</h1>--%>
<%--<br/>--%>

<%--</body>--%>
<%--</html>--%>
<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <title>Fancy Car</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<%-- header --%>
<div class="container">
    <div class="col text-center">
        <h1 class="text-danger"><%= "Welcome to Fancy Car" %></h1>
        <%--        <a href="index.jsp">Home Page</a>--%>
        <%! Users user; %>
        <% user = (Users) session.getAttribute("user"); %>
        <% if (user == null) { %>
        <br>
        <img src="${pageContext.request.contextPath}/image/4.png" alt="..." class="img-thumbnail">
        <img src="${pageContext.request.contextPath}/image/3.png" alt="..." class="img-thumbnail">
        <%--        <div class="col text-center">--%>
        <br><br><br><br>
        <button type="button" class="btn bg-danger text-white"> <a href="UserLogIn.jsp" style="color: inherit" > Your Fancy Cars Start Here</a> </button>
    </div>
    <% } else { %>
    <a href="UserProfile.jsp"> Welcome, <%= user.getFirstName() %> </a>
    <% } %>


</div>
<br/>

</body>
</html>