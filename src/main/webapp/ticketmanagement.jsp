<%@ page import="entities.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="ticketmanagement" method="POST">
    <table>
        <thead>
        <tr>
            <td width="20%">flightId</td>
            <td width="20%">dateTime</td>
            <td width="20%">airportSource</td>
            <td width="20%">airportDestination</td>
            <td width="20%">planeId</td>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${flights}" var="flight">
            <tr>
                <td align="left"><c:out value="${flight.flightId}"/></td>
                <td align="left"><c:out value="${flight.dateTime}"/></td>
                <td align="left"><c:out value="${flight.airportSource}"/></td>
                <td align="left"><c:out value="${flight.airportDestination}"/></td>
                <td align="left"><c:out value="${flight.planeId}"/></td>
                <td align="center">
                    <input type="radio" name="checkradio"
                           value="c:out value="${flight.toString()}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>

</body>
</html>
