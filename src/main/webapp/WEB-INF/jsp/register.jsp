<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Register | Restora</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/register.css'/>" />
    
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
                <a href="/login" class="actionBtn">
                    <img src="<c:url value='/resources/assets/icon/login.svg'/>" alt="Login Icon" />
                    <span>Login</span>
                </a>
            </div>
    
        </nav>
    </header>
    
    <section id="register">
        <div class="register">

            <h2><span>Register</span> yourself</h2>
            <p>Please proceed with self-registration</p>

            <c:if test="${errorMsg!=null}">
                <div class="errorStatus">${errorMsg}</div>
            </c:if>

            <form:form action="register" method="POST" modelAttribute="user">

                <h3>Basic Details</h3>
                <div class="form_section">

                    <div class="form_group col-12">
                        <form:label for="fullName" path="fullName" class="form-label">Full Name <i>*</i></form:label>
                        <form:input type="text" class="form-control" id="fullName" name="fullName" path="fullName" />
                        <form:errors path="fullName" />
                    </div>

                    <div class="form_group col-md-6">
                        <form:label for="email" path="email" class="form-label">Email Address <i>*</i></form:label>
                        <form:input type="email" class="form-control" id="email" name="email" path="email" />
                        <form:errors path="email" />
                    </div>
                    
                    <div class="form_group col-md-5">
                        <form:label for="mobileNo" path="mobileNo" class="form-label">Phone Number <i>*</i></form:label>
                        <form:input type="text" class="form-control" id="mobileNo" name="mobileNo" path="mobileNo" />
                        <form:errors path="mobileNo" />
                    </div>
                    
                    <div class="form_group col-md-4">
                        <form:label for="dob" path="dob" class="form-label">Date of Birth <i>*</i></form:label>
                        <form:input type="date" class="form-control" id="dob" name="dob" path="dob" />
                        <form:errors path="dob" />
                    </div>
                    
                    <div class="form_group col-md-4 radio">
                        <h6>Gender <i>*</i></h6>
                        <div class="form-check">
                            <form:radiobutton class="form-check-input" name="gender" id="genderMale" value="M" path="gender" />
                            <form:label class="form-check-label" for="male" path="gender">Male</form:label>
                        </div>
                        <div class="form-check">
                            <form:radiobutton class="form-check-input" name="gender" id="genderFemale" value="F" path="gender" />
                            <form:label class="form-check-label" for="female" path="gender">Female</form:label>
                        </div>
                        <form:errors path="gender" />
                    </div>

                </div>

                <h3>Address Details</h3>
                <div class="form_section">

                    <div class="form_group col-12">
                        <form:label for="address1" path="address1" class="form-label">Address Line 1 <i>*</i></form:label>
                        <form:input type="text" class="form-control" id="address1" name="address1" path="address1" />
                        <form:errors path="address1" />
                    </div>

                    <div class="form_group col-12">
                        <form:label for="address2" path="address2" class="form-label">Address Line 2</form:label>
                        <form:input type="text" class="form-control" id="address2" name="address2" path="address2" />
                        <form:errors path="address2" />
                    </div>

                </div>

                <h3>Authentication Details</h3>
                <div class="form_section">

                    <div class="form_group col-md-5">
                        <form:label for="username" path="username" class="form-label">Create a New Username <i>*</i></form:label>
                        <form:input type="text" class="form-control" id="username" name="username" path="username" />
                        <form:errors path="username" />
                    </div>
                    
                    <div class="form_group col-md-5">
                        <form:label for="password" path="password" class="form-label">Create a New Password <i>*</i></form:label>
                        <form:input type="password" class="form-control" id="password" name="password" path="password" />
                        <form:errors path="password" />
                    </div>

                </div>

                <button type="submit" id="submit" class="btn">Register Now</button>

            </form:form>

        </div>
    </section>

</body>

</html>