<%--<%@ page import="fancycar.model.Users" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>

<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1"%>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Create User Account</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<%! Users user; %>--%>
<%--<% user = (Users) session.getAttribute("user"); %>--%>
<%--<% if (user == null) { %>--%>
<%--    <form action="users" method="post">--%>
<%--        <p>--%>
<%--            <label for="username">Username</label>--%>
<%--            <input id="username" name="username" value="${fn:escapeXml(param.username)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="password">Password</label>--%>
<%--            <input id="password" name="password" value="${fn:escapeXml(param.password)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="firstname">FirstName</label>--%>
<%--            <input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="lastname">LastName</label>--%>
<%--            <input id="lastname" name="lastname" value="${fn:escapeXml(param.lastname)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="email">Email</label>--%>
<%--            <input id="email" name="email" value="${fn:escapeXml(param.email)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <label for="phone">Phone Number</label>--%>
<%--            <input id="phone" name="phone" value="${fn:escapeXml(param.phone)}">--%>
<%--        </p>--%>
<%--        <p>--%>
<%--            <input type="submit">--%>
<%--            <br/><br/><br/>--%>
<%--            <span id="resultMessage"><b>${messages.result}</b></span>--%>
<%--        </p>--%>
<%--    </form>--%>
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
    <title>Create User Account</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<div class="container">
    <h1 class="text-primary"> New User Register From:</h1>
    <div class="card">
        <div class="card-body">
            <%! Users user; %>
            <% user = (Users) session.getAttribute("user"); %>
            <% if (user == null) { %>
            <form action="users" method="post">
                <div class="form-group row">
                    <label for="username" class="col-sm-2 col-form-label">Username</label>
                    <div class="col-sm-7">
                        <input id="username" name="username" value="${fn:escapeXml(param.username)}" class="form-control" placeholder="Enter Username">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="password" class="col-sm-2 col-form-label">Password</label>
                    <div class="col-sm-7">
                        <input id="password" name="password" value="${fn:escapeXml(param.password)}" class="form-control" placeholder="Enter Password">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="firstname" class="col-sm-2 col-form-label">FirstName</label>
                    <div class="col-sm-7">
                        <input id="firstname" name="firstname" value="${fn:escapeXml(param.firstname)}" class="form-control" placeholder="Enter First Name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lastname" class="col-sm-2 col-form-label">LastName</label>
                    <div class="col-sm-7">
                        <input id="lastname" name="lastname" value="${fn:escapeXml(param.lastname)}" class="form-control" placeholder="Enter Last Name">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="email" class="col-sm-2 col-form-label">Email   </label>
                    <div class="col-sm-7">
                        <input id="email" name="email" value="${fn:escapeXml(param.email)}" class="form-control" placeholder="Enter Email Address">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="phone" class="col-sm-2 col-form-label">Phone Number</label>
                    <div class="col-sm-7">
                        <input id="phone" name="phone" value="${fn:escapeXml(param.phone)}" class="form-control" placeholder="Enter Phone Number">
                    </div>
                </div>
                <br>
                <input type="submit" class="btn bg-primary text-white mr-5">
                <button type="button" class="btn btn-primary mr-5">
                    <a href="index.jsp" style="color: inherit">Home Page</a>
                </button>
                <a href="UserLogIn.jsp"> Log in here as an existing user</a>
                <br/><br/><br/>
                <span id="resultMessage"><b>${messages.result}</b></span>
            </form>
            <% } else { %>
            <p>You've already logged in as <a href="UserProfile.jsp"><%= user.getFirstName() %></a>.</p>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
