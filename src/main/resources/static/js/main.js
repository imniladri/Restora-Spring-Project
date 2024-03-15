$(document).ready(function () {
    $(window).on("load", function () {
        $("#preloader").addClass("inactive");
    });

    $(".menu_icon").click(function () {
        $(".menu_squeeze").toggleClass("menu_active");
        $("nav .nav_items").toggleClass("active");
    });

    $(".userBtn").click(function () {
        $(".userMenu").toggleClass("active");
    });

    $("#openMessage").click(function () {
        $("#message").addClass("active");
    });

    $("#openContact").click(function () {
        $("#contact").addClass("active");
    });

    $("#modalTrigger").click(function () {
        $("#logoutModal").addClass("active");
    });

    $("#confirmDelete").click(function () {
        $("#deleteModal").addClass("active");
    });

    $(".closeBtn").click(function () {
        $("#message").removeClass("active");
        $("#contact").removeClass("active");
        $("#logoutModal").removeClass("active");
        $("#deleteModal").removeClass("active");
    });

    $(".order_card .btn").click(function () {
        $(this).next().slideToggle(200);
    });

    setTimeout(() => {
        $("#toastSuccess").addClass("inactive");
    }, 500);
});
