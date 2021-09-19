<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<!--               暫時用不到                             -->
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商品頁面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/Ming.css">
</head>
<body>
	<header>
		<div class="header-div">
			<div class="header-img">
				<a href=""><img class="logo"
					src="<%=request.getContextPath()%>/img/store-img/APE3-1.svg" alt="" /></a>
			</div>
			<div class="nav">
				<span class="header-singin"><a href="">登入</a>&emsp;|&emsp;<a
					href="">註冊</a></span>
				<ul>
					<li><a class="a1"
						href="<%=request.getContextPath()%>/store/store.jsp">線上商城</a></li>
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
							<button type="submit"
								style="border: unset; background-color: unset;">
								<img id="shoplogo" class="shop"
									src="<%=request.getContextPath()%>/img/store-img/shop.png"
									alt="" />
							</button>
						</form>
						<ul id="car" class="car">
						<%
								TreeMap cart = (TreeMap) session.getAttribute("cart");

								if (cart != null) {
									for (Object cartVO : (Collection)cart.values()) {
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
			<li><a href="">會員中心</a></li>
		</ul>
		</div>
	</header>

	<div class="container">
		<div class=layout_left>

			<ul class="ul1">
				<li class="layout_product_name" id="all">商品總覽</li>
				<li class="layout_product_name" id="pet">寵物用品</li>
				<li class="layout_product_name" id="coffee">咖啡豆</li>
				<li class="layout_product_name" id="handmade">創意手做
			</ul>


		</div>
		<div class="product_content">
			<input type="hidden" id="PNO" name="pro_no"
				value="${productVO.product_no}"> <input type="hidden"
				id="PNAME" name="pro_name" value="${productVO.product_name}">
			<div class="product_only">
				<img
					src="<%=request.getContextPath()%>/PicReader1?product_no=${productVO.product_no}"
					alt="">
			</div>
			<div class="product_detail">
				<form action="SingleProduct" method="post">
					<div class="product_name">${productVO.product_name}</div>
					<div class="product_price">NT${productVO.product_price}</div>
				</form>
				<div class="status">
					狀態
					<div class="status_button">
						<button class="btn btn-outline-primary">現貨</button>
						<button class="btn btn-outline-primar_no">預購(缺貨中...)</button>
					</div>
				</div>
				<div class="count_text">
					數量 <select name="count" id="count" class="count">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>


					</select>
				</div>
				<div class="status_button">
					<button id="car" class="btn btn-outline-primary">加入購物車</button>
					<button id="buy" class="btn btn-outline-primary">
						<a href="/order.html"></a>立即購買
					</button>
				</div>
			</div>
		</div>

	</div>
	<div id="fs_alert">
		<div class="fs_alert_bg"></div>
		<div class="fs_alert_show">
			<div class="fs_alert_title">成功</div>
			<div class="fs_alert_txt">已加入購物車</div>
			<div class="btn_s " id="alert_ok">確認</div>

		</div>
	</div>
	<div id="fs_alert1">
		<div class="fs_alert_bg"></div>
		<div class="fs_alert_show">
			<div class="fs_alert_title">成功</div>
			<div class="fs_alert_txt">已加入追蹤清單</div>
			<div class="btn_s1" id="alert_ok">確認</div>
			`
		</div>
	</div>
	<%@ include file="/footer.file" %>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/store-js/code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/store-js/code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

	
	<script
		src="<%=request.getContextPath()%>/js/store-js/bootstrap.bundle.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/store-js/product.js"></script>

</body>
</html>