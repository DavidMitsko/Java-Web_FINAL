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

<%@ include file="help/bootstrap.jsp" %>

<jsp:useBean id="reviewMap" type="java.util.HashMap<java.lang.String, com.mitsko.mrdb.entity.Review>" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<c:import url="header/mainNavbar.jsp" var="navbar"/>
<html>
<head>
    <title>
        <fmt:message key="title.history" bundle="${var}"/>
    </title>
</head>
<body>

${navbar}

<div class="container mt-3">

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
                    <form method="post" action="${pageContext.request.contextPath}/remove_review">
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
