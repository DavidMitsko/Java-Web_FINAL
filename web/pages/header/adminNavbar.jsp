<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 06.02.2020
  Time: 14:33
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

<div class="container">
    <ul class="nav nav-tabs">
        <form method="get" action="${pageContext.request.contextPath}/take_users">
            <li class="nav-item">
                <button type="submit" class="nav-link btn">
                    <fmt:message key="navbar.admin.users" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/add_movie_page">
            <li>
                <button type="submit" class="nav-link btn">
                    <fmt:message key="navbar.admin.addMovie" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/take_movies_for_remove">
            <li>
                <button type="submit" class="btn nav-link">
                    <fmt:message key="navbar.admin.removeMovie" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/sign_out">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.admin.signOut" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/locale">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.admin.local" bundle="${var}"/>
                </button>
                <input type="hidden" value="${pageContext.request.queryString}" name="query">
                <input type="hidden" value="${pageContext.request.servletPath}" name="path">
            </li>
        </form>
    </ul>
</div>
</body>
</html>
