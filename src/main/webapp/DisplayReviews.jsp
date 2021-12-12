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
    <title>Display Reviews</title>
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

<h1>Reviews for the car</h1>
        <table border="1">
            <tr>
                <th>ReviewId</th>
                <th>UserName</th>
                <th>VIN</th>
                <th>Created</th>
                <th>Rating</th>
                <th>Content</th>
            </tr>
            <c:forEach items="${reviews}" var="reviews" >
                <tr>
                    <td><c:out value="${reviews.getReviewId()}" /></td>
                    <td><c:out value="${reviews.getUser().getUserName()}" /></td>
                    <td><c:out value="${reviews.getCar().getVin()}" /></td>
                    <td><fmt:formatDate value="${reviews.getCreated()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${reviews.getRating()}" /></td>
                    <td><c:out value="${reviews.getContent()}" /></td>
                </tr>
            </c:forEach>
       </table>
    <br>

    <h2>Review This Car</h2>
    <form action="findreviews" method="post">
        <p>
            <input type="hidden" id="vin" name="vin" value=<c:out value="${vin}" />>
            <label for="content">Review Content:</label><br>
            <textarea type="text" id="content" name="content" value=""></textarea><br>
            <label for="rating">Rating(0.0 - 5.0):</label><br>
            <input type="text" id="rating" name="rating" value=""><br><br>
            <input type="submit" value="Review">
        </p>
    </form>
    </div>
</body>
</html>
