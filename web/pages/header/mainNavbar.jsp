<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 06.02.2020
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>

    </title>
</head>
<body>
    <header class="navbar navbar-expand-lg navbar-light flex-column flex-md-row bd-navbar bg-light">
        <div class="navbar-nav-scroll">
            <ul class="navbar-nav bd-navbar-nav flex-row">
                <form method="get" action="${pageContext.request.contextPath}/take_movies">
                    <li class="nav-item">
                        <button type="submit" class="nav-link btn">
                            <fmt:message key="navbar.main.main" bundle="${var}"/>
                        </button>
                    </li>
                </form>
                <form method="get" action="${pageContext.request.contextPath}/user_history">
                    <li>
                        <button type="submit" class="nav-link btn">
                            <fmt:message key="navbar.main.history" bundle="${var}"/>
                        </button>
                    </li>
                </form>
            </ul>
        </div>

        <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
            <form method="post" action="${pageContext.request.contextPath}/locale">
                <li>
                    <button type="submit" class="btn float-right">
                        <fmt:message key="navbar.main.local" bundle="${var}"/>
                    </button>
                    <input type="hidden" value="${pageContext.request.queryString}" name="query">
                    <input type="hidden" value="${pageContext.request.servletPath}" name="path">
                    <input type="hidden" value="${pageContext.session.getAttribute("movieName")}" name="path">
                </li>
            </form>

            <form method="get" action="${pageContext.request.contextPath}/sign_out">
                <li>
                    <button type="submit" class="btn btn-outline-success my-2 my-sm-0">
                        <fmt:message key="navbar.main.signOut" bundle="${var}"/>
                    </button>
                </li>
            </form>
        </ul>
    </header>
</body>
</html>
