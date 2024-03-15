<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Add Menu Item | Restora</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/newitem.css'/>" />
    
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
                <c:choose>
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
            </div>
    
        </nav>
    </header>

    <section id="newitem">
        <div class="newitem">

            <h2><span>New Food</span> Item</h2>
            <p>Admin! Add new food item for menu page</p>

            <form:form action="newitem" method="POST" modelAttribute="item">

                <c:if test="${errorMsg!=null}">
                    <div class="errorMsg">${errorMsg}</div>
                </c:if>

                <div class="form_group col-12">
                    <form:label for="foodItemName" path="foodItemName" class="form-label">Food Name <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="foodItemName" name="foodItemName" path="foodItemName" />
                    <form:errors path="foodItemName" />
                </div>

                <div class="form_group col-12">
                    <form:label for="foodItemDesc" path="foodItemDesc" class="form-label">Food Description <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="foodItemDesc" name="foodItemDesc" path="foodItemDesc" />
                    <form:errors path="foodItemDesc" />
                </div>

                <div class="form_group col-12">
                    <form:label for="foodItemImageSrc" path="foodItemImageSrc" class="form-label">Food Image Source (Food Name) <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="foodItemImageSrc" name="foodItemImageSrc" path="foodItemImageSrc" placeholder="/resources/assets/img/items/[type_food_image_name]" />
                    <form:errors path="foodItemImageSrc" />
                </div>

                <div class="form_group col-12">
                    <form:label for="foodItemPrice" path="foodItemPrice" class="form-label">Food Price <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="foodItemPrice" name="foodItemPrice" path="foodItemPrice" />
                    <form:errors path="foodItemPrice" />
                </div>

                <div class="form_group col-12">
                    <form:label for="foodItemCategory" path="foodItemCategory" class="form-label">Food Category (Eg: Vegan/Hot/Drink/Any) <i>*</i></form:label>
                    <form:input type="text" class="form-control" id="foodItemCategory" name="foodItemCategory" path="foodItemCategory" />
                    <form:errors path="foodItemCategory" />
                </div>

                <button type="submit" id="submit" class="btn">Add To Menu</button>

            </form:form>

        </div>
    </section>

</body>

</html>