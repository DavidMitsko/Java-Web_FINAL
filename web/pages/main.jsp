<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 27.01.2020
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="movieList" class="java.util.ArrayList" scope="request"/>
<html>
<head>
    <title>Main</title>
</head>
<body>
<table>
    <tr>
        <th>Image</th>
        <th>Name</th>
        <th>Average Rating</th>
    </tr>
    <c:forEach var="movie" items="${movieList}">
        <tr>
            <td>
                <c:if test="${movie.imageName != null}">
                    <img src="${movie.imageName}" width="187" height="287" alt="Noooooooo"/>
                </c:if>
            </td>
            <form method="get" action="${pageContext.request.contextPath}/Take_Reviews">
                <td>
                    <button type="submit" name="movieNameForReview" value="${movie.name}"> ${movie.name} </button>
                </td>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/Rating">
                <td>
                    <button type="submit" name="movieNameForRating" value="${movie.name}"> ${movie.averageRating} </button>
                </td>
            </form>
        </tr>

    </c:forEach>
</table>
<form method="get" action="${pageContext.request.contextPath}/Sign_Out">
    <input type="submit" value="SignOut"/>
</form>
</body>
</html>
