<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <th>id</th>
            <td><input type='text' name='id' readonly value="${accident.id}"></td>
            <th>Name</th>
            <td><input type='text' name='name' value="${accident.name}"></td>
            <th>Text</th>
            <td><input type='text' name='text' value="${accident.text}"></td>
            <th>Address</th>
            <td><input type='text' name='address' value="${accident.address}"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Изменить" /></td>
        </tr>
    </table>
</form>
</body>
</html>