<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.02.2020
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
</head>
<body>

<div class="form-group">
    <label for="usr">
        <fmt:message key="text.addMovie.name" bundle="${var}"/>
    </label>
    <input type="text" class="form-control" id="usr" name="movieName">
</div>

<div class="form-group">
    <label for="comment">
        <fmt:message key="text.addMovie.description" bundle="${var}"/>
    </label>
    <textarea class="form-control" rows="5" id="comment" name="movieDescription"></textarea>
</div>

<div class="form-group">
    <input type="file" id="file" name="movieImage" class="form-control-file border">
</div>

</body>
</html>
