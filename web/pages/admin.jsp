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


<div class="container">
    ${navbar}

    <c:if test="${userMap.size() != 0}">
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
</div>
</body>
</html>
