<%@ page import="entities.Flight" %>

<%@ page import="entities.Ticket" %>
<%@ page import="entities.Plane" %>
<%@ page import="entities.Airport" %>
<%@ page import="entities.Customer" %>

<%@ page contentType="text/html;charset=UTF-8" %>
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
    String user = (String) session.getAttribute("email");
    String userName = null;
    String sessionID = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) userName = cookie.getValue();
            if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>
<h3>Hi <%=userName %>, Login successful. %>
</h3>
<br>
User=<%=userName %>
<br>
<form method="post" action="logout">
    <input class="button" type="submit" value="Logout">
</form>

<form action="ticketmanagement" method="POST">
    <table>
        <thead>
        <tr>
            <td width="20%">flightId</td>
            <td width="25%"><b>Date & time</b></td>
            <td width="25%"><b>Source</b></td>
            <td width="25%"><b>Destination</b></td>
            <td width="25%"><b>Plane</b></td>
            <td width="20%"><b>price</b></td>

            <td width="25%"><b>Selection</b></td>
        </tr>
        </thead>

        <tbody>
<%--suppress ELValidationInJSP --%>
<c:forEach items="${flights}" var="flight">
            <tr>
                <td align="left"><c:out value="${flight.flightId}"/></td>
                <td align="left"><c:out value="${flight.dateTime}"/></td>
                <td align="left"><c:out value="${flight.airportSource}"/></td>
                <td align="left"><c:out value="${flight.airportDestination}"/></td>
                <td align="left"><c:out value="${flight.planeId}"/></td>
                <td align="left"><c:out value="${flight.price}"/></td>

                <td align="center">
                    <input type="radio" name="checkradio"
                           value="${flight}"/>
                </td>
            </tr>
        </c:forEach>

    </table>

    <input type="checkbox" name="registrationpriority" value="true">
    <a>Registration priority</a>
    <br>

    <a><b>Weight:</b></a>
    <input type="radio" name="weight" value="20" checked="checked"><a>20</a>
    <input type="radio" name="weight" value="50"><a>50</a>
    <input type="radio" name="weight" value="100"><a>100</a>
    <br>

    <input type = "submit" value = "Request ticket" />

</form>

<a href="<c:url value="/"/>">Back to main page</a>

</body>
</html>
