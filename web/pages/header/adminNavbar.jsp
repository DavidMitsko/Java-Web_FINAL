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
        <form method="get" action="${pageContext.request.contextPath}/Take_Users">
            <li class="nav-item">
                <button type="submit" class="nav-link btn">
                    <fmt:message key="navbar.admin.users" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}addMovie.jsp">
            <li>
                <button type="submit" class="nav-link btn">
                    <fmt:message key="navbar.admin.addMovie" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/Take_Movies">
            <li>
                <button type="submit" class="btn nav-link">
                    <fmt:message key="navbar.admin.removeMovie" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/Sign_Out">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.admin.signOut" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/Locale">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.admin.local" bundle="${var}"/>
                </button>
            </li>
        </form>
    </ul>
</div>
</body>
</html>
