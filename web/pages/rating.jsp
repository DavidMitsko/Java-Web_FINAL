<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 28.01.2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rating</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Add_Rating">
    <label>
        Введите свою оценку
        <input type="text" name="newRating"/>
    </label>
    <button type="submit">Добавить</button>
</form>
</body>
</html>
