<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.01.2020
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="help/bootstrap.jsp" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.reg" bundle="${var}"/>
    </title>
</head>
<body>
<form id="form" method="post" action="${pageContext.request.contextPath}/registration">
    <div class="form-group">
        <label for="uname">
            <fmt:message key="text.reg.login" bundle="${var}"/>
        </label>
        <input type="text" class="form-control" id="uname" onchange="validLogin()"
               placeholder=
               <fmt:message key="text.reg.login.placeholder" bundle="${var}"/> name="login" required>
        <output class="text-danger" id="wrongLogin"></output>
    </div>
    <div class="form-group">
        <label for="password">
            <fmt:message key="text.reg.password" bundle="${var}"/>
        </label>
        <input type="password" class="form-control" id="password" onchange="validPassword()"
               placeholder=
               <fmt:message key="text.reg.password.placeholder" bundle="${var}"/> name="password" required>
        <output class="text-danger" id="wrongPassword"></output>
    </div>
    <div class="form-group">
        <label for="pwd">
            <fmt:message key="text.reg.password.again" bundle="${var}"/>
        </label>
        <input type="password" class="form-control" id="pwd" onchange="checkBothPassword()"
               placeholder=
               <fmt:message key="text.reg.password.placeholder" bundle="${var}"/> name="passwordAgain" required>
        <output class="text-danger" id="matchPasswords"></output>
    </div>
    <button type="button" onclick="valid()" class="btn btn-primary">
        <fmt:message key="button.reg.enter" bundle="${var}"/>
    </button>
</form>
</body>

<script>
    let loginFlag = false;
    let passwordFlag = false;
    let matchPasswords = false;

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
        let password = document.getElementById("password").value;

        if (!passwordPattern.exec(password)) {
            passwordFlag = false;
            document.getElementById("wrongPassword").innerHTML = "<fmt:message key="signIn/reg.wrongPassword.message" bundle="${var}"/>";
        } else {
            passwordFlag = true;
            document.getElementById("wrongPassword").innerHTML = "";
        }
    }

    function checkBothPassword() {
        let password = document.getElementById("password").value;
        let againPassword = document.getElementById("pwd").value;

        if (password != againPassword) {
            matchPasswords = false;
            document.getElementById("matchPasswords").innerHTML = "<fmt:message key="reg.message" bundle="${var}"/>";
        } else {
            matchPasswords = true;
            document.getElementById("matchPasswords").innerHTML = "";
        }
    }

    function valid() {
        if (loginFlag && passwordFlag && matchPasswords) {
            document.getElementById("form").submit();
        } else {
            alert("<fmt:message key="signIn/reg.message" bundle="${var}"/>");
        }
    }
</script>

</html>
