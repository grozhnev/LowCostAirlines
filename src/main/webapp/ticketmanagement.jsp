<%@ page import="entities.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Tickets</title>
</head>

<body>

<%
    //allow access only if session exists
    String user = (String) session.getAttribute("uname");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("uname")) userName = cookie.getValue();
            if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<h3>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %>
</h3>
<br>
User=<%=user %>
<br>
<form method="post" action="logout">
    <input class="button" type="submit" value="Logout">
</form>


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
                           value="${flight}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>

        <input type = "submit" value = "Request ticket" />
    </table>

</form>

</body>
</html>
