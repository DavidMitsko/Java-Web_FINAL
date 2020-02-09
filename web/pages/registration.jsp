<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.01.2020
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="bootstrap.jsp" %>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>
        <fmt:message key="title.reg" bundle="${var}"/>
    </title>
</head>
<body>
<form id="form" method="post" action="${pageContext.request.contextPath}/Registration">
    <div class="form-group">
        <label for="uname">
            <fmt:message key="text.reg.login" bundle="${var}"/>
        </label>
        <input type="text" class="form-control" id="uname"
               placeholder=
               <fmr:message key="text.reg.login.placeholder" bundle="${var}"/> name="login" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="form-group">
        <label for="password">
            <fmt:message key="text.reg.password" bundle="${var}"/>
        </label>
        <input type="password" class="form-control" id="password"
               placeholder=
               <fmt:message key="text.reg.password.placeholder" bundle="${var}"/> name="password" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <div class="form-group">
        <label for="pwd">
            <fmt:message key="text.reg.password.again" bundle="${var}"/>
        </label>
        <input type="password" class="form-control" id="pwd"
               placeholder=
               <fmt:message key="text.reg.password.placeholder" bundle="${var}"/> name="passwordAgain" required>
        <div class="valid-feedback">Valid.</div>
        <div class="invalid-feedback">Please fill out this field.</div>
    </div>
    <button type="button" onclick="valid()" class="btn btn-primary">
        <fmt:message key="button.reg.enter" bundle="${var}"/>
    </button>
</form>
</body>

<script>
    function valid() {
        let flag = true;
        let equalsFlag = true;

        const loginPattern = /^[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]{3,20}$/;
        const passwordPattern = /^[a-zA-Z]{1}[a-zA-Z1-9]{3,20}$/;

        let login = document.getElementById("uname").value;
        let password = document.getElementById("password").value;
        let passwordAgain = document.getElementById("pwd").value;

        if (!loginPattern.exec(login)) {
            flag = false;
        }

        if (password !== passwordAgain) {
            equalsFlag = false;
            flag = false;
        }

        if (!passwordPattern.exec(password)) {
            flag = false;
        }

        if (flag) {
            document.getElementById("form").submit();
        } else if (!equalsFlag) {
            alert("<fmt:message key="reg.message" bundle="${var}"/>");
        } else {
            alert("<fmt:message key="signIn/reg.message" bundle="${var}"/>");
        }
    }
</script>

</html>
