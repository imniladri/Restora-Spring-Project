<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Restora: Restaurant</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/components/loader.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/components/header.css'/>" />
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/index.css'/>" />
    
    <script defer src="<c:url value='/resources/js/libraries/jquery-3.7.1.min.js'/>"></script>
    <script defer src="<c:url value='/resources/js/main.js'/>"></script>
</head>

<body>

    <% response.setHeader("Cache-Control", "no-cache, no-store"); %>

    <c:if test="${!pageLoaded}">
        <div id="preloader">
            <div class="loader"></div>
        </div>
    </c:if>

    <div id="patternBg"></div>

    <header>
        <nav class="navbar">
    
            <a href="/" class="nav_brand">
                <img src="<c:url value='/resources/assets/img/logo.svg'/>" alt="Restora Logo" />
                <span>Restora</span>
            </a>
    
            <div class="nav_items">
                <a href="/" class="nav_links">Home</a>
                <a href="javascript:void()" id="openMessage" class="nav_links">Message</a>
                <a href="/menu" class="nav_links">Menu</a>
                <a href="/reservation" class="nav_links">Reservation</a>
                <a href="javascript:void()" id="openContact" class="nav_links">Contact</a>
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
                        <a href="/login" class="actionBtn">
                            <img src="<c:url value='/resources/assets/icon/login.svg'/>" alt="Login Icon" />
                            <span>Login</span>
                        </a>
                    </c:otherwise>
                </c:choose>
    
                <div class="menu_icon menu_squeeze">
                    <span class="menu_inner"></span>
                </div>
            </div>
    
        </nav>
    </header>
    
    <section id="home">
        <div class="home">
            <div class="text_content">
                <h2>
                    We do not 
                    <span class="d-inline-block">
                        c
                        <img src="<c:url value='/resources/assets/img/o1.png'/>" alt="o" />
                        <img src="<c:url value='/resources/assets/img/o2.png'/>" alt="o" />
                        k,
                    </span> 
                    <br /> 
                    We create your emotions!
                </h2>
                <h4>Presented By - Restora</h4>
                <p>
                    Welcome to our restaurant app! We are excited to offer you a seamless dining experience.
                    Here you can easily browse our menu, place orders, and make reservations.
                    Our app is designed to make your dining experience as convenient and enjoyable as
                    possible. Start exploring!
                </p>
    
                <div class="home_btn">
                    <a href="/menu" class="btn">
                        <img src="<c:url value='/resources/assets/icon/menu.svg'/>" alt="Icon" />
                        <span>Our Menu</span>
                    </a>
    
                    <a href="/reservation" class="btn">
                        <img src="<c:url value='/resources/assets/icon/reservation.svg'/>" alt="Icon" />
                        <span>Reservation</span>
                    </a>
                </div>
            </div>
    
            <div class="img_content">
                <img src="<c:url value='/resources/assets/img/2.png'/>" alt="Dish Img 2" />
                <img src="<c:url value='/resources/assets/img/3.png'/>" alt="Dish Img 3" />
                <img src="<c:url value='/resources/assets/img/1.png'/>" alt="Dish Img 1" />
            </div>
        </div>
    </section>
    
    <section id="message">
        <div class="message">
    
            <div class="msg_text">
                <h2>Message From <br /> <span data-text="Developer">Developer</span></h2>
                <p>
                    This is a friendly web application for restaurant with different features.
                    This is built using Java, Spring Boot, MVC, Data JPA, Oracle SQL, Servlet / JSP and HTML/CSS/JS.
                    Currently we have two use cases: User and Admin. Explore yourself around.
                </p>
                <img src="<c:url value='/resources/assets/img/signature.png'/>" alt="Signature" />
            </div>
    
            <div class="msg_img">
                <img src="<c:url value='/resources/assets/img/dev.png'/>" alt="Dev" class="dev_img" />
            </div>

            <div class="closeBtn">
                <i class="bx bx-x"></i>
            </div>
    
        </div>
    </section>
    
    <section id="contact">
        <div class="contact">

            <p>Restora Contact Details</p>
            <h2 data-text="Get In Touch">Get In Touch</h2>

            <div class="details">
                <h4><i class="bx bxs-map"></i> <span>Montreal, 1510 Rue Sauve</span></h4>
                <h4><i class="bx bxs-time"></i> <span>08:00 - 23:00</span></h4>
                <h4><i class="bx bxs-phone"></i> <span>+33-785-550-594</span></h4>
                <h4><i class="bx bxs-envelope"></i> <span>restora@mail.com</span></h4>
            </div>

            <div class="closeBtn">
                <i class="bx bx-x"></i>
            </div>
    
        </div>
    </section>

    <section id="logoutModal">
        <div class="logoutModal">
            <h4>Are you sure you want logout?</h4>
            <p>Your cart items will get deleted if you logout!</p>
            <a href="/logout" class="btn">Logout<i class="bx bx-log-out"></i></a>
            <div class="closeBtn"><i class="bx bx-x"></i></div>
        </div>
    </section>

</body>

</html>