<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 02.02.2020
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddMovie</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Add_Movie" enctype="multipart/form-data">
    <label>
        Введите название:
        <input type="text" name="movieName">
    </label>
    <br>
    <label>
        Введите описание:
        <input type="text" name="movieDescription">
    </label>
    <br>
    <input type="file" name="movieImage">
    <br>
    <button type="submit">Добавить</button>
</form>
</body>
</html>
