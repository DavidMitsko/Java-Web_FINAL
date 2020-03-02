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
<%@ taglib prefix="v" uri="http://MRDb.mitsko.com" %>

<%@ include file="help/bootstrap.jsp" %>

<jsp:useBean id="review" type="java.util.HashMap<java.lang.String, com.mitsko.mrdb.entity.Review>" scope="request"/>
<jsp:useBean id="user" type="java.util.HashMap<java.lang.String, java.lang.Integer>" scope="request"/>
<jsp:useBean id="description" class="java.lang.String" scope="request"/>

<c:import url="header/mainNavbar.jsp" var="navbar"/>
<c:import url="graphics/star.svg" var="star"/>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<html>
<head>
    <title>
        <fmt:message key="title.review" bundle="${var}"/>
    </title>
</head>
<body>

${navbar}
<div class="container mt-3">

    <label>
        <c:if test="${description != null}">
            ${description}
            <br>
        </c:if>
    </label>



    <v:ReviewTag reviewHashMap="${review}" usersRatingHashMap="${user}"/>

    <form id="form" method="post" action="${pageContext.request.contextPath}/add_review">
        <div class="form-group">
            <label for="review">
                <fmt:message key="text.review.addReview" bundle="${var}"/>:
            </label>
            <input type="text" class="form-control" id="review" name="usersReview">
            <div class="text-danger">
                <output class="text-danger" id="error"></output>
            </div>
            <button type="button" name="addReview" class="btn btn-primary" onclick="valid()">
                <fmt:message key="button.review.add" bundle="${var}"/>
            </button>
        </div>
    </form>

</div>

<footer>

</footer>
</body>

<script>
    function valid() {
        const pattern = /^[a-zA-Z0-9]{1,1000}$/;

        let review = document.getElementById("review").value;

        if (pattern.exec(review)) {
            document.getElementById("form").submit();
        } else {
            document.getElementById("error").innerHTML = "<fmt:message key="addReview.message" bundle="${var}"/>";
        }
    }


</script>

</html>
