<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<header>
    <jsp:include page="menu.jsp"></jsp:include>
</header>
<form action="/bookhut/add" method="post">
    <div class="form-group col-sm-4">
        <label class="control-label" for="title">Title:</label>
        <input type="text" class="form-control" id="title" name="title">
    </div>
    <div class="form-group col-sm-4">
        <label class="control-label" for="author">Author:</label>
        <input type="text" class="form-control" id="author" name="author">
    </div>
    <div class="form-group col-sm-4">
        <label class="control-label" for="pages">Pages:</label>
        <input type="number" class="form-control" id="pages" name="pages">
    </div>
    <div class="form-group col-sm-4">
        <button type="submit" class="btn btn-primary">Add</button>
    </div>
</form>
</body>
</html>
