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
<a href="UserProfile.jsp">Users Profile</a>
<%! Users user; %>
<% user = (Users) session.getAttribute("user"); %>
<% if (user == null) { %>
<a href="UserLogIn.jsp"> Log In </a>
<% } %>

<form action="findcarlistingsbydetails" method="post">
    <h1>Search Car By Details</h1>
    <div class="card">
        <div class="card-body">

            <div class="form-group row">
                <label for="model" class="col-md-2 col-form-label">model</label>
                <div class="clo-sm-7">
                    <input id="model" name="model" value="${fn:escapeXml(param.model)}" placeholder="Enter Model">
                </div>
            </div>
            <div class="form-group row">
                <label for="year" class="col-md-2 col-form-label">year</label>
                <div class="clo-sm-7">
                    <input id="year" name="year" value="${fn:escapeXml(param.year)}" placeholder="Enter Year">
                </div>
            </div>
            <div class="form-group row">
                <label for="color" class="col-md-2 col-form-label">color</label>
                <div class="clo-sm-7">
                    <input id="color" name="color" value="${fn:escapeXml(param.color)}" placeholder="Enter Color">
                </div>
            </div>
            <div class="form-group row">
                <label for="mile" class="col-md-2 col-form-label">mile</label>
                <div class="clo-sm-7">
                    <input id="mile" name="mile" value="${fn:escapeXml(param.mile)}" placeholder="Enter Milleage">
                </div>
            </div>
            <div class="form-group row">
                <label for="price" class="col-md-2 col-form-label">price</label>
                <div class="clo-sm-7">
                    <input id="price" name="price" value="${fn:escapeXml(param.price)}" placeholder="Enter Price">
                </div>
            </div>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<% if (user != null) {%>
<form action="findcarlistingsbydetails" method="post">
    <h1>Matching Car Listings</h1>
        <table border="1">
            <tr>
                <th>Who Saved</th>
                <th>Reviews</th>
                <th>Recommendations</th>
                <th>Model</th>
                <th>Year</th>
                <th>ExteriorColor</th>
                <th>Mileage</th>
                <th>Price</th>
                <th>Picture</th>
                <th>VIN</th>
                <th>Sellers</th>
                <th>City</th>
                <th>InteriorColor</th>
                <th>HasAccident</th>
                <th>isCPO</th>

            </tr>
            <c:forEach items="${CarListings}" var="CarListings" >
                <tr>
                    <td><a href="usersavedcars?vin=<c:out value="${CarListings.getVin()}"/>">Save</a></td>
                    <td><a href="findreviews?vin=<c:out value="${CarListings.getVin()}"/>">Reviews</a></td>
                    <td><a href="findrecommendations?vin=<c:out value="${CarListings.getVin()}"/>">Recommendations</a></td>
                    <td><c:out value="${CarListings.getCarmodel().toString()}" /></td>
                    <td><c:out value="${CarListings.getYear()}" /></td>
                    <td><c:out value="${CarListings.getExteriorColor()}" /></td>
                    <td><c:out value="${CarListings.getMileage()}" /></td>
                    <td><c:out value="${CarListings.getPrice()}" /></td>
                    <td><img src="<c:out value="${CarListings.getPictureUrl()}" />" alt="img" border=1 height=100 width=200></td>
                    <td><c:out value="${CarListings.getVin()}" /></td>
                    <td><c:out value="${CarListings.getSellers().getName()}" /></td>
                    <td><c:out value="${CarListings.getCity()}" /></td>
                    <td><c:out value="${CarListings.getInteriorColor()}" /></td>
                    <td><c:out value="${CarListings.isHasAccident()}" /></td>
                    <td><c:out value="${CarListings.isCPO()}" /></td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </form>
        <% } %>

</div>
</div>
</div>
</body>
</html>