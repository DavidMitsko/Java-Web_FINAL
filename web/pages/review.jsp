<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 27.01.2020
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="bootstrap.jsp" %>

<jsp:useBean id="movieName" type="java.lang.String" scope="session"/>
<jsp:useBean id="review" type="java.util.HashMap<java.lang.String, com.mitsko.mrdb.entity.Review>" scope="request"/>
<jsp:useBean id="user" type="java.util.HashMap<java.lang.String, java.lang.Integer>" scope="request"/>
<jsp:useBean id="description" class="java.lang.String" scope="request"/>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<%@ taglib prefix="v" uri="http://MRDb.mitsko.com" %>
<c:import url="star.svg" var="star"/>
<html>
<head>
    <title>
        <fmt:message key="title.review" bundle="${var}"/>
    </title>
</head>
<body>
<div class="container">

    <label>
        <c:if test="${description != null}">
            ${description}
            <br>
        </c:if>
    </label>

    <v:ReviewTag reviewHashMap="${review}" usersRatingHashMap="${user}" star="${star}"/>

    <form method="post" action="${pageContext.request.contextPath}/Add_Review">
        <div class="form-group">
            <label for="usr">
                <fmt:message key="text.review.addReview" bundle="${var}"/> ${movieName}:
            </label>
            <input type="text" class="form-control" id="usr" name="usersReview">
            <button type="submit" name="addReview">Добавить</button>
        </div>
    </form>

</div>
</body>
</html>
