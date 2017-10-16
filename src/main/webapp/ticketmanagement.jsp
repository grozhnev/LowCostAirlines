<%@ page import="entities.Flight" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Tickets</title>
    <style>
        body {
            background-color: lightgray;
        }

        table, th, td {
            border: 1px solid black;
            border-radius: 3px;
        }

        .button {
            background-color: darkgray;
            border: none;
            color: black;
            padding: 12px 22px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 8px;
        }
    </style>
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
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<form action="ticketmanagement" method="POST">
    <table>
        <thead>
        <tr>
            <td width="25%"><b>Date & time</b></td>
            <td width="25%"><b>Source</b></td>
            <td width="25%"><b>Destination</b></td>
            <td width="25%"><b>Plane</b></td>
            <td width="25%"><b>Selection</b></td>
        </tr>
        </thead>

        <tbody>

        <c:forEach items="${flights}" var="flight">
            <tr>
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
        
    </table>
    <input type = "submit" value = "Request ticket" />
</form>

<a href="/">Back to main page</a>

</body>
</html>
