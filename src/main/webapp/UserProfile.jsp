<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<html>
<head>
    <title>User Profile</title>
</head>
<body>
<%-- header --%>

<%! Users user; %>
<% user = (Users) session.getAttribute("user"); %>
<% if (user == null) { %>
<a href="UserLogIn.jsp"> Log In </a>
<% } else { %>
<%-- User info --%>
<div>
    <p>Signed in as:</p>
    <p>
        <p>User Name: <%=user.getUserName()%></p>
        <p>First Name: <%=user.getFirstName()%></p>
        <p>Last Name: <%=user.getLastName()%></p>
        <p>Email: <%=user.getEmail()%></p>
        <p>Phone: <%=user.getPhone()%></p>
    </p>
</div>

<%-- Reset Password --%>
<div>
    <a href="resetpassword">Reset Password</a>
</div>

<%-- Saved Cars --%>
<div>
    <a href="usersavedcars">Saved Cars</a>
</div>

<%-- Find Cars --%>
<div>
    <a href="findcarlistingsbyvin">Find Cars</a>
</div>

<%-- Find Car Models --%>
<div>
    <a href="findcarmodels">Find Car Models</a>
</div>

<%-- Find Reviews --%>
<div>
    <a href="findreviews">Find Reviews</a>
</div>

<%-- Personalized Recommendations --%>
<div>

</div>
<% } %>


</body>
</html>
