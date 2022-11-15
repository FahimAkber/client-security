<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>

    </head>
    <body>
        <h4>You have no right to access the page</h4>
        <br>
        <br>
        <br>

        <form action="/logout" method="post">
            <button>Log Out</button>
        </form>
    </body>
</html>