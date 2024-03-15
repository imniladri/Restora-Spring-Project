<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Reservation | Restora</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/reservation.css'/>" />
    
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
                
                <c:choose>
                    
                    <c:when test="${userLoggedIn}">
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
                    </c:when>

                    <c:when test="${adminLoggedIn}">
                        <a href="javascript:void()" class="userBtn">
                            <img src="<c:url value='/resources/assets/icon/user.svg'/>" alt="User Icon" />
                        </a>
                    
                        <div class="userMenu">
                            <h4>Hello, <span>Admin</span></h4>
                            <hr />
                            <a href="/newitem" class="nav_links">Add Menu Items<i class="bx bx-plus-circle"></i></a>
                            <a href="/bookings" class="nav_links">View Bookings<i class="bx bx-calendar-check"></i></a>
                            <hr />
                            <a href="/admin/logout" class="nav_links">Logout<i class="bx bx-log-out"></i></a>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <a href="/" class="actionBtn">
                            <img src="<c:url value='/resources/assets/icon/login.svg'/>" alt="Login Icon" />
                            <span>Home</span>
                        </a>
                    </c:otherwise>

                </c:choose>    
    
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

    <section id="reservation">
        <div class="reservation">

            <h2><span>Reserve</span> Your Table</h2>
            <p>Mmm...! Your evening will be great!</p>

            <form:form action="reservation" method="POST" modelAttribute="booking">

                <h4>Booking Table <span>@${userSession.username}</span></h4>

                <c:if test="${failedMsg!=null}">
                    <div class="failedMsg">${failedMsg}</div>
                </c:if>

                <div class="form_group col-12">
                    <form:label for="customerName" path="customerName" class="form-label">Name <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="customerName" name="customerName" path="customerName" />
                    <form:errors path="customerName" />
                </div>
                
                <div class="form_group col-md-6 col-12">
                    <form:label for="customerEmail" path="customerEmail" class="form-label">Email <i>*</i></form:label>
                    <form:input type="email" class="form-control" id="customerEmail" name="customerEmail" path="customerEmail" />
                    <form:errors path="customerEmail" />
                </div>
                
                <div class="form_group col-md-5 col-12">
                    <form:label for="customerPhoneNo" path="customerPhoneNo" class="form-label">Phone Number <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="customerPhoneNo" name="customerPhoneNo" path="customerPhoneNo" />
                    <form:errors path="customerPhoneNo" />
                </div>
                
                <div class="form_group col-12">
                    <form:label for="bookingDate" path="bookingDate" class="form-label">Reservation Date <i>*</i></form:label>
                    <form:input type="date" class="form-control" id="bookingDate" name="bookingDate" path="bookingDate" />
                    <form:errors path="bookingDate" />
                </div>
                
                <div class="form_group col-md-4 col-12">
                    <form:label for="bookingTimeFrom" path="bookingTimeFrom" class="form-label">Reservation Time From <i>*</i></form:label>
                    <form:input type="time" class="form-control" id="bookingTimeFrom" name="bookingTimeFrom" path="bookingTimeFrom" />
                    <form:errors path="bookingTimeFrom" />
                </div>
                
                <div class="form_group col-md-4 col-12">
                    <form:label for="bookingTimeTo" path="bookingTimeTo" class="form-label">Reservation Time To <i>*</i></form:label>
                    <form:input type="time" class="form-control" id="bookingTimeTo" name="bookingTimeTo" path="bookingTimeTo" />
                    <form:errors path="bookingTimeTo" />
                </div>
                
                <div class="form_group col-md-3 col-12">
                    <form:label for="noOfPersons" path="noOfPersons" class="form-label">No of Persons <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="noOfPersons" name="noOfPersons" path="noOfPersons" />
                    <form:errors path="noOfPersons" />
                </div>
                
                <div class="form_group col-12">
                    <form:label for="bookingPurpose" path="bookingPurpose" class="form-label">Purpose of Reservation <i>*</i></form:label>
                    <form:textarea class="form-control" name="bookingPurpose" id="bookingPurpose" path="bookingPurpose" cols="30" rows="5"></form:textarea>
                    <form:errors path="bookingPurpose" />
                </div>

                <button type="submit" id="submit" class="btn">Confirm Booking</button>

            </form:form>

        </div>
    </section>

</body>

</html>