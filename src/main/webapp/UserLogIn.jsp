<%--<%@ page import="fancycar.model.Users" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>

<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1"%>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Login</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<%! Users user; %>--%>
<%--<% user = (Users) session.getAttribute("user"); %>--%>
<%--<% if (user == null) { %>--%>
<%--    <form action="users" method="get">--%>
<%--        <h1>Welcome, please log in here</h1>--%>
<%--        <p>--%>
<%--            <label for="username">UserName</label>--%>
<%--            <input id="username" name="username" value="${fn:escapeXml(param.username)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="password">PassWord</label>--%>
<%--            <input id="password" name="password" value="${fn:escapeXml(param.password)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <input type="submit" value="Log in" />--%>
<%--        </p>--%>
<%--    </form>--%>
<%--    <br/>--%>
<%--    <div id="signup"><a href="UserCreate.jsp">Create user here!</a></div>--%>
<%--    <br/>--%>
<%--<% } else { %>--%>
<%--    <p>You've already logged in as <a href="UserProfile.jsp"><%= user.getFirstName() %></a>.</p>--%>
<%--<% } %>--%>

<%--</body>--%>
<%--</html>--%>
<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">

    <%! Users user; %>
    <% user = (Users) session.getAttribute("user"); %>
    <% if (user == null) { %>
    <form action="users" method="get">

        <h1 class="text-primary">Welcome! Please log in here</h1>
        <div class="card">
            <div class="card-body">

                <div class="form-group row">
                    <label for="username" class="col-md-2 col-form-label">UserName</label>
                    <div class="clo-sm-7">
                        <input id="username" name="username" value="${fn:escapeXml(param.username)}" placeholder="Enter User Name">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="password" class="col-md-2 col-form-label">Password</label>
                    <div class="clo-sm-7">
                        <input id="password" name="password" value="${fn:escapeXml(param.password)}" placeholder="Enter Password">
                    </div>
                </div>


                <%--                        <input type="submit" value="Log in" />--%>

                <button type="submit" class="btn btn-primary mr-5">Submit</button>

                <button type="button" class="btn btn-primary">
                    <a href="index.jsp" style="color: inherit">Home</a>
                </button>


    </form>

    <br/>
    <br/>
    <div id="signup"><a href="UserCreate.jsp">Create New User Here!</a></div>
    <br/>
    <% } else { %>
    <p>You've already logged in as <a href="UserProfile.jsp"><%= user.getFirstName() %></a>.</p>
    <% } %>
</div>
</div>
</div>
</body>
</html>
