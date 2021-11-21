<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find Reviews</title>
</head>
<body>
	<form action="findreviews" method="post">
		<h1>Search for Reviews by UserName</h1>
		<p>
			<label for="username">UserName</label>
			<input id="username" name="username" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Matching Reviews</h1>
        <table border="1">
            <tr>
                <th>ReviewId</th>
                <th>UserName</th>
                <th>VIN</th>
                <th>Created</th>
                <th>Rating</th>
                <th>Content</th>
            </tr>
            <c:forEach items="${reviews}" var="review" >
                <tr>
                    <td><c:out value="${review.getReviewId()}" /></td>
                    <td><c:out value="${review.getUser().getUserName()}" /></td>
                    <td><c:out value="${review.getCar().getVin()}" /></td>
                    <td><fmt:formatDate value="${review.getCreated()}" pattern="yyyy-MM-dd"/></td>
                    <td><c:out value="${review.getRating()}" /></td>
                    <td><c:out value="${review.getContent()}" /></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
