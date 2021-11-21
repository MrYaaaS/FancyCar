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
    <title>Reset Your Password</title>
</head>
<body>
<%! Users user; %>
<% user = (Users) session.getAttribute("user"); %>
<% if (user == null) { %>
    <a href="UserLogIn.jsp"> Log In </a>
<% } else { %>
    <h1>Reset Your Password!</h1>
    <form action="resetpassword" method="post">
        <p>
            <label for="newpassword">NewPassWord</label>
            <input id="newpassword" name="newpassword" value="${fn:escapeXml(param.newpassword)}">
        </p>
        <p>
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			    <input type="submit">
			</span>

            <br/><br/><br/>
            <span id="successMessage"><b>${messages.successMsg}</b></span>
        </p>
    </form>
<% } %>

</body>
</html>
