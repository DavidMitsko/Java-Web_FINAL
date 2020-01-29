<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 27.01.2020
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="reviewList" type="java.util.ArrayList" scope="request"/>
<jsp:useBean id="movieName" type="java.lang.String" scope="session"/>
<html>
<head>
    <title>Review</title>
    <style>
        .layer {
            padding-left: 25px;
        }
    </style>
</head>
<body>
<table>
    <c:if test="${reviewList != null}">
        <c:forEach var="review" items="${reviewList}">
            <tr>
                <td>
                        ${review.userLogin}
                <td>
            </tr>
            <tr>
                <td class="layer">
                        ${review.review}
                <td>
            </tr>
        </c:forEach>
    </c:if>
</table>
<br>
<form method="post" action="${pageContext.request.contextPath}/Add_Review">
    <label>
        Напишите здесь свой отзыв о ${movieName}:
        <input type="text" name="usersReview">
    </label>
    <button type="submit" name="addReview">Добавить</button>
</form>
</body>
</html>