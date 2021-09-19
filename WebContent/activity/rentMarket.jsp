<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.MemVO"%>




<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>rentMarket</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/header.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/activity-css/slick.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/activity-css/slick-theme.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">

<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/activity-js/slick.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/pageCss/rentMarket.css">


</head>

<body>
	<%@ include file="/header.file"%>




	<div class="div_bg">
		<img src="<%=request.getContextPath()%>/img/activity-img/banner1.png"
			class="bg_1" alt="">
	</div>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<div
			style="position: relative; left: 77px; padding: 0 30px; width: 400px; background-color: rgba(255, 255, 255, 0.3); border-radius: 50px; bottom: 30px;">
			<font style="color: red;">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red;">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<div class="bg_range">
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/rent/RentServlet.do"
			enctype="multipart/form-data">
			<div class="create_data">
				<div class="create_infor">
					<div>
						活動名稱： <span>${activityVO.activityName}</span>
					</div>
					<div>
						開始日期： <span>${activityVO.startDate}</span>
					</div>
					<div>
						結束日期： <span>${activityVO.closeDate}</span>
					</div>
					<div>
						活動天數： <span id="dayCount">${activityVO.dayCount}</span>
					</div>
					<div>
						攤位價格： <span id="rentPrice">${activityVO.price}</span> 元／天
					</div>
					<div>
						活動地址： <span>${activityVO.address}</span>
					</div>
					<div class="act_pic">
						<img
							src="<%=request.getContextPath()%>/activity/ActivityPic?activityNo=${activityVO.activityNo}">
					</div>
					<div>
						<div>活動簡介：</div>
						<div class="INTRODUCTION">${activityVO.introduction}</div>
					</div>

					<br> <br>
					<div style="font-size: 20px; margin-bottom: 30px;">請填寫以下資料</div>
					<div>
						攤位名稱： <input type="text" name="rentName"
							value="${rentVO.rentName}" class="rentName">
					</div>
					<div>
						攤位簡介：
						<textarea placeholder="請填寫攤位簡介..." name="rentIntroduction"
							value="${rentVO.rentIntroduction}" class="rentIntroduction"></textarea>
					</div>
					<div id="auto_input">填入</div>
				</div>
				<div class="create_pic">
					<div class="preview" id="preview">
						<span class="pic_text">預覽圖</span>
					</div>
					<input type="file" accept="image/*" class="button" id="p_file"
						name="rentPic">

				</div>
			</div>
			<div class="greenleaf">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/greenleaf_long.png"
					alt="">
			</div>
			<div class="rent_range">
				<div id="seat-map"></div>
				<!-- 模擬資料庫 -->
				<!-- activity抓資料 -->
				<input type="hidden" id="rent_available"
					value="${activityVO.rentString}"> <input type="hidden"
					id="rent_rows" value="${activityVO.rowNum}"> <input
					type="hidden" id="rent_columns" value="${activityVO.columnsNum}">
				<!-- 活動抓不可訂位 -->
				<input type="hidden" id="reservation"
					value="${activityVO.reservationAll}"> <input type="hidden"
					name="activityNo" value="${activityVO.activityNo}">


			</div>
			<input type="hidden" name="booking" id="selRentArr"> <span
				class="reserve_rent"> 選位：
				<ul id="selected-rent"></ul>
			</span> <span class="totalPrice"> 合計： <span id="totalPrice"></span> <input
				type="hidden" name="rentPrice" value="" class="rentPrice">

			</span>
			<div>
				<button class="button1" id="button1">送出申請</button>
				<input type="hidden" name="action" value="insert"> <input
					type="hidden" name="userNo" value="${userNo}">
			</div>
			<div class="note">*提出申請後請至專區卻確認審查狀態</div>
		</FORM>
	</div>
	<div class="flower_pic">
		<img src="<%=request.getContextPath()%>/img/activity-img/flower9.png"
			alt="flower">
	</div>
	<div class="block2"></div>
	<nav id="pagetop" class="block_pagetop">
		<a href="#"> top <span></span>
		</a>
	</nav>



	<%@ include file="/footer.file"%>





	<!-- <script src="js/bootstrap.bundle.min.js"></script> -->
	<script
		src="<%=request.getContextPath()%>/js/activity-js/sweetalert.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/activity-js/jquery.seat-charts.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/header.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/activity-js/rentMarket.js"></script>
	<script>
		$("#auto_input").on("click" , function(){
           $(".rentName").val("手工針織小物");
           $(".rentIntroduction").val("每一針、每一線，編織出的作品都包含著我們的用心。");

	     });
	</script>
</body>

</html>