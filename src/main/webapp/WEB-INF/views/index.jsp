<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Girls</title>
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th>UserName</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${users}" var="user">
        <tr>
            <th scope="row"></th>
            <td>${user}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>