<%@ page contentType="text/html;charset=UTF-8" %>
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

    <h1><span style="font-size: xx-large; color: black; ">Lowcoster Airlines</span></h1>

    <h1><span style="font-size: medium; color: black; ">to continue choose:</span></h1>
    <br>
    <form method="post" action="login">
            <input class="button" style="width: 30%" type="submit" value="Login in system">
    </form>

    <form method="post" action="registration">
        <input class="button" style="width: 30%" type="submit" value="Registration">
    </form>

    </body>
</html>

