<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>LowCostAirlines</title>
        <style>
            body{
                background-color: lightgray;
            }
            table, th, td {
                border: 4px solid black;
            }
            .button {
                background-color: darkgray;
                border: none;
                color: black;
                padding: 20px 32px;
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
    <h1><font size="7" color="black">Lowcoster Airlines</font></h1>
    <br>
    <h1><font size="4" color="black">to continue choose:</font></h1>
    <br>
    <form method="post" action="login">
            <td ><input class="button" type="submit" value="Login in system"></td>
    </form>
    <br>
    <form method="post" action="registration">
        <tr><td><input class="button" type="submit" value="Registration"></td>
    </form>
    </body>
</html>

