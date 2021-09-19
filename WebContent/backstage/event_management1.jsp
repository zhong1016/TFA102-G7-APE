<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.activity.model.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="java.lang.*"%>

<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/backendmanagercheack2.css">
<title>活動管理</title>
</head>
<body>
	<div class="block1"></div>
	<div class="bg">
		<img src="<%=request.getContextPath()%>/img/backstage-img/bg.png"
			alt="">
	</div>
	<div class="main">
		<div class="main-div">
			<div class="panel-div">
				<!-- 姓名圖片 -->
				<div class="name">
					<img src="<%=request.getContextPath()%>/PicReader" alt="" />
					<p>${manager.nickname}</p>
				</div>

				<!-- 功能版面 -->
				<!-- 首頁公告 -->
				<div class="homepage">
					<a href="<%=request.getContextPath()%>/backstage/homepage.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/house.svg"
						alt="">
						<p>首頁公告</p>
					</a>
				</div>

				<!-- 人員管理 -->
				<div class="worker_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/member.svg"
						alt="">
						<p>人員管理</p>
					</a>
				</div>
				<!-- 管理員列表 -->
				<div class="worker_management_list none">
					<a href="">
						<p>管理員列表</p>
					</a>
				</div>

				<!-- 檢舉列表 -->
				<div class="report_list">
					<a href="<%=request.getContextPath()%>/backstage/report.jsp"> <img
						src="<%=request.getContextPath()%>/img/backstage-img/list.svg"
						alt="">
						<p>檢舉列表</p>
					</a>
				</div>
				<!-- 檢舉清單  待審核名單 -->
				<div class="report_list_all none">
					<a href="">
						<p>檢舉清單</p>
					</a> <a href="">
						<p>待審核名單</p>
					</a>
				</div>
				<!-- 活動管理 -->
				<div class="event_management">
					<a
						href="<%=request.getContextPath()%>/backstage/event_management.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/events.svg"
						alt="">
						<p>活動管理</p>
					</a>
				</div>

				<!-- 會員管理 -->
				<div class="member_management">
					<a
						href="<%=request.getContextPath()%>/backstage/member_management.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/people.svg"
						alt="">
						<p>會員管理</p>
					</a>
				</div>

				<div class="member_management">
					<a
						href="<%=request.getContextPath()%>/exhibition/BasicExhibition.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/exhibition.svg"
						alt="" />
						<p>展覽管理</p> </a>
				</div>
				<div class="member_management">
					<a href="<%=request.getContextPath()%>/map/MapManagement.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/map.svg"
						alt="">
						<p>地圖管理</p></a>
				</div>
				<div>
					<form action="<%=request.getContextPath()%>/Logout">
						<button type="submit" class="logout">登出</button>
					</form>
				</div>
			</div>

			<!-- 內容板塊 -->
			<div class="panel-text">

				<div class="body_content_data">
					<div class="container">
						<h2>活動審核</h2>
						<table class="TB_COLLAPSE">

							<tr class="">
								<th>項目</th>
								<th>明細</th>
							</tr>
							<tr>
								<td>活動編號</td>
								<td>${activityVO.activityNo}</td>
							</tr>
							<tr>
								<td>活動名稱</td>
								<td>${activityVO.activityName}</td>
							</tr>
							<tr>
								<td>活動開始日期</td>
								<td>${activityVO.startDate}</td>
							</tr>
							<tr>
								<td>活動結束日期</td>
								<td>${activityVO.closeDate}</td>
							</tr>
							<tr>
								<td>活動天數</td>
								<td>${activityVO.dayCount}</td>
							</tr>
							<tr>
								<td>活動時間</td>
								<td>${activityVO.activityHours}</td>
							</tr>
							<tr>
								<td>攤位數量</td>
								<td>${activityVO.rentCount}</td>
							</tr>
							<tr>
								<td>建立時間</td>
								<td>${activityVO.createDate}</td>
							</tr>
							<tr>
								<td>活動地址</td>
								<td>${activityVO.address}</td>
							</tr>
							<tr>
								<td>出租價格</td>
								<td>${activityVO.price}</td>
							</tr>
							<tr>
								<td>活動簡介</td>
								<td>${activityVO.introduction}</td>
							</tr>
							<tr>
								<td>活動圖片</td>
								<td><img
									src="<%=request.getContextPath()%>/ActPicReader?activityNo1=${activityVO.activityNo}">
								</td>
							</tr>
							<tr>
								<td>活動類型</td>
								<td>小農商品</td>
							</tr>

							<tr>
								<td>審核狀態</td>
								<td>待審核</td>
							</tr>

						</table>


						<form action="<%=request.getContextPath()%>/EventVerify"
							method="post">
							<button type="submit" class="button1" name="verify" value="pass">通過</button>
							<button type="submit" class="button1" name="verify" value="fail">未通過</button>
						</form>





					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</body>

</html>
