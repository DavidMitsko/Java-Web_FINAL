<%--
  Created by IntelliJ IDEA.
  User: Давид
  Date: 02.02.2020
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="error" type="java.lang.String" scope="request"/>
<html>
<head>
    <title>Error</title>
</head>
<body>
${error}
</body>
</html>
