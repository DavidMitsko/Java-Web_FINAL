<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 24.01.2020
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="text" var="var"/>
<html>
<head>
    <title>MRDb</title>
</head>
<body>
<a href="pages/signIn.jsp">
    <button type="submit">
        <fmt:message key="signIn" bundle="${var}"/>
    </button>
</a>
<br>
<a href="pages/registration.jsp">
    <button type="submit">
        <fmt:message key="reg" bundle="${var}"/>
    </button>
</a>
</body>
</html>
