<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.* "%>
<%@ page import="com.activity.model.ActivityService"%>
<%@ page import="com.activity.model.ActivityVO"%>
<%@ page import="com.cop.model.CopVO"%>

<%
	if (session.getAttribute("copVO") == null) {
		session.setAttribute("location", request.getRequestURI());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/members/Member.jsp");
		dispatcher.forward(request, response);
		return;
	}

	CopVO copVO = (CopVO) session.getAttribute("copVO");
	Integer companyNo = copVO.getCompanyNo();

	ActivityService activitySvc = new ActivityService();
	List<ActivityVO> activitylist = activitySvc.getMyActivity(companyNo);
	pageContext.setAttribute("activitylist", activitylist);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>vendorIndex</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/header.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/activity-css/slick.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/activity-css/slick-theme.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">

<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/activity-js/slick.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/pageCss/vendorIndex.css">


</head>

<body>
	<%@ include file="/header.file"%>


	<div class="div_bg">

		<div class="div_top">
			<img src="<%=request.getContextPath()%>/img/activity-img/div_top.png"
				alt="">
		</div>
	</div>
	<div class="bg_range">
		<div class="div_button1">
			<a style="text-decoration: none;">
				<button class="button1 createAct" id="create">創建新活動</button>
			</a>
			<div class="container-3">
				<span class="icon"> <i class="fa fa-search"></i>
				</span> <input type="search" id="search" placeholder="Search..." />
			</div>
		</div>
		<div>
			<img
				src="<%=request.getContextPath()%>/img/activity-img/greenleaf_long.png"
				class="Sidebar1" alt="">
		</div>
		<div class="div_group">
			<div class="container" id="myUL">
				<c:forEach var="activityVO" items="${activitylist}">
					<li class="card mb-3 act_list">
						<div class="row g-0">
							<div class="col-md-4">
								<img
									src="<%=request.getContextPath()%>/activity/ActivityPic?activityNo=${activityVO.activityNo}"
									class="img-fluid rounded-start" alt="...">
							</div>
							<div class="col-md-4">
								<div class="card-body">
									<h6 class="card-title">
										活動名稱 <a class="title">${activityVO.activityName}</a>
									</h6>
									<div class="card-text">
										<div class="card-deco">
											開始日期： <span>${activityVO.startDate}</span>
										</div>
										<div class="card-deco">
											結束日期： <span>${activityVO.closeDate}</span>
										</div>
										<div class="card-deco">
											活動時間： <span>${activityVO.activityHours}</span>
										</div>
										<div class="card-deco">
											攤位數量： <span>${activityVO.rentCount}</span>
										</div>
										<div class="card-deco">
											創建日期： <span><fmt:formatDate type="both"
													dateStyle="medium" timeStyle="medium"
													value="${activityVO.createDate}" /></span>
										</div>
										<div class="card-deco">
											活動地址： <span>${activityVO.address}</span>
										</div>
										<div class="card-deco">
											活動狀態： <span> <c:forEach var="actEntry"
													items="${actMap}">
													<c:if test="${activityVO.status == actEntry.key}">${actEntry.value}</c:if>
												</c:forEach>
											</span>
										</div>

									</div>

									<div class=" view_edit_icon">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/activity/ActivityServlet.do">
											<input type="hidden" name="activityNo"
												value="${activityVO.activityNo}"> <input
												type="hidden" name="action" value="getOne_For_Display">
											<div class="view_edit">
												<button class="button2" type="submit">
													<img
														src="<%=request.getContextPath()%>/img/activity-img/view.png"
														title="瀏覽">
												</button>
											</div>
										</FORM>
										<c:if test="${activityVO.status == 'a3'}">
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/activity/ActivityServlet.do">
												<input type="hidden" name="activityNo"
													value="${activityVO.activityNo}"> <input
													type="hidden" name="action" value="getOne_For_Update">
												<div class="view_edit">
													<button class="button2" type="submit">
														<img
															src="<%=request.getContextPath()%>/img/activity-img/edit_icon.png"
															title="編輯">
													</button>
												</div>
											</FORM>
										</c:if>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="card-body act_infor">
									<p class="card-text">活動簡介:</p>
									<span>${activityVO.introduction}</span>
								</div>
							</div>
							<hr style="color: gray;">
						</div>
					</li>
				</c:forEach>

			</div>
		</div>
	</div>
	<div class="flower_pic">
		<img src="<%=request.getContextPath()%>/img/activity-img/flower5.png"
			alt="flower">
	</div>
	<div class="block2"></div>
	<nav id="pagetop" class="block_pagetop">
		<a href="#"> top <span></span>
		</a>
	</nav>


	<%@ include file="/footer.file"%>


	<!-- <script src="js/bootstrap.bundle.min.js"></script> -->
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

			$("#create").on("click", function() {
				window.location.href = 'createActivity.jsp';
			});
		});
	</script>
</body>

</html>