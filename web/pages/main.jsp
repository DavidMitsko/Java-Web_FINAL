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

<%@include file="bootstrap.jsp" %>

<jsp:useBean id="movieList" class="java.util.ArrayList" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.main" bundle="${var}"/>
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
                <input type="hidden" name="page" value="${pageContext.request.requestURL}"/>
                <button type="submit" class="btn float-right">
                    <fmt:message key="navbar.main.local" bundle="${var}"/>
                </button>
            </li>
        </form>
    </ul>

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
                            <img src="${movie.imageName}" width="187" height="287" alt="Noooooooo"/>
                        </c:if>
                    </td>
                    <form method="get" action="${pageContext.request.contextPath}/Take_Reviews">
                        <td>
                            <button type="submit" name="movieNameForReview" value="${movie.name}"
                                    class="btn"> ${movie.name} </button>
                        </td>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/Rating">
                        <td>
                            <button type="submit" name="movieNameForRating"
                                    value="${movie.name}" class="btn"> ${movie.averageRating} </button>
                        </td>
                    </form>
                </tr>

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
