<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 27.01.2020
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="userList" class="java.util.ArrayList" scope="request"/>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/Add_Movie">
    <label>
        Добавьте фильм:
        <input type="text" name="movieName"/>
    </label>
    <button type="submit" name="addMovie">Добавить фильм</button>
    <br>
    <br>
</form>
<table>
    <c:forEach var="user" items="${userList}">
        <form method="post" action="${pageContext.request.contextPath}/Change_Status">
            <tr>
                <td>
                    <button type="submit" name="status" value="${user}">${user}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</body>
</html>
