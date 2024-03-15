<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Profile | Restora</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/dashboard.css'/>" />
    
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
    
            <div class="nav_items">
                <a href="/" class="nav_links">Home</a>
                <a href="/" class="nav_links">Message</a>
                <a href="/menu" class="nav_links">Menu</a>
                <a href="/reservation" class="nav_links">Reservation</a>
                <a href="/" class="nav_links">Contact</a>
            </div>
    
            <div class="nav_action">
    
                <a href="/cart" class="cartBtn">
                    <img src="<c:url value='/resources/assets/icon/cart.svg'/>" alt="Cart Icon" />
                    <span>${cart.cartItems.size() > 0 ? cart.cartItems.size() : 0}</span>
                </a>
                
                <a href="javascript:void()" class="userBtn">
                    <img src="<c:url value='/resources/assets/icon/user.svg'/>" alt="User Icon" />
                </a>
                
                <div class="userMenu">
                    <h4>Hello, <span>${userSession.fullName}</span></h4>
                    <hr />
                    <a href="/dashboard/${userSession.username}" class="nav_links">Dashboard<i class="bx bx-user"></i></a>
                    <a href="/bookings" class="nav_links">View Bookings<i class="bx bx-calendar-event"></i></a>
                    <a href="/orders" class="nav_links">View Orders<i class="bx bx-list-check"></i></a>
                    <hr />
                    <c:choose>
                        <c:when test="${cart.cartItems.size() > 0}">
                            <a href="javascript:void()" id="modalTrigger">Logout<i class="bx bx-log-out"></i></a>
                        </c:when>
                        <c:otherwise>
                            <a href="/logout" class="nav_links">Logout<i class="bx bx-log-out"></i></a>
                        </c:otherwise>
                    </c:choose>
                </div>
    
                <div class="menu_icon menu_squeeze">
                    <span class="menu_inner"></span>
                </div>
            </div>
    
        </nav>
    </header>

    <section id="logoutModal">
        <div class="logoutModal">
            <h4>Are you sure you want logout?</h4>
            <p>Your cart items will get deleted if you logout!</p>
            <a href="/logout" class="btn">Logout<i class="bx bx-log-out"></i></a>
            <div class="closeBtn"><i class="bx bx-x"></i></div>
        </div>
    </section>

    <section id="deleteModal">
        <div class="deleteModal">
            <h4>Are you sure you want delete?</h4>
            <p>Your account will be permanently removed.</p>
            <a href="/dashboard/deleteUser/${userSession.username}" class="btn">Confirm Delete<i class="bx bx-trash"></i></a>
            <div class="closeBtn"><i class="bx bx-x"></i></div>
        </div>
    </section>

    <section id="dashboard">
        <div class="dashboard">

            <p>Hi there, Welcome to your dashboard</p>
            <h2><span>${userSession.fullName}</span></h2>
            <h4><span>@</span> ${userSession.username}</h4>

            <form:form action="updateUser" method="POST">

                <c:if test="${isUserDataInvalid}">
                    <div class="errorMsg">${invalidUserMsg}</div>
                </c:if>
            
                <h3>Basic Details</h3>
                <div class="form_section">

                    <form:hidden path="userId" />
                    <form:hidden path="fullName" />
                    <form:hidden path="username" />
                    <form:hidden path="password" />
            
                    <div class="form_group col-md-6">
                        <form:label for="email" path="email" class="form-label">Email Address</form:label>
                        <form:input type="email" class="form-control" id="email" name="email" path="email" value="${userBean.email}" />
                        <form:errors path="email" />
                    </div>

                    <div class="form_group col-md-5">
                        <form:label for="mobileNo" path="mobileNo" class="form-label">Phone Number</form:label>
                        <form:input type="text" class="form-control" id="mobileNo" name="mobileNo" path="mobileNo" value="${userBean.mobileNo}" />
                        <form:errors path="mobileNo" />
                    </div>
            
                    <div class="form_group col-md-4">
                        <form:label for="dob" path="dob" class="form-label">Date of Birth</form:label>
                        <form:input type="text" class="form-control" id="dob" name="dob" path="dob" value="${dobFormat}" readonly="true" />
                        <form:errors path="dob" />
                    </div>
            
                    <div class="form_group col-md-4 radio">
                        <h6>Gender</h6>
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
                        <form:label for="address1" path="address1" class="form-label">Address Line 1</form:label>
                        <form:input type="text" class="form-control" id="address1" name="address1" path="address1" value="${userBean.address1}" />
                        <form:errors path="address1" />
                    </div>
            
                    <div class="form_group col-12">
                        <form:label for="address2" path="address2" class="form-label">Address Line 2</form:label>
                        <form:input type="text" class="form-control" id="address2" name="address2" path="address2" value="${userBean.address2}" />
                        <form:errors path="address2" />
                    </div>
            
                </div>

                <div class="form_actionBtn">
                    <button type="submit" id="submit" class="btn">Update Profile</button>
                    <a href="javascript:void()" id="confirmDelete" class="deletebtn btn">Delete Profile</a>
                </div>

            </form:form>

        </div>
    </section>

</body>

</html>