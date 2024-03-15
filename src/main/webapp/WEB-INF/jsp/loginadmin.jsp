<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Admin Login | Restaurant</title>
    <link rel="shortcut icon" type="image/png" href="<c:url value='/resources/assets/img/favicon.png'/>" />
    
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <meta name="author" content="Niladri Mondal" />
    <meta name="description" content="Welcome to Restora." />
    <meta name="keywords" content="niladri mondal, restora, food, restaurant" />
    
    <meta property="og:site_name" content="Restora" />
    <meta property="og:title" content="Welcome to Restora." />
    <meta property="og:description" content="Welcome to Restora." />
    <meta property="og:image" content="<c:url value='/resources/assets/img/opengraph.png'/>" />
    <meta property="og:url" content="https://www.google.com/" />
    
    <meta name="theme-color" content="#181a20" />
    <meta name="msapplication-navbutton-color" content="#181a20" />
    
    <link rel="stylesheet" href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css" />
    
    <link rel="stylesheet" href="<c:url value='/resources/css/libraries/bootstrap.min.css'/>" />
    
    <link rel="stylesheet" href="<c:url value='/resources/css/components/settings.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/components/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/login.css'/>" />
    
    <script defer src="<c:url value='/resources/js/libraries/jquery-3.7.1.min.js'/>"></script>
    <script defer src="<c:url value='/resources/js/main.js'/>"></script>
</head>

<body>

    <% response.setHeader("Cache-Control", "no-cache, no-store" ); %>
    
    <div id="patternBg"></div>
    
    <header>
        <nav class="navbar">
    
            <a href="/" class="nav_brand">
                <img src="<c:url value='/resources/assets/img/logo.svg'/>" alt="Restora Logo" />
                <span>Restora</span>
            </a>
    
            <div class="nav_action">
                <a href="/" class="actionBtn">
                    <img src="<c:url value='/resources/assets/icon/login.svg'/>" alt="Login Icon" />
                    <span>Home</span>
                </a>
            </div>
    
        </nav>
    </header>

    <section id="login">
        <div class="login">
    
            <p>Hi, There</p>
            <h2><span>Admin </span>Login</h2>

            <form:form action="login" method="POST" modelAttribute="loginAdmin">
                <form:label for="username" path="username">Username</form:label>
                <form:input type="text" name="username" id="username" class="form-control" path="username" />
                <form:errors path="username" />
                
                <form:label for="password" path="password">Password</form:label>
                <form:input type="password" name="password" id="password" class="form-control" path="password" />
                <form:errors path="password" />
                
                <p class="error">${errorMsg}</p>

                <button type="submit" value="Login" class="btn">Login</button>
            </form:form>
    
            <hr />
    
            <div class="form_action">
                <a href="/reset" class="btn">Forget Password?</a>
                <a href="/register" class="btn">New Here! <span>Register Now</span></a>
                <a href="/login" class="btn">Let <span>User</span> Login Here!</a>
            </div>
    
        </div>
    </section>

</body>

</html>