<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.01.2020
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="bootstrap.jsp" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.login" bundle="${var}"/>
    </title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Sign_In">

    <div class="form-group">
        <label for="uname">
            <fmt:message key="text.login.login" bundle="${var}"/>
        </label>
        <input type="text" class="form-control" id="uname"
               placeholder=
               <fmt:message key="text.login.login.placeholder" bundle="${var}"/> name="login" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="form-group">
        <label for="pwd">
            <fmt:message key="text.login.password" bundle="${var}"/>
        </label>
        <input type="password" class="form-control" id="pwd"
               placeholder=
               <fmt:message key="text.login.password.placeholder" bundle="${var}"/> name="password" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <button type="submit" class="btn btn-primary">
        <fmt:message key="button.login.enter" bundle="${var}"/>
    </button>
</form>
</body>
</html>
