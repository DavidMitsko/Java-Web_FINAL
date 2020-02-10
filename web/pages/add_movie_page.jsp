<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 02.02.2020
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="bootstrap.jsp" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<c:import url="header/adminNavbar.jsp" var="navbar"/>
<html>
<head>
    <title>
        <fmt:message key="title.addMovie" bundle="${var}"/>
    </title>
</head>
<body>
<div class="container">
    ${navbar}

    <form method="post" action="${pageContext.request.contextPath}/add_movie" enctype="multipart/form-data">
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

        <button type="submit" class="btn btn-primary">
            <fmt:message key="button.addMovie.add" bundle="${var}"/>
        </button>
    </form>
</div>
</body>
</html>
