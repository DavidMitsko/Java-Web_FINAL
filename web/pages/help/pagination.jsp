<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.02.2020
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="size" type="java.lang.Integer" scope="request"/>
<jsp:useBean id="currentPage" type="java.lang.Integer" scope="request"/>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
</head>
<body>
<ul class="pagination justify-content-center" style="margin:20px 0">
    <form method="get" action="${pageContext.request.contextPath}/previous">
        <li class="page-item">
            <button type="submit" class="page-link" name="previous">
                <fmt:message key="button.main.previous" bundle="${var}"/>
            </button>
        </li>
    </form>
    <c:forEach begin="${1}" end="${size}" step="${1}" var="i">
        <c:if test="${currentPage != i}">
            <form method="get" action="${pageContext.request.contextPath}/change_page">
                <li class="page-item">
                    <button type="submit" class="page-link" name="page" value="${i}">
                            ${i}
                    </button>
                </li>
            </form>
        </c:if>

        <c:if test="${currentPage == i}">
            <form method="get" action="${pageContext.request.contextPath}/change_page">
                <li class="page-item active">
                    <button type="submit" class="page-link" name="page" value="${i}">
                            ${i}
                    </button>
                </li>
            </form>
        </c:if>
    </c:forEach>
    <form method="get" action="${pageContext.request.contextPath}/next">
        <li class="page-item">
            <button type="submit" class="page-link" name="next">
                <fmt:message key="button.main.next" bundle="${var}"/>
            </button>
        </li>
    </form>
</ul>

</body>
</html>
