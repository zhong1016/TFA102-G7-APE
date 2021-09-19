<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>activityIndex</title>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/header.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick-theme.css" />
    <link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/activity-js/slick.min.js"></script>

	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/pageCss/activityIndex.css" />

   
</head>

<body>
    <%@ include file="/header.file" %>

    <div class="div_bg">
        <img src="<%=request.getContextPath()%>/img/activity-img/ranbow.png" class="bg_1" alt="">
        <div class="bg_range"></div>
        <div class="left">
            <div class="single-item">
                <div><img src="<%=request.getContextPath()%>/img/activity-img/rent1.png" alt=""></div>
                <div><img src="<%=request.getContextPath()%>/img/activity-img/rent2.png" alt=""></div>
                <div><img src="<%=request.getContextPath()%>/img/activity-img/rent3.png" alt=""></div>
                <div><img src="<%=request.getContextPath()%>/img/activity-img/rent4.png" alt=""></div>
                <div><img src="<%=request.getContextPath()%>/img/activity-img/rent5.png" alt=""></div>
            </div>
        </div>
        <div class="right">
            <div class="information">
                <div class="act">場地租借</div>
                <div><img src="<%=request.getContextPath()%>/img/activity-img/greenleaf.png" class="Sidebar" alt=""></div>
                <div class="text">
                  「創意，是把艱深的藝術作品，換穿一套平民化的外包裝後，便可以輕易進入一般人的生活，成為一種令人感到舒服、愉快的存在；而創意市集，即是一場生活藝術的展演。」
                </div>
            </div>
            <div class="buttongroup">
                <button class="button1">廠商專區</button>
                <button class="button2">攤商專區</button>
            </div>
        </div>
        <nav id="pagetop" class="block_pagetop">
            <a href="#">top<span></span>
            </a>
        </nav>
    </div>





	<%@ include file="/footer.file" %>


    <script src="<%=request.getContextPath()%>/js/header.js"></script>
    <script>
        $(function () {
            $('.single-item').slick({
                dots: true
            });

            $(".bg_1").fadeTo(3000, 0.5);


            $("#pagetop").addClass("hide");
            $("#pagetop").click(function (e) {
                e.preventDefault();
                $("html,body").animate(
                    {
                        scrollTop: 0,
                    },
                    600
                );
            });
            $(window).scroll(function () {
                if ($(window).scrollTop() > 300) {
                    if ($("#pagetop").hasClass("hide")) {
                        $("#pagetop").toggleClass("hide");
                    }
                } else {
                    $("#pagetop").addClass("hide");
                }
            });
			
            $(".button1").on("click", function (){
            	window.location.href ='vendorIndex.jsp';
            });
            
            $(".button2").on("click", function (){
            	window.location.href ='rentIndex.jsp';
            });
        });

    </script>

</body>

</html>