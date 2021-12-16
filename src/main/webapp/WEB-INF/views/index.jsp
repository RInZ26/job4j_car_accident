<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accidents</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<body>
<div>
    Login as : ${user.username}
</div>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Text</th>
        <th>Address</th>
        <th>Type</th>
        <th>Rules</th>

    </tr>
    </thead>
    <tbody>

    <c:forEach items="${accidents}" var="accident">
        <tr>
            <th scope="row"></th>
            <td>${accident.name}</td>
            <td>${accident.text}</td>
            <td>${accident.address}</td>
            <td>${accident.type.name}</td>

            <c:if test="${accident.rules.size() == 0}">
                <td>Статьи отсутствуют</td>
                >
            </c:if>>
            <c:forEach var="rule" items="${accident.rules}">
                <td>${rule.name}</td>
            </c:forEach>

            <td>
                <a href="<c:url value='/edit?id=${accident.id}'/>">Изменить</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>