<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<select name="airport">
    <c:forEach items="${airports}" var="airport">
        <option value="${airport.airportId}"><c:out value="${airport.name}" /></option>
    </c:forEach>
</select>
</body>
</html>
