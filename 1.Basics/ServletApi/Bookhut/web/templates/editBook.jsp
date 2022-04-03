<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/bookhut/css/styles.css" type="text/css">
<link rel="stylesheet" href="/bookhut/css/bootstrap.min.css" type="text/css">

<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<header>
    <jsp:include page="menu.jsp"></jsp:include>
</header>
<form action="/bookhut/shelves/edit/${book.title}" method="post">
    <div class="form-group col-sm-4">
        <label class="control-label" for="title">Title:</label>
        <input type="text" class="form-control" id="title" name="title" value="${book.title}">
    </div>
    <div class="form-group col-sm-4">
        <label class="control-label" for="author">Author:</label>
        <input type="text" class="form-control" id="author" name="author" value="${book.author}">
    </div>
    <div class="form-group col-sm-4">
        <label class="control-label" for="pages">Pages:</label>
        <input type="number" class="form-control" id="pages" name="pages" value="${book.pages}">
    </div>
    <div class="form-group col-sm-4">
        <button type="submit" class="btn btn-primary">Edit</button>
    </div>
</form>
</body>
</html>
