<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LowCostAirlines</title>
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

<form method="post" action="login">
    <h1>
        <font size="5" color="black">Welcome to login page</font>
        <br>
    </h1>
    <table border="2" width="2" bgcolor="#d3d3d3" style="color:black">
        <tr>
            <td><b>UserName</b></td>
            <td><input class="button" type="text" name="uname"></td>
        </tr>
        <tr>
            <td><b>Password</b></td>
            <td><input class="button" type="password" name="upass"></td>
        </tr>
        <tr>
            <td><input class="button" type="submit" value="Login"></td>
        </tr>
    </table>

</form>

</body>
</html>