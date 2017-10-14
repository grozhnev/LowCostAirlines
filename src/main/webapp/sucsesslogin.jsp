<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<h1><font size="3" color="black">Airports: ${airports}</font></h1>
<h1><font size="3" color="black">Planes: ${planes}</font></h1>
<h1><font size="3" color="black">Customers: ${customers}</font></h1>

<a href="/">Back to main page</a>
</body>
</html>


