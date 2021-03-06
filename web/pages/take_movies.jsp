<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 27.01.2020
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="help/bootstrap.jsp" %>

<jsp:useBean id="movieList" class="java.util.ArrayList" scope="request"/>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<c:import url="header/mainNavbar.jsp" var="navbar"/>
<c:import url="help/pagination.jsp" var="pagination"/>
<html>
<head>
    <title>
        <fmt:message key="title.main" bundle="${var}"/>
    </title>
</head>
<body>

${navbar}

<div class="container mt-3">

    <c:if test="${movieList.size() != 0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th></th>
                <th>
                    <fmt:message key="table.main.name" bundle="${var}"/>
                </th>
                <th>
                    <fmt:message key="table.main.rating" bundle="${var}"/>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="movie" items="${movieList}">
                <tr>
                    <td>
                        <c:if test="${movie.imageName != null}">
                            <img src="${pageContext.request.contextPath}/get_image?fileName=${movie.imageName}"
                                 width="187" height="287" alt="Noooooooo"/>
                        </c:if>
                    </td>
                    <form method="get" action="${pageContext.request.contextPath}/take_reviews">
                        <td>
                            <button type="submit" name="movieNameForReview" value="${movie.name}"
                                    class="btn"> ${movie.name} </button>
                        </td>
                    </form>
                    <form method="get" action="${pageContext.request.contextPath}/rating">
                        <td>
                            <button type="submit" name="movieNameForRating"
                                    value="${movie.name}" class="btn"> ${movie.averageRating} </button>
                        </td>
                    </form>
                </tr>

            </c:forEach>
            </tbody>
        </table>

        ${pagination}
    </c:if>
</div>
</body>
</html>
