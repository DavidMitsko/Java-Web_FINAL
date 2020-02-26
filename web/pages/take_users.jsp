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

<%@include file="help/bootstrap.jsp" %>
<c:import url="header/adminNavbar.jsp" var="navbar"/>

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

${navbar}

<div class="container mt-3">

    <c:if test="${userMap.size() != 0}">
        <table class="table table-striped">
            <tbody>
            <c:forEach var="user" items="${userMap}">
                <form method="post" action="${pageContext.request.contextPath}/change_status">
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
    </c:if>
</div>
</body>
</html>
