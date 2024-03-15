<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Bookings | Restora</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/bookings.css'/>" />
    
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

    <section id="bookings">
        <div class="bookings">

            <c:if test="${userLoggedIn}">
                <p>Hi, <span>${userSession.fullName}</span>. All your bookings done till now!</p>
            </c:if>
            <c:if test="${adminLoggedIn}">
                <p>Hi, <span>Admin</span>. All bookings done till now!</p>
            </c:if>
            <h2><span>Reservations</span> List</h2>

            <c:choose>
                <c:when test="${deleteSuccess!=null}">
                    <div class="deleteStatus isSuccess">${deleteSuccess}</div>
                </c:when>
                <c:when test="${deleteError!=null}">
                    <div class="deleteStatus isError">${deleteError}</div>
                </c:when>
            </c:choose>

            <c:choose>

                <c:when test="${!isBookingListNA}">
                    <div class="bookings_list">
                        <c:forEach items="${bookings}" var="booking">
                    
                            <div class="booking_card">
                                <div class="booking_customer">
                                    <h3>Booked by customer <span>${booking.customerName}</span></h3>
                                    <p>Contact: <span>${booking.customerEmail} | ${booking.customerPhoneNo}</span></p>
                                </div>
                                <hr />
                                <div class="booking_details">
                                    <div>
                                        <h4>Date: <span>${booking.bookingDate}</span></h4>
                                        <h4>Time: <span>${booking.bookingTimeFrom} - ${booking.bookingTimeTo}</span></h4>
                                    </div>
                                    <h5>Number of Persons: ${booking.noOfPersons}</h5>
                                    <p>${booking.bookingPurpose}</p>
                                </div>
                                <c:if test="${adminLoggedIn}">
                                    <a class="deleteBtn btn" href="/bookings/deleteBooking/${booking.bookingId}">
                                        <i class="bx bxs-trash-alt"></i>
                                    </a>
                                </c:if>
                            </div>
                    
                        </c:forEach>
                    </div>
                </c:when>

                <c:when test="${isBookingListNA}">

                    <c:if test="${userLoggedIn}">
                        <div class="bookings_empty">
                            <h4>You haven't booked any table till now!</h4>
                            <a class="btn" href="/reservation">Book Now</a>
                        </div>
                    </c:if>

                    <c:if test="${adminLoggedIn}">
                        <div class="bookings_empty">
                            <h4>Your customers haven't booked any table till now!</h4>
                            <a class="btn" href="/">Home</a>
                        </div>
                    </c:if>

                </c:when>

            </c:choose>

        </div>
    </section>

</body>

</html>