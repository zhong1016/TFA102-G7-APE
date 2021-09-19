<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.mem.model.*"%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>訂單</title>
<!-- <link rel="stylesheet" href="/css/bootstrap.min.css"> -->

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/store-css/Ming.css">
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
	
	<div class="container">

		<div class="step">
			<div class="step_block">

				<span class="step_num_OK">1</span> <span class="step_text">確認商品</span>

			</div>
			<div class="step_block">
				<span class="step_num">2</span> <span class="step_text">填寫資料</span>
			</div>
			<div class="step_block">
				<span class="step_num">3</span> <span class="step_text">結帳</span>
			</div>

		</div>

		<div class="order-content">
			<div class=order-title>


				<table class="order">
					<tr class="order_title">
						<td>商品名稱</td>
						<td>數量</td>
						<td>單價</td>
						<td>總金額</td>

					</tr>


					<c:forEach var="productVO" items="${productList}" varStatus="loop">

						<tr class="order_content">
							<input type="hidden" name="index" value="${loop.index}" />
							<input type="hidden" name="product" value="checkout">
							<td>${productVO.product_name}</td>
							<input type="hidden" name="pro_name"
								value="${productVO.product_name}">
							<td><select name="order_count" id="" class="order-count">
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
							</select></td>


							<td class="priceForOne">${productVO.product_price}</td>
							<td class="priceForTotal">${productVO.product_price}</td>
							<td><button class="delete-button">刪除</button></td>

						</tr>

					</c:forEach>
				</table>


				<div class="totalspan">

					<span class="checkoutspan">結帳總金額:</span>
					<button class="checkout">結帳</button>
				</div>
				<%-- /form>--%>
				<div class="member">
					<span class=member_span>訂購人資料</span>
					<ul class="member_content">
					<input type="hidden" value="${memVO.userNo}"></input>
						<li>訂購人:<input class="a5" type="text" value="${memVO.userlName}" id="name"></input></li>
						<li>手機 :<input class="a5" type="text" value="${memVO.userPhone}"
							id="phone"></input></li>
						<li>地址 :<input class="a5" type="text" value="${memVO.userAddress}"
							id="address"></input></li>
						<div>
							<button class="checkbutton">確認</button>
						</div>
					</ul>
				</div>

				<span class="simple"><img src="/img/b_simple_114_0L.jpg"
					alt=""></span>
				<div class="pay">
					<ul>
						<li class="member_span">付款方式</li>
						<br>
					</ul>
					<input type="radio" class="radio" name="pay" value="credit" checked>
					<label for="r1" class="label_text">信用卡 </label><br> <br>

					<div class="group">
						<label>信用卡卡號：</label> <input type="text" class="card"
							maxlength="4"> <input type="text" class="card"
							maxlength="4"> <input type="text" class="card"
							maxlength="4"> <input type="text" class="card"
							maxlength="4">
					</div>
					<div>

						<button class="OK">確認</button>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@ include file="/footer.file" %>
<!-- 	<script type="text/javascript" -->
<%-- 		src="<%=request.getContextPath()%>/js/store-js/code.jquery.com/jquery-1.11.0.min.js"></script> --%>
<!-- 	<script type="text/javascript" -->
<%-- 		src="<%=request.getContextPath()%>/js/store-js/code.jquery.com/jquery-migrate-1.2.1.min.js"></script> --%>
<!-- 	<script -->
<!-- 		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>	 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="<%=request.getContextPath()%>/js/store-js/order.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/store-js/bootstrap.bundle.min.js"></script>
	<script>
	$(function() {
		//結帳總金額
		var total = 0;
		$(".priceForTotal").each(function() {
			total += parseInt($(this).text())
		});
		$('.checkoutspan').text("結帳總金額: " + total);

		$("select").each(
				function() {
					$(this).on(
							'change',
							function() {
								//數量
								let num = $(this).val();

								//價格
								let priceForOne = $(this).closest("td")
										.nextAll(".priceForOne").text();

								//該商品總金額
								let priceForTotal = $(this).closest("td")
										.nextAll(".priceForTotal");
								priceForTotal.text(num * priceForOne);

								//結帳總金額
								var total = 0;
								$(".priceForTotal").each(function() {
									total += parseInt($(this).text())
								});
								$('.checkoutspan').text("結帳總金額: " + total);
							})

				})

	})
<!-- 	</script> -->
	<script type="text/javascript">
		$("input.card").on("keydown", function(e) {
			//console.log(e.which);
			if ((e.which >= 48 && e.which <= 57) || e.which == 8) {

				//console.log(e.target.value.length);
				if (e.target.value.length == 0 && e.which == 8) {
					$(this).prev().focus();
				}
			} else {
				e.preventDefault();
			}
		});
		$("input.card").on("keydown", function(e) {
			//console.log(e.which);
			if ((e.which >= 48 && e.which <= 57) || e.which == 8) {

				//console.log(e.target.value.length);
				if (e.target.value.length == 0 && e.which == 8) {
					$(this).prev().focus();
				}
			} else {
				e.preventDefault();
			}
		});

		$("input.card").on("keyup", function(e) {

			// \D 代表非數字字元，g 代表全域尋找
			//let str = e.target.value;
			//console.log(e.target.value);
			let str = (e.target.value).replace(/\D/g, "");

			$(this).val(str);

			//console.log(str.length);
			if (str.length == 4) {
				$(this).next().focus();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	</script>

</body>
</html>