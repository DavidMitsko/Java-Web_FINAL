<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 03.02.2020
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="help/bootstrap.jsp" %>
<c:import url="header/adminNavbar.jsp" var="navbar"/>

<jsp:useBean id="movieList" type="java.util.ArrayList<com.mitsko.mrdb.entity.Movie>" scope="request"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>Remove Movie</title>
</head>
<body>
${navbar}

<div class="container mt-3">


    <ul class="list-group">
        <c:forEach var="movie" items="${movieList}">
            <li class="list-group-item">
                <output type="text" class="text">
                        ${movie.name}
                </output>

                <div class="container mt-3">
                    <form method="get" action="${pageContext.request.contextPath}/change_movie_page">
                        <button type="submit" class="btn btn-outline-success my-2 my-sm-0" name="movieName" value="${movie.name}">
                            Change
                        </button>
                    </form>
                    <form method="post" action="${pageContext.request.contextPath}/remove_movie">
                        <button type="submit" class="btn btn-outline-secondary my-2 my-sm-0" name="movieForRemove" value="${movie.name}">
                            Delete
                        </button>
                    </form>
                </div>
            </li>
        </c:forEach>
    </ul>

    <ul class="pagination justify-content-center" style="margin:20px 0">
        <form method="get" action="${pageContext.request.contextPath}/previous">
            <li class="page-item">
                <button type="submit" class="page-link" name="previous">
                    <fmt:message key="button.main.previous" bundle="${var}"/>
                </button>
            </li>
        </form>
        <form method="get" action="${pageContext.request.contextPath}/next">
            <li class="page-item">
                <button type="submit" class="page-link" name="next">
                    <fmt:message key="button.main.next" bundle="${var}"/>
                </button>
            </li>
        </form>
    </ul>

</div>
</body>
</html>
