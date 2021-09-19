<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>線上商城</title>
    <!-- <link rel="stylesheet" href="/css/bootstrap.min.css"> -->
    
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/store-css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/store-css/Ming.css">
</head>
<body>
 	<header>
		<div class="header-div">
			<div class="header-img">
				<a href=""><img class="logo" src="<%=request.getContextPath()%>/img/store-img/APE3-1.svg" alt="" /></a>
			</div>
			<div class="nav">
				<span class="header-singin"><a href="<%=request.getContextPath()%>/members/Member.jsp">登入</a>&emsp;|&emsp;<a
					href="">註冊</a></span>
				<ul>
					<li><a class="a1" href="<%=request.getContextPath()%>/store/store.jsp">線上商城</a></li>
					<li><a class="a1" href="#">展覽活動</a>
						<ul class="menu-ul">
							<li class="a2"><a href="">全年檔期</a></li>
							<li class="a2"><a href="">優惠資訊</a></li>
							<li class="a2"><a href="">報名查詢</a></li>
						</ul></li>
					<li><a class="a1" href="#">地圖導覽</a></li>
					<li><a class="a1" href="#">廠商專區</a>
						<ul class="menu-ul">
							<li class="a2"><a href="">廠商後台</a></li>
							<li class="a2"><a href="">場地租借</a></li>
							<li class="a2"><a href="">活動查詢</a></li>
						</ul></li>
					<li><a class="a1" href="#">討論區</a></li>
					<li><a class="a1" href="#">聯絡客服</a>
						<ul class="menu-ul">
							<li class="a2"><a href="">意見回饋</a></li>
							<li class="a2"><a href="">常見問答</a></li>
							<li class="a2"><a href="">贈品兌換</a></li>
						</ul></li>
					<li><a class="a1" href="#">會員中心</a></li>
					<div style="position: relative" class="a1-shop" href="#">
						<form method="post" action="<%=request.getContextPath()%>/Order">
							<button type="submit" style="border: unset;background-color: unset;"><img id="shoplogo" class="shop" src="<%=request.getContextPath()%>/img/store-img/shop.png" alt="" /></button>
						</form>
						<ul id="car" class="car">
<!-- 							<span class="car_top">商品明細:</span> -->
							<%
								TreeMap cart = (TreeMap) session.getAttribute("cart");

								if (cart != null) {
									for (Object cartVO : cart.values()) {
							%>
								<li><%=cartVO%></li>
							<%
								}
								}
							%>
						</ul>
						
					</div>
				</ul>
			</div>
			</ul>
		</div>
		<input type="checkbox" name="menu-switcher" id="menu-switcher" /> <label
			for="menu-switcher" class="hamburger">
			<div class="hamburger-line"></div>
		</label>
		<ul class="menu">
			<li><a href="">線上商城</a></li>
			<li><a href="">展覽活動</a></li>
			<li><a href="">地圖導覽</a></li>
			<li><a href="">廠商專區</a></li>
			<li><a href="">討論區</a></li>
			<li><a href="">聯絡客服</a></li>
			<li><a href="<%=request.getContextPath()%>/store/buyer_manage.jsp">會員中心</a></li>
		</ul>
		</div>
	</header>
    <!-- ------------------------左欄 活動總覽--------------------------------------- -->
    <div class="container">
        <div class="down">
            <div class="aside">
                <h1 class="aside-title">活動總覽</h1>
                <ul class="aside-list">
                    <li class="aside-item">
                        <a href="">
                            <img class="activity_img" src="<%=request.getContextPath()%>/img/store-img/ac1.jpg">
                        </a>
                    </li>
                    <li class="aside-item">
                        <a href="">
                            <img class="activity_img" src="<%=request.getContextPath()%>/img/store-img/ac2.jpg">
                        </a>
                    </li>
                    <li class="aside-item">
                        <a href="">
                            <img class="activity_img" src="<%=request.getContextPath()%>/img/store-img/ac3.jpg">
                        </a>
                    </li>
                    <li class="aside-item">
                        <a href="">
                            <img class="activity_img" src="<%=request.getContextPath()%>/img/store-img/ac4.jpg">
                        </a>
                    </li>
                </ul>
            </div>
            <!-- ------------------------中上 熱門商品--------------------------------------- -->
            <div class="main">
               <%--  <div>
                   <ul class=menu2>
                        <li class="a3"><a href="/shop_all.html">商品總覽</a></li>
                        <li class="a3"><a href="/shop_pet.html">毛小孩</a></li>
                        <li class="a3"><a href="/shop_coffee.html">咖啡廣場</a></li>
                        <li class="a3"><a href="/shop_handmade.html">創意手做</a></li>
                    </ul>
                </div> --%>
                <h2 class="main-title">商品總覽</h2>
                <ul class="slider-list">
                    <li class="slider-item">
                        <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                            <img class="slider-banner" src="<%=request.getContextPath()%>/img/store-img/handmade/2-3.jpg">
                        </a>
                    </li>
                    <li class="slider-item">
                        <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                            <img class="slider-banner" src="<%=request.getContextPath()%>/img/store-img/pet/3-7.jpg">
                        </a>
                    </li>
                    <li class="slider-item">
                        <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                            <img class="slider-banner" src="<%=request.getContextPath()%>/img/store-img/coffee/1-1.jpg">
                        </a>
                    </li>
                </ul>
                <ul class=pointlist>

                </ul>
                <!-- ------------------------左下 新品上市--------------------------------------- -->
                <div class="main-title-left">新品上市</div>
                <div class="box box1">
                    <ul class="left_down-item">
                        <li class="down-item">
                            <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                                <img class="down-banner" id="banner" src="<%=request.getContextPath()%>/img/store-img/pet/3-9.jpg">
                            </a>
                        </li>
                        <li class="down-item">
                            <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                                <img class="down-banner" id="banner" src="<%=request.getContextPath()%>/img/store-img/pet/3-6.jpg">
                            </a>
                        </li>
                        <li class="down-item">
                            <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                                <img class="down-banner" id="banner" src="<%=request.getContextPath()%>/img/store-img/pet/3-4.jpg">
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- ------------------------右下 促銷商品--------------------------------------- -->
                <!-- <div class="main-title-right">促銷商品</div> -->
                <div class="box box2">
                    <ul class="right_down-item">
                        <li class="down-item">
                            <a href="<%=request.getContextPath()%>/store/store_all.jsp">
                                <img class="down-banner" id="banner" src="<%=request.getContextPath()%>/img/store-img/handmade/2-6.jpg">
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </div>
    
    
    <%@ include file="/footer.file" %>
    
<!--     <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->
<!--     <script type="text/javascript" src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script> -->
   
    <script src="<%=request.getContextPath()%>/js/store-js/bootstrap.bundle.min.js"></script>
     <script src="<%=request.getContextPath()%>/js/store-js/js.js"></script>
  
</body>
</html>