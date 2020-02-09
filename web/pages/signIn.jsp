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
<form id="form" method="post" action="${pageContext.request.contextPath}/Sign_In">

    <div class="form-group">
        <label for="uname">
            <fmt:message key="text.login.login" bundle="${var}"/>
        </label>
        <input type="text" class="form-control" id="uname"
               placeholder=
               <fmt:message key="text.login.login.placeholder" bundle="${var}"/> name="login" required>
    </div>
    <div class="form-group">
        <label for="pwd">
            <fmt:message key="text.login.password" bundle="${var}"/>
        </label>
        <input type="password" class="form-control" id="pwd"
               placeholder=
               <fmt:message key="text.login.password.placeholder" bundle="${var}"/> name="password" required>
    </div>
    <button type="button" class="btn btn-primary" onclick="valid()">
        <fmt:message key="button.login.enter" bundle="${var}"/>
    </button>
    <p id="1"></p>
    <p id="2"></p>
</form>
</body>

<script>
    function valid() {
        let flag = true;

        const loginPattern = /^[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]{3,20}$/;
        const passwordPattern = /^[a-zA-Z]{1}[a-zA-Z1-9]{3,20}$/;

        let login = document.getElementById("uname").value;
        let password = document.getElementById("pwd").value;

        if (!loginPattern.exec(login)) {
            flag = false;
        }

        if(!passwordPattern.exec(password)) {
            flag = false;
        }

        if(flag) {
            document.getElementById("form").submit();
        } else {
            alert("<fmt:message key="signIn/reg.message" bundle="${var}"/>");
        }

    }
</script>

</html>
