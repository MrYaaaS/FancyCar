<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>My Saved Cars</title>
</head>
<body>

<h1>My Saved Cars</h1>
<table border="1">
    <tr>
        <th>VIN</th>
        <th>Model</th>
        <th>Sellers</th>
        <th>PicUrl</th>
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
            <td><c:out value="${CarListings.getVin()}" /></td>
            <td><c:out value="${CarListings.getCarmodel().toString()}" /></td>
            <td><c:out value="${CarListings.getSellers().getName()}" /></td>
            <td><c:out value="${CarListings.getPictureUrl()}" /></td>
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

</body>
</html>
