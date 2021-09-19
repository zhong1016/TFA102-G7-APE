<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>

<%
	


	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>商品上架</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/index.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/Ming.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/backend.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/product_insert.css">
</head>



<body>
	<header>
		<div class="header-div">
			<div class="header-img">
				<a href=""><img class="logo" src="<%=request.getContextPath()%>/img/store-img/APE3-1.svg" alt="" /></a>
			</div>
			<div class="nav">
				<span class="header-singin"><a href="">登入</a>&emsp;|&emsp;<a
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


	<div class="warp">
		<button type="button" class="btn" id="goPre"></button>
		<button type="button" class="btn" id="goNext"></button>
	</div>



	<!-- 前台面板 -->
	<div class="body-content">

		<!-- 面板列 -->
		<div class="body_content_list">
			<div class="name">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/tony.jfif" alt="">
				<p>金城武</p>
			</div>

			<!--會員資料 -->
			<div class="body_member_list">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/people.svg" alt="">
				<a href="#">會員資料</a>
			</div>
			<!-- 買家專區 -->
			<div class="body_buyer_list">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/cart.svg" alt="">
				<a href="#" class="buyer_click">買家專區</a>
			</div>
			<div class="body_buyer_list_all on">
				<ul>
					<li><a href="<%=request.getContextPath()%>/store/buyer_manage.jsp">訂單查詢</a></li>
					
				</ul>
			</div>
			<!--賣家專區  -->
			<div class="body_seller_list">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/shop.svg" alt="">
				<a href="#" class="seller_click">賣家專區</a>
			</div>
			<div class="body_seller_list_all on">
				<ul>
					<li><a href="<%=request.getContextPath()%>/store/seller_manage.jsp">訂單查詢</a></li>
					<li><a href="<%=request.getContextPath()%>/store/product_manage.jsp">商品管理</a></li>
					<li><a href="<%=request.getContextPath()%>/store/product_add.jsp">商品上架</a></li>
				</ul>
			</div>

			<!-- 攤位管理 -->
			<div class="body_stall_list">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/stall.svg" alt="">
				<a href="#">攤位管理</a>
			</div>
			<!-- 我的票券 -->
			<div class="body_ticket_list">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/ticket.svg" alt="">
				<a href="#">我的票券</a>
			</div>


			<!--文章管理 -->
			<div class="body_article_list">
				<img src="<%=request.getContextPath()%>/img/store-img/backimg/list.svg" alt="">
				<a href="#">文章管理</a>
			</div>


		</div>

		<!-- 面板內容 -->




		<div class="body_content_data">
			<div class="body_content_data_member">
				<p>商品上架</p>
			</div>



			<div class="product_manager">

				<div class="product_pic">
					<input type="file" class="file"><img src="" alt="請新增圖片">

				</div>


				<div class="product_name">
					商品名稱 <input type="text" class="inputtext" name="action">

				</div>


				<div class="product_price">

					價格: <input type="number" class="input_price" name="action">





					<div class="product_count">
						庫存數量: <input type="number" class="input_count" name="action">
					</div>

				</div>



				<div class="product_gory">
					商品類別: <select name="category" id="category" class="category"
						name="category">
						<option value="1">寵物用品</option>
						<option value="2">咖啡豆</option>
						<option value="3">創意手做</option>

					</select>
				</div>


				<div class="button_div">
					<input type="hidden" name="add"> <input type="submit"
						class="button" id="btn_submit" value="上架"></input>

				</div>

			</div>


		</div>


	</div>

	<%--c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if> --%>

<%@ include file="/footer.file" %>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/store-js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/store-js/backend.js"></script>
	<script src="<%=request.getContextPath()%>/js/store-js/product_add.js"></script>
</body>
</html>