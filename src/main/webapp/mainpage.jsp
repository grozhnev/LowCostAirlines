<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>LowCostAirlines</title>
    </head>
    <body>

    <form method="post" action="Login">
        <h1>
        <font size="5" color="black">Welcome to Lowcoster home</font>
            <br>
            <font size="3" color="gray">To continue please log in</font>
        </h1>
        <table border="2" width="2" bgcolor="#d3d3d3" style="color:black">
            <tr><td><b>UserName</b></td> <td><input type="text" name="uname"></td></tr>
            <tr><td><b>Password</b></td> <td><INPUT type="password" name="upass"></td></tr>
            <tr><td><input type="submit" value="Login"></td>
                <td><input type="reset" value="Reset"></td>
        </table>

    </form>

    </body>
</html>

