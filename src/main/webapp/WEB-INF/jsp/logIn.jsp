<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Hugo 0.84.0">
        <title>Headers · Bootstrap v5.0</title>
        <link rel="stylesheet" href="/resources/static/bootstrap.min.css">
        <script src="/resources/static/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    </head>
    <body>
        <h4 style="color: blue">Please Log In First</h4>
        <br>
        <br>
        <p id="message">${message}</p>
        <br>
        <form:form id="form">
            <input id="name" type="text" placeholder="Enter your name" name="username"><br>
            <input id="password" type="text" placeholder="Enter your password" name="password"><br>
            <button type="button" id="btnSubmit" onclick="show()"> Submit </button>
        </form:form>




        <script type="text/javascript">

           $("#btnSubmit").onclick(function () {
               alert("hi");
           })

            function show(){
                var name = document.querySelector("#name").value;
                var pass = document.querySelector("#password").value;

                var form = document.querySelector("#form");

                var data = new FormData(form);

                if(name != null && name.trim().length > 0 && pass != null && pass.trim().length > 0){

                    var request = new XMLHttpRequest();
                    request.open("POST","/verification", true);
                    request.send(data);



                    request.onreadystatechange= function () {
                        if (this.readyState == 4 && this.status == 200) {
                            // if(this.responseText == null){
                            //     window.location = "http://localhost:8080/welcome";
                            //     alert(window.location);
                            //     window.location.reload();
                            // }else{
                            //     message = this.responseText;
                            // }


                            if(this.responseText == "success"){
                                window.location.href = "/welcome";
                            }else{
                                alert(this.responseText);
                            }
                        }
                    }
                }else{
                    alert("Please select mandatory fields.");
                }

            }


        </script>
    </body>
</html>

