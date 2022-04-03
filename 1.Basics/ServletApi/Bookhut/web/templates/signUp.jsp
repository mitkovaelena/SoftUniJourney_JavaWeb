<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<header>
   <jsp:include page="menu.jsp"></jsp:include>
</header>
<form action="/bookhut/signup" method="post">
    <div class="form-group col-sm-4">
    <label class="control-label" for="username">Username:</label>
    <input type="text" class="form-control" id="username" name="username">
    </div>
    <div class="form-group col-sm-4">
    <label class="control-label" for="pass">Password:</label>
    <input type="password" class="form-control" id="pass" name="pass">
    </div>
    <div class="form-group col-sm-3">
    <button type="submit" class="btn btn-primary">Sign Up</button>
    </div>
</form>
</body>
</html>
