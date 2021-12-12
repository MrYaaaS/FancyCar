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
    <title>Find Car Listings</title>
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

<form action="findcarlistingsbymodel" method="post">
    <h1>Search for a car listing by model</h1>
    <p>
        <label for="model">MODEL</label>
        <input id="model" name="model" value="${fn:escapeXml(param.model)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<br/>
<div id="findcarlistingsbymodel"><a href="findcarlistingsbymodel">Find a car listings by MODEL</a></div>
<div id="findcarcarmodelbyid"><a href="findcarmodelbyid">Find a car Model by id</a></div>
<br/>
<h1>Matching Car Listings</h1>

<br>
<% if (user != null) {
%>
<form>
        <table border="1">
            <tr>
                <th>Who Saved</th>
                <th>Reviews</th>
                <th>Recommendations</th>
                <th>Picture</th>
                <th>VIN</th>
                <th>Model</th>
                <th>Sellers</th>
                <th>Year</th>
                <th>City</th>
                <th>ExteriorColor</th>
                <th>InteriorColor</th>
                <th>Mileage</th>
                <th>HasAccident</th>
                <th>isCPO</th>
                <th>Price</th>

            </tr>
            <c:forEach items="${CarListings}" var="CarListings" >
                <tr>
                    <td><a href="usersavedcars?vin=<c:out value="${CarListings.getVin()}"/>">Save</a></td>
                    <td><a href="findreviews?vin=<c:out value="${CarListings.getVin()}"/>">Reviews</a></td>
                    <td><a href="findrecommendations?vin=<c:out value="${CarListings.getVin()}"/>">Recommendations</a></td>
                    <td><img src="<c:out value="${CarListings.getPictureUrl()}" />" alt="img" border=1 height=100 width=200></td>
                    <td><c:out value="${CarListings.getVin()}" /></td>
                    <td><c:out value="${CarListings.getCarmodel().toString()}" /></td>
                    <td><c:out value="${CarListings.getSellers().getName()}" /></td>
                    <td><c:out value="${CarListings.getYear()}" /></td>
                    <td><c:out value="${CarListings.getCity()}" /></td>
                    <td><c:out value="${CarListings.getExteriorColor()}" /></td>
                    <td><c:out value="${CarListings.getInteriorColor()}" /></td>
                    <td><c:out value="${CarListings.getMileage()}" /></td>
                    <td><c:out value="${CarListings.isHasAccident()}" /></td>
                    <td><c:out value="${CarListings.isCPO()}" /></td>
                    <td><c:out value="${CarListings.getPrice()}" /></td>


                </tr>
            </c:forEach>
        </table>
    <br>
</form>
<% } %>
</div>
</body>
</html>