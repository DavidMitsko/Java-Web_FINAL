<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 28.01.2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="bootstrap.jsp"%>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.rating" bundle="${var}"/>
    </title>
</head>
<body>
<div class="container">
    <form id="form" method="post" action="${pageContext.request.contextPath}/Add_Rating">
        <div class="form-group">
            <label for="usr">
                <fmt:message key="text.rating.addRating" bundle="${var}"/>
            </label>
            <input type="text" class="form-control" id="usr" name="newRating"/>
            <button type="button" class="btn btn-primary" onclick="valid()">
                <fmt:message key="button.review.add" bundle="${var}"/>
            </button>
        </div>
    </form>
</div>
</body>

<script>
    function valid() {
        const pattern = /^[0-9]*[.,]?[0-9]+$/;

        let rating = document.getElementById("usr").value;

        if(pattern.exec(rating)) {
            document.getElementById("form").submit();
        } else {
            alert("<fmt:message key="addRating.message" bundle="${var}"/>");
        }
    }
</script>

</html>
