<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 26.01.2020
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Sign_In">
    <label>
        Введите свой логин:
        <input type="text" name="login"/>
    </label>
    <br>
    <label>
        Введите пароль:
        <input type="password" name="password"/>
    </label>
    <br>
    <button type="submit">Войти</button>
</form>
</body>
</html>
