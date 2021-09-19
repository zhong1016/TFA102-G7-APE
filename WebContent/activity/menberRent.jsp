<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.rent.model.RentVO"%>
<%@page import="java.util.List"%>
<%@page import="com.rent.model.RentService"%>
<%@page import="com.mem.model.MemVO"%>

<jsp:useBean id="activitySvc" scope="page"
	class="com.activity.model.ActivityService" />
<%
	if (session.getAttribute("memVO") == null) {
		session.setAttribute("location", request.getRequestURI());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/members/Member.jsp");
		dispatcher.forward(request, response);
		return;// 程式中斷
	}

	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Integer userNo = memVO.getUserNo();
	RentService rentSvc = new RentService();
	List<RentVO> rentlist = rentSvc.getMyRent(userNo);
	pageContext.setAttribute("rentlist", rentlist);
	pageContext.setAttribute("userNo", userNo);
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我的攤位</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/header.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" /> --%>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/backend.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/pageCss/backendmenberrent.css">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />

</head>

<body>
	<%@ include file="/header.file"%>

	<!-- 	<div class="warp"> -->
	<!-- 		<button type="button" class="btn" id="goPre"></button> -->
	<!-- 		<button type="button" class="btn" id="goNext"></button> -->
	<!-- 	</div> -->



	<!-- 前台面板 -->
	<div class="body-content">

		<!-- 面板列 -->
		<div class="body_content_list">
			<div class="name">
				<img
					src="<%=request.getContextPath()%>/memPicReader?user_no=${userNo}"
					alt="">
				<p>林杜熙</p>
			</div>

			<!--會員資料 -->
			<div class="body_member_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/people.svg"
					alt=""> <a href="#">會員資料</a>
			</div>
			<!-- 買家專區 -->
			<div class="body_buyer_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/cart.svg"
					alt=""> <a href="#" class="buyer_click">買家專區</a>
			</div>
			<div class="body_buyer_list_all on">
				<ul>
					<li><a href="">訂單查詢</a></li>
					<li><a href="">商品追蹤清單</a></li>
				</ul>
			</div>
			<!--賣家專區  -->
			<div class="body_seller_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/shop.svg"
					alt=""> <a href="#" class="seller_click">賣家專區</a>
			</div>
			<div class="body_seller_list_all on">
				<ul>
					<li><a href="#">訂單查詢</a></li>
					<li><a href="#">商品管理</a></li>
					<li><a href="#">商品上架</a></li>
				</ul>
			</div>

			<!-- 攤位管理 -->
			<div class="body_stall_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/stall.svg"
					alt=""> <a href="#">攤位管理</a>
			</div>
			<!-- 我的票券 -->
			<div class="body_ticket_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/ticket.svg"
					alt=""> <a href="#">我的票券</a>
			</div>


			<!--文章管理 -->
			<div class="body_article_list">
				<img
					src="<%=request.getContextPath()%>/img/backstage-img/events.svg"
					alt=""> <a href="#">文章管理</a>
			</div>


		</div>


		<!-- 面板內容 -->
		<div class="body_content_data">
			<div class="container">
				<h2>我的攤位</h2>
				<ul class="responsive-table">
					<li class="table-header">
						<div class="header header-1">活動名稱</div>
						<div class="header header-2">活動日期</div>
						<div class="header header-3">攤位名稱</div>
						<div class="header header-4">攤位簡介</div>
						<div class="header header-5">攤位圖片</div>
						<div class="header header-6">審核</div>
					</li>
					<c:forEach var="rentVO" items="${rentlist}" varStatus="status">
						<li class="table-row">
							<div class="col col-1" data-label="ACTIVITY_NAME">${activitySvc.getOneActivity(rentVO.activityNo).activityName}</div>
							<div class="col col-2" data-label="ACTIVITY_DATE">
								${activitySvc.getOneActivity(rentVO.activityNo).startDate}
								<p>&nbsp;</p>
								${activitySvc.getOneActivity(rentVO.activityNo).closeDate}
							</div>
							<div class="col col-3" data-label="RENT_NAME">${rentVO.rentName}</div>
							<div class="col col-4" data-label="RENT_INTRODUCTION">${rentVO.rentIntroduction}</div>
							<div class="col col-5" data-label="RENT_PIC">
								<img
									src="<%=request.getContextPath()%>/rent/RentPic?rentNo=${rentVO.rentNo}">
							</div>
							<div class="col col-6" data-label="STATUS">
								<span class="STATUS"> <c:forEach var="rentEntry"
										items="${rentMap}">
										<c:if test="${rentVO.status == rentEntry.key}">${rentEntry.value}</c:if>
									</c:forEach>
								</span>
							</div>
						</li>
					</c:forEach>

				</ul>
			</div>

		</div>

	</div>
	<%@ include file="/footer.file"%>
	<script src="<%=request.getContextPath()%>/js/header.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<%-- 	<script src="<%=request.getContextPath()%>/js/index.js"></script> --%>
	<script src="<%=request.getContextPath()%>/js/backend.js"></script>
</body>

</html>