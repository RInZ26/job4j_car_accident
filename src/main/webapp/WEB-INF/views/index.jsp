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
        <th>Name</th>
        <th>Text</th>
        <th>Address</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${accidents}" var="accident">
        <tr>
            <th scope="row"></th>
            <td>${accident.name}</td>
            <td>${accident.text}</td>
            <td>${accident.address}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>