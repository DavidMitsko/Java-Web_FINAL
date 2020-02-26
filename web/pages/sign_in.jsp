<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.01.2020
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="help/bootstrap.jsp" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>

<jsp:useBean id="message" scope="request" class="java.lang.String"/>
<html>
<head>
    <title>
        <fmt:message key="title.login" bundle="${var}"/>
    </title>
</head>
<body>
<div class="container">
    <form id="form" method="post" action="${pageContext.request.contextPath}/sign_in">

        <div class="form-group">
            <label for="uname">
                <fmt:message key="text.login.login" bundle="${var}"/>
            </label>
            <input type="text" class="form-control" id="uname" onchange="validLogin()"
                   placeholder=
                   <fmt:message key="text.login.login.placeholder" bundle="${var}"/> name="login" required>
            <output class="text-danger" id="wrongLogin"></output>
        </div>
        <div class="form-group">
            <label for="pwd">
                <fmt:message key="text.login.password" bundle="${var}"/>
            </label>
            <input type="password" class="form-control" id="pwd" onchange="validPassword()"
                   placeholder=
                   <fmt:message key="text.login.password.placeholder" bundle="${var}"/> name="password" required>
            <output class="text-danger" id="wrongPassword"></output>
        </div>

        <div class="text-danger">
            <output class="text-danger" id="danger"></output>
        </div>

        <c:if test="${!message.equals(\"\")}">
            <div class="text-danger">
                <output class="text-danger">${message}</output>
            </div>
        </c:if>

        <button type="button" class="btn btn-primary" onclick="valid()">
            <fmt:message key="button.login.enter" bundle="${var}"/>
        </button>

    </form>
    <form method="get" action="${pageContext.request.contextPath}pages/registration.jsp">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="button.login.reg" bundle="${var}"/>
        </button>
    </form>
</div>
</body>

<script>
    let loginFlag = false;
    let passwordFlag = false;

    function validLogin() {
        const loginPattern = /^[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]{3,20}$/;
        let login = document.getElementById("uname").value;

        if (!loginPattern.exec(login)) {
            loginFlag = false;
            document.getElementById("wrongLogin").innerHTML = "<fmt:message key="signIn/reg.wrongLogin.message" bundle="${var}"/>";
        } else {
            loginFlag = true;
            document.getElementById("wrongLogin").innerHTML = "";
        }
    }

    function validPassword() {
        const passwordPattern = /^[a-zA-Z]{1}[a-zA-Z1-9]{3,20}$/;
        let password = document.getElementById("pwd").value;

        if (!passwordPattern.exec(password)) {
            passwordFlag = false;
            document.getElementById("wrongPassword").innerHTML = "<fmt:message key="signIn/reg.wrongPassword.message" bundle="${var}"/>";
        } else {
            passwordFlag = true;
            document.getElementById("wrongPassword").innerHTML = "";
        }
    }

    function valid() {
        if (loginFlag && passwordFlag) {
            document.getElementById("form").submit();
        } else {
            alert("<fmt:message key="signIn/reg.message" bundle="${var}"/>");
        }
    }
</script>

</html>
