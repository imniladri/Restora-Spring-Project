<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <title>Registered Successfully!</title>
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
    <link rel="stylesheet" href="<c:url value='/resources/css/pages/status.css'/>" />
    
    <script defer src="<c:url value='/resources/js/libraries/jquery-3.7.1.min.js'/>"></script>
    <script defer src="<c:url value='/resources/js/main.js'/>"></script>
</head>

<body>

    <% response.setHeader("Cache-Control", "no-cache, no-store" ); %>

    <div id="patternBg"></div>

    <section id="status">
        <div class="status">
            <img src="<c:url value='/resources/assets/img/success.png'/>" alt="Status Image" />
            <h2>
                <c:choose>
                    <c:when test="${successHead!=null}">${successHead}</c:when>
                    <c:otherwise>Success Heading Message</c:otherwise>
                </c:choose>
            </h2>
            <p>
                <c:choose>
                    <c:when test="${successPara!=null}">${successPara}</c:when>
                    <c:otherwise>Success Paragraph Message</c:otherwise>
                </c:choose>
            </p>
            
            <a href="
                <c:choose>
                    <c:when test='${successUrl!=null}'>${successUrl}</c:when>
                    <c:otherwise>/</c:otherwise>
                </c:choose>
            " class="btn">
                <c:choose>
                    <c:when test="${successBtn!=null}">${successBtn}</c:when>
                    <c:otherwise>Success Button Text</c:otherwise>
                </c:choose>
            </a>

        </div>
    </section>

</body>

</html>