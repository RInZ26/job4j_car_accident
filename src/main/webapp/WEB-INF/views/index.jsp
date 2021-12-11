<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Girls</title>
</head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    function editAccident(accident) {
        try {
            var x = new XMLHttpRequest();
            x.open("GET", "/edit", false);
            x.send();
        } catch (e) {
            console.log(e);
        }
    }

</script>
<body>
<a href="<c:url value='/create'/>">Добавить инцидент</a>
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
            <td>
                <form action="<c:url value="/edit?id=${accident.id}&name=${accident.name}&text=${accident.text}&address=${accident.address}"/>"
                      method="post"
                      enctype="multipart/form-data">
                    <button type="submit" class="btn btn-primary">Изменить</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>