<%--<%@ page import="fancycar.model.Users" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>

<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--         pageEncoding="ISO-8859-1" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>User Profile</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--&lt;%&ndash; header &ndash;%&gt;--%>

<%--<%! Users user; %>--%>
<%--<% user = (Users) session.getAttribute("user"); %>--%>
<%--<% if (user == null) { %>--%>
<%--<a href="UserLogIn.jsp"> Log In </a>--%>
<%--<% } else { %>--%>
<%--&lt;%&ndash; User info &ndash;%&gt;--%>
<%--<div>--%>
<%--    <p>Signed in as:</p>--%>
<%--    <p>--%>
<%--        <p>User Name: <%=user.getUserName()%></p>--%>
<%--        <p>First Name: <%=user.getFirstName()%></p>--%>
<%--        <p>Last Name: <%=user.getLastName()%></p>--%>
<%--        <p>Email: <%=user.getEmail()%></p>--%>
<%--        <p>Phone: <%=user.getPhone()%></p>--%>
<%--    </p>--%>
<%--</div>--%>

<%--&lt;%&ndash; Reset Password &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="resetpassword">Reset Password</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; Saved Cars &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="usersavedcars">My Saved Cars</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; My Recommendations &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findrecommendations">My Recommendations</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; My Reviews &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findreviews">My Reviews</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; Find Cars &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findcarlistingsbyvin">Find Cars By Vin</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; Find Cars &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findcarlistingsbymodel">Find Cars By Model</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; Find Car Models &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findcarmodels">Find Car Models</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; Find Reviews &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findreviews">Find Reviews</a>--%>
<%--</div>--%>

<%--&lt;%&ndash; Personalized Recommendations &ndash;%&gt;--%>


<%--&lt;%&ndash; Find CarListing by details &ndash;%&gt;--%>
<%--<div>--%>
<%--    <a href="findcarlistingsbydetails">Find CarListings By Details</a>--%>
<%--</div>--%>

<%--<% } %>--%>


<%--</body>--%>
<%--</html>--%>

<%@ page import="fancycar.model.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<%-- header --%>
<div class="container">
    <%! Users user; %>
    <% user = (Users) session.getAttribute("user"); %>
    <% if (user == null) { %>
    <a href="UserLogIn.jsp"> Log In </a>
    <% } else { %>
    <%-- User info --%>
    <h1 class="text-primary">User Information:</h1>

    <div class="form-group row">
        <div class="clo-sm-7">User Name: <%=user.getUserName()%></div>
    </div>
    <div class="form-group row">
        <div class="clo-sm-7">First Name: <%=user.getFirstName()%></div>
    </div>
    <div class="form-group row">
        <div class="clo-sm-7">Last Name: <%=user.getLastName()%></div>
    </div>
    <div class="form-group row">
        <div class="clo-sm-7">Email: <%=user.getEmail()%></div>
    </div>
    <div class="form-group row">
        <div class="clo-sm-7">Phone: <%=user.getPhone()%></div>
    </div>

    <%-- Reset Password --%>
    <div class="form-group row">
        <div class="clo-sm-7">
            <a href="resetpassword">Reset Password</a>
        </div>
    </div>

    <%-- Saved Cars --%>
    <div class="form-group row">
        <div class="clo-sm-7">
            <a href="usersavedcars">Saved Cars</a>
        </div>
    </div>

    <%-- Find Cars --%>
    <div class="form-group row">
        <div class="clo-sm-7">
            <a href="findcarlistingsbyvin">Find Cars by VIN</a>
        </div>
    </div>

    <%-- Find Car Models --%>
    <div class="form-group row">
        <div class="clo-sm-7">
            <a href="findcarmodels">Find Car Models</a>
        </div>
    </div>

<%--    Find Cars by Model--%>
    <div class="form-group row">
        <div class="clo-sm-7">
            <a href="findcarlistingsbymodel">Find Cars By Model</a>
        </div>
    </div>




    <%-- My Reviews --%>
    <div class="form-group row">
        <div class="clo-sm-7">
            <a href="findreviews">My Reviews</a>
        </div>
    </div>

    <%-- Find CarListing by details --%>
    <div class="from-group row">
        <div class="clo-sm-7">
            <a href="findcarlistingsbydetails">Find CarListings By Details</a>
        </div>
    </div>
    <br>
<%--     My Recommendations --%>
    <div class="from-group row">
        <div class="clo-sm-7">
            <a href="findrecommendations">My Recommendations</a>
        </div>
    </div>
    <br>
<%--    TODO: Personalized Recommendations --%>
    <div class="from-group row">
        <div class="clo-sm-7">
            <a href="personalizedrecommendations">Personalized Recommendations</a>
        </div>
    </div>


    <br>

    <%-- User Logout --%>
    <div class="from-group row">
        <div class="clo-sm-7">
            <a href="logout">Logout</a>
        </div>
    </div>


    <% } %>

</div>
</body>
</html>

