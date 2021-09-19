<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
	ProductDAOImpl dao = new ProductDAOImpl();
	List<ProductVO> list = dao.getAll().stream().filter(e -> e.getState().equals("正常"))
			.collect(Collectors.toList());
	List<ProductVO> list1 = list.stream().filter(e -> e.getProduct_category_no().equals(1))
			.collect(Collectors.toList());
	List<ProductVO> list2 = list.stream().filter(e -> e.getProduct_category_no().equals(2))
			.collect(Collectors.toList());
	List<ProductVO> list3 = list.stream().filter(e -> e.getProduct_category_no().equals(3))
			.collect(Collectors.toList());

	pageContext.setAttribute("list", list);
	pageContext.setAttribute("list1", list1);
	pageContext.setAttribute("list2", list2);
	pageContext.setAttribute("list3", list3);

	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>商城總覽</title>
<link
	href="https://fonts.googleapis.com/css2?family=Long+Cang&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/Ming.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/style.css">
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
				<li class="layout_product_name" id="handmade">創意手做</li>
			</ul>
		</div>
		<!-- <div class="product_head">商品總覽</div> -->
		<div class="product_list">

			<%--商品總覽 --%>
			<c:forEach var="productVO" items="${list}">
				<div class="product-all">
					<input type="hidden" id="PNO" name="pro_no"
						value="${productVO.product_no}"> <input type="hidden"
						id="PNAME" name="pro_name" value="${productVO.product_name}">

					<div class="product_img">
						<button class="add">加入購物車</button>
						<a
							href="<%=request.getContextPath()%>/SingleProduct?pro_no=${productVO.product_no}"><img
							src="<%=request.getContextPath()%>/PicReader1?product_no=${productVO.product_no}"
							alt=""></a>
					</div>

					<div class="info-box">
						<input type="hidden" name="pro_name"
							value="${productVO.product_name}">
						<div class="title_name">${productVO.product_name}</div>
						<input type="hidden" name="pro_price"
							value="${productVO.product_price}">
						<div class="title_price">NT${productVO.product_price}</div>
					</div>

				</div>
			</c:forEach>
			<%--毛小孩用品--%>
			<c:forEach var="productVO" items="${list1}">
				<div class="pet">
					<div class="product_img">
						<button class="add" id="btnadd">加入購物車</button>
						<a
							href="<%=request.getContextPath()%>/SingleProduct?pro_no=${productVO.product_no}"><img
							src="<%=request.getContextPath()%>/PicReader1?product_no=${productVO.product_no}"
							alt=""></a>
					</div>
					<div class="info-box">
						<input type="hidden" name="pro_name"
							value="${productVO.product_name}">
						<div class="title_name">${productVO.product_name}</div>
						<input type="hidden" name="pro_price"
							value="${productVO.product_price}">
						<div class="title_price">NT${productVO.product_price}</div>
					</div>
				</div>
			</c:forEach>

			<%--咖啡豆--%>

			<c:forEach var="productVO" items="${list2}">
				<div class="coffee">
					<div class="product_img">
						<button class="add" id="btnadd">加入購物車</button>
						<a
							href="<%=request.getContextPath()%>/SingleProduct?pro_no=${productVO.product_no}"><img
							src="<%=request.getContextPath()%>/PicReader1?product_no=${productVO.product_no}"
							alt=""></a>
					</div>
					<div class="info-box">
						<input type="hidden" name="pro_name"
							value="${productVO.product_name}">
						<div class="title_name">${productVO.product_name}</div>
						<input type="hidden" name="pro_price"
							value="${productVO.product_price}">
						<div class="title_price">NT${productVO.product_price}</div>
					</div>
				</div>
			</c:forEach>

			<%--創意手做--%>
			<c:forEach var="productVO" items="${list3}">
				<div class="handmade">
					<div class="product_img">
						<button class="add" id="btnadd">加入購物車</button>
						<a
							href="<%=request.getContextPath()%>/SingleProduct?pro_no=${productVO.product_no}"><img
							src="<%=request.getContextPath()%>/PicReader1?product_no=${productVO.product_no}"
							alt=""></a>
					</div>
					<div class="info-box">
						<input type="hidden" name="pro_name"
							value="${productVO.product_name}">
						<div class="title_name">${productVO.product_name}</div>
						<input type="hidden" name="pro_price"
							value="${productVO.product_price}">
						<div class="title_price">NT${productVO.product_price}</div>
					</div>
				</div>
			</c:forEach>



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

	<%@ include file="/footer.file" %>
	<script type="text/javascript"
		src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	<script src=""></script>
	<script src="<%=request.getContextPath()%>/js/store-js/shop.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/store-js/bootstrap.bundle.min.js"></script>

</body>
</html>