<%-- 
    Document   : Login
    Created on : Mar 3, 2016, 6:30:14 PM
    Author     : Ahmed Ashraf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <link rel="stylesheet" href="Resources/css/style1.css">
        <link rel="stylesheet" href="Resources/css/bootstrap.css"> 
        <link rel="stylesheet" href="Resources/css/font-awesome.min.css">   
        <link rel="stylesheet" href="Resources/css/style.css" >
        <link rel="stylesheet" href="Resources/css/media.css" >
        <link rel="stylesheet" href="Resources/css/defult-theme.css" >
        <link rel="stylesheet" href='Resources/css/hover.css'>
        <link rel="stylesheet" href='Resources/css/animate.css'>
        <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'>
        <link type="text/css" rel="stylesheet" href="Resources/css/jquery.ui.css"/>
        <link type="text/css" rel="stylesheet" href="Resources/css/default.css"/>
        <link type="text/css" rel="stylesheet" href="Resources/css/tooltipster.css"/>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>  
        <script type="text/javascript" src="Resources/js/jquery.tooltipster.min.js"></script>
        <script type="text/javascript" src="Resources/js/html5shiv.min.js"></script>
        <script type="text/javascript" src="Resources/js/respond.min.js"></script>
        <script type="text/javascript" src="Resources/js/jquery.poptrox.min.js"></script>
        <script type="text/javascript" src="Resources/lib/hash.js"></script>
        <script type="text/javascript" src="Resources/lib/turn.min.js"></script>
        <script type="text/javascript" src="Resources/lib/zoom.min.js"></script>
        <script type="text/javascript" src="Resources/lib/bookshelf.js"></script>
        <script src="Resources/js/bootstrap.min.js"></script>
        <script src="Resources/js/plugin.js"></script>
        <script src="Resources/js/wow.min.js"></script>
        <!--<script>new WOW().init();</script>-->

        <script>
             $.ajax({
                    url: "Login",
                    type: 'GET',
                    async: false,
                    data: {},
                    success: function (data, textStatus, jqXHR) {
                        if(data== "user"){
                            window.location.href = "UserHome.jsp";
                        }
                    }
                });
        </script>
    </head>


    <body> 
        <jsp:include page="htmls/StartOfSigninPage.html"></jsp:include>
            <br> <br> <br> <br>
            <div class="container">

                <section id="content">
                    <form  action="Login" method="post">
                        <h1>Login Form</h1>
                        <div>
                            <input type="text" placeholder="Username" required="" id="userName"  name="userName"/>
                        </div>
                        <div>
                            <input type="password" placeholder="Password" required="" id="password"  name="password"/>
                        </div>
                        <div>
                            <input type="submit" value="Login" />
                            <div><input id="remember" name="remember" type="checkbox" value="rememberme"><span style="color:#FF1F1F">Remember me </span> </div>
                            <a href="SignUp.jsp">Register</a>
                        </div>
                    </form>
                <c:if test = "${error=='1'}">
                    <div style="color: red;">login failed</div>
                </c:if>
            </section>
        </div>

        <jsp:include page="htmls/RestOfMainPage.html"></jsp:include>   
    </body>
</html>

