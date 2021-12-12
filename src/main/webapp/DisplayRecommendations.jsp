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
    <title>Find Recommendations</title>
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
    <h1>Matching Recommendations</h1>
            <table border="1">
                <tr>
                    <th>RecommendationId</th>
                    <th>UserName</th>
                    <th>VIN</th>
                    <th>Created</th>
                </tr>
                <c:forEach items="${recommendations}" var="recommendation" >
                    <tr>
                        <td><c:out value="${recommendation.getRecommendationId()}" /></td>
                        <td><c:out value="${recommendation.getUser().getUserName()}" /></td>
                        <td><c:out value="${recommendation.getCar().getVin()}" /></td>
                        <td><fmt:formatDate value="${recommendation.getCreated()}" pattern="yyyy-MM-dd"/></td>
                    </tr>
                </c:forEach>
           </table>

        <h2>Recommend This Car</h2>
        <form action="findrecommendations" method="post">
            <p>
                <input type="hidden" id="vin" name="vin" value=<c:out value="${vin}" />>
                <input type="submit" value="Recommend">
            </p>
        </form>
    </div>
</body>
</html>
