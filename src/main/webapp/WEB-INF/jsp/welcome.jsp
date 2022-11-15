<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <link rel="stylesheet" href="/resources/static/bootstrap.min.css">
        <script src="/resources/static/jquery-3.6.1.min.js"></script>
        <script src="/resources/static/bootstrap.min.js"></script>
    </head>
    <body>
        <h4>HI</h4>
        <br>
        <br>
        <form action="/logout" method="post">
            <button id="click">Log Out</button>
        </form>
        <br>

        <script type="text/javascript">
            $("#click").onclick(function () {
                alert("dfdfdfdfdf");
            })
        </script>
    </body>
</html>