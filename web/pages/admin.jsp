<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 27.01.2020
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="bootstrap.jsp" %>

<jsp:useBean id="userMap" class="java.util.HashMap" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.admin" bundle="${var}"/>
    </title>
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
        <form method="get" action="${pageContext.request.contextPath}/Locale">
            <li>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.admin.local" bundle="${var}"/>
                </button>
            </li>
        </form>
    </ul>
</div>

<c:if test="${userMap != null}">
    <table class="table table-striped">
        <tbody>
        <c:forEach var="user" items="${userMap}">
            <form method="post" action="${pageContext.request.contextPath}/Change_Status">
                <tr>
                    <td>
                        <button type="submit" class="btn" name="status" value="${user.key}">${user.key}</button>
                    </td>
                    <td>
                            ${user.value}
                    </td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>

    <ul class="pagination justify-content-center" style="margin:20px 0">
        <form method="get" action="${pageContext.request.contextPath}/Previous">
            <li class="page-item">
                <button type="submit" class="page-link" name="previous">
                    <fmt:message key="button.main.previous" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/Next">
            <li class="page-item">
                <button type="submit" class="page-link" name="next">
                    <fmt:message key="button.main.next" bundle="${var}"/>
                </button>
            </li>
        </form>
    </ul>
</c:if>
</body>
</html>
