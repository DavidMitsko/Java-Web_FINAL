<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.02.2020
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="help/bootstrap.jsp" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<c:import url="header/adminNavbar.jsp" var="navbar"/>
<c:import url="help/movie_info.jsp" var="movie_info"/>
<html>
<head>
    <title>
        <fmt:message key="title.changeMovie" bundle="${var}"/>
    </title>
</head>
<body>

${navbar}

<div class="container mt-3">
    <form method="post" action="${pageContext.request.contextPath}/change_movie" enctype="multipart/form-data">
        ${movie_info}

        <button type="submit" class="btn btn-primary">
            <fmt:message key="button.changeMovie" bundle="${var}"/>
        </button>
    </form>
</div>

</body>
</html>
