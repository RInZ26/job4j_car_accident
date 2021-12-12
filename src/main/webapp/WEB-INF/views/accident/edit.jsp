<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <th>id</th>
            <td><input type='text' name='id' readonly value="${editedAccident.id}"></td>
            <th>Name</th>
            <td><input type='text' name='name' value="${editedAccident.name}"></td>
            <th>Text</th>
            <td><input type='text' name='text' value="${editedAccident.text}"></td>
            <th>Address</th>
            <td><input type='text' name='address' value="${editedAccident.address}"></td>

        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
        </tr>

        <tr>
            <td>Статьи:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Изменить"/></td>
        </tr>
    </table>
</form>
</body>
</html>