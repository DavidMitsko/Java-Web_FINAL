<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 03.02.2020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="bootstrap.jsp" %>

<jsp:useBean id="reviewMap" type="java.util.HashMap<java.lang.String, com.mitsko.mrdb.entity.Review>" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.history" bundle="${var}"/>
    </title>
</head>
<body>
<div class="container">
    <ul class="nav nav-tabs">
        <form method="get" action="${pageContext.request.contextPath}/Take_Movies">
            <li class="nav-item">
                <button type="submit" class="nav-link btn">
                    <fmt:message key="navbar.main.main" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/Take_History">
            <li>
                <button type="submit" class="nav-link btn">
                    <fmt:message key="navbar.main.history" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/Sign_Out">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.main.signOut" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/Locale">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.main.local" bundle="${var}"/>
                </button>
            </li>
        </form>
    </ul>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>
                <fmt:message key="table.history.name" bundle="${var}"/>
            </th>
            <th>
                <fmt:message key="table.history.review" bundle="${var}"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="review" items="${reviewMap}">
            <tr>
                <td>
                        ${review.key}
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/Remove_Review">
                        <button type="submit" name="reviewForRemove" value="${review.value.ID}" class="btn">
                                ${review.value.review}
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
