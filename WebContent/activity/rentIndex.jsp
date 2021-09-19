<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.activity.model.ActivityVO"%>


<jsp:useBean id="activitySvc" scope="page" class="com.activity.model.ActivityService" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>rentIndex</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/header.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick-theme.css" />
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">

<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/activity-js/slick.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/pageCss/rentIndex.css">

</head>

<body>
	<%@ include file="/header.file"%>

	<div class="div_bg">

		<div class="div_top">
			<img src="<%=request.getContextPath()%>/img/activity-img/banner3.png" alt="">
		</div>
	</div>
	<div class="bg_range">
		<div class="div_button1">
			<div class="container-3">
				<span class="icon">
					<i class="fa fa-search"></i>
				</span>
				<input type="search" id="search" placeholder="Search..." />
			</div>
			<!-- <button type="button" class="button1">追蹤清單</button> -->
<!-- 			<div class="checklist"> -->
<!-- 				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">追蹤清單</button> -->
<!-- 			</div> -->

<!-- 			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 				<div class="modal-dialog"> -->
<!-- 					<div class="modal-content"> -->
<!-- 						<div class="modal-header"> -->
<!-- 							<h5 class="modal-title" id="exampleModalLabel">追蹤清單</h5> -->
<!-- 							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
<!-- 						</div> -->
<!-- 						<div class="modal-body"> -->
<!-- 							<h5>追蹤的第一個</h5> -->
<!-- 							<p>小農市集是一個提供小農們自產自銷的平台。</p> -->
<!-- 							<hr> -->
<!-- 							<h5>追蹤的第二個</h5> -->
<!-- 							<p>由老式連排宿舍改造而成，在活化空間及保存記憶規劃下，融合著現代藝術的創作。</p> -->
<!-- 							<hr> -->
<!-- 							想要增加連結到指定的活動 -->

<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->




		</div>
	</div>
	
	
	<div>
		<img src="<%=request.getContextPath()%>/img/activity-img/greenleaf_long.png" class="Sidebar1" alt="">
	</div>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
	<div style="position: relative;left: 117px;padding: 0 30px;width: 400px;background-color: rgba(255, 255, 255, 0.3);border-radius: 50px;">
		<font  style="color: red;">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red;">${message}</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>
	<div class="card_group">

		<div class="container custom_container">

			<div class="row">
				<div class="col-12">
					<ul class="row" id="myUL">

						<c:forEach var="activityVO" items="${activitySvc.getActivityByStatus('a1')}" varStatus="status">

								<li class="col-md-4">
									<div class="card">
										<img src="<%=request.getContextPath()%>/activity/ActivityPic?activityNo=${activityVO.activityNo}" class="card-img-top">
										<div class="card-body">

											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/rent/RentServlet.do">
												<input type="hidden" name="activityNo" value="${activityVO.activityNo}">
												<input type="hidden" name="action" value="getOne_For_Display">
												<input type="hidden" name="location" value="<%=request.getRequestURI() %>" />
												<button type="submit" class="btn btn-primary">start a business</button>
											</FORM>
											<h5 class="card-title">${activityVO.activityName}</h5>
											<a>
												<p class="card-text">${activityVO.introduction}</p>
											</a>
										</div>
									
	</div>
	</li>

	</c:forEach>

	<!--                         
						<!--                         <li class="col-md-4"> -->
	<!--                             <div class="card"> -->
	<%--                                 <img src="<%=request.getContextPath()%>/img\rent9.jpg" class="card-img-top" alt="..."> --%>
	<!--                                 <div class="card-body"> -->
	<!--                                     <div class="btn btn-primary">start a business</div> -->
	<!--                                     <h5 class="card-title">活動介紹</h5> -->
	<!--                                     <a> -->
	<!--                                         <p class="card-text"> -->
	<!--                                             	精選台灣話題性、創意滿點的人氣店家，透過市集活動讓饕客們一次就能把人氣美食吃到位，對國內外遊客推廣在地美食文化與質感生活的品味，不想踩雷、還是口袋清單有的，不用東奔西跑，來美食市集絕對讓你吃好吃滿，不飽不歸！ -->
	<!--                                         </p> -->
	<!--                                     </a> -->
	<!--                                 </div> -->
	<!--                             </div> -->
	<!--                         </li> -->


	</ul>
	</div>
	</div>
	</div>
	</div>
	<div class="flower_pic">
		<img src="<%=request.getContextPath()%>/img/activity-img/flower2.png" alt="flower">
	</div>
	<div class="block2"></div>

	<nav id="pagetop" class="block_pagetop">
		<a href="#">
			top
			<span></span>
		</a>
	</nav>



	<%@ include file="/footer.file"%>

	<script src="<%=request.getContextPath()%>/js/activity-js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/header.js"></script>
	<script>
		$(function() {
			$('.single-item').slick({
				dots : true
			});

			$("#pagetop").addClass("hide");
			$("#pagetop").click(function(e) {
				e.preventDefault();
				$("html,body").animate({
					scrollTop : 0,
				}, 100);
			});
			$(window).scroll(function() {
				if ($(window).scrollTop() > 300) {
					if ($("#pagetop").hasClass("hide")) {
						$("#pagetop").toggleClass("hide");
					}
				} else {
					$("#pagetop").addClass("hide");
				}
			});

			$("#search").on("change", function() {
				var input, filter, ul, li, a, i, txtValue;
				input = document.getElementById('search');
				filter = input.value.toUpperCase();
				ul = document.getElementById("myUL");
				li = ul.getElementsByTagName('li');

				// Loop through all list items, and hide those who don't match the search query
				for (i = 0; i < li.length; i++) {
					a = li[i].getElementsByTagName("a")[0];
					txtValue = a.textContent || a.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						li[i].style.display = "";
					} else {
						li[i].style.display = "none";
					}
				}
			});

		});
	</script>
</body>

</html>