<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Shelves</title>
</head>
<body>
<header>
    <jsp:include page="menu.jsp"></jsp:include>
</header>
<table class="table">
    <thead >
    <tr>
    <th>Title</th>
    <th>Author</th>
    <th>Pages</th>
    <th>Edit Book</th>
    <th>Delete Book</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="books" value="${BOOKS}"/>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>
                <c:out value="${book.title}"/>
            </td>
            <td>
                <c:out value="${book.author}"/>
            </td>
            <td>
                <c:out value="${book.pages}"/>
            </td>
            <td>
                <a href="shelves/edit/${book.title}">Edit</a>
            </td>
            <td>
                <a href="shelves/delete/${book.title}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
