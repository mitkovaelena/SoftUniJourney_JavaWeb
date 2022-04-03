<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/bookhut/css/styles.css" type="text/css">
<link rel="stylesheet" href="/bookhut/css/bootstrap.min.css" type="text/css">

<nav class="navbar navbar-toggleable-md">
    <ul class="navbar-nav">
        <li class="nav-item"><a class="nav-link" href="/bookhut">Home</a></li>

        <c:set var="username" value="${USERNAME}" scope="session"/>
        <c:choose>
            <c:when test="${username != null}">
                <li class="nav-item"><a class="nav-link" href="/bookhut/add">Add Book</a></li>
                <li class="nav-item"><a class="nav-link" href="/bookhut/shelves">Shelves</a></li>
                <li class="list-item"><a class="nav-link" href="/bookhut/signout">Sign Out(${username})</a></li>
            </c:when>
            <c:otherwise>
                <li class="nav-item"><a class="nav-link" href="/bookhut/signup">Sign Up</a></li>
                <li class="nav-item"><a class="nav-link" href="/bookhut/signin">Sign In</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>

<div class="alert alert-${ALERT_CLASS} col-sm-4 ml-2" ${HIDDEN}>
    <strong>${ALERT_NAME}</strong> ${ALERT_MESSAGE}
</div>

