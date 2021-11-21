<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>Login</title>
</head>
<body>

<%! Users user; %>
<% user = (Users) session.getAttribute("user"); %>
<% if (user == null) { %>
    <form action="users" method="get">
        <h1>Welcome, please log in here</h1>
        <p>
            <label for="username">UserName</label>
            <input id="username" name="username" value="${fn:escapeXml(param.username)}">
        </p>
        <p>
            <label for="password">PassWord</label>
            <input id="password" name="password" value="${fn:escapeXml(param.password)}">
        </p>
        <p>
            <input type="submit" value="Log in" />
        </p>
    </form>
    <br/>
    <div id="signup"><a href="UserCreate.jsp">Create user here!</a></div>
    <br/>
<% } else { %>
    <p>You've already logged in as <a href="UserProfile.jsp"><%= user.getFirstName() %></a>.</p>
<% } %>

</body>
</html>
