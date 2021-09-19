<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.report.model.*"%>
<%@ page import="java.util.stream.Collectors"%>

<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}

	//將所有檢舉抓出
	ReportService reportSvc = new ReportService();
	List<ReportVO> list = reportSvc.getAll();
	//濾出審核中活動		
	List<ReportVO> listA0 = list.stream().filter(e -> e.getREPORT_STATUS().equals("a0"))
			.collect(Collectors.toList());
	//濾出通過活動
	List<ReportVO> listA1 = list.stream().filter(e -> e.getREPORT_STATUS().equals("a1"))
			.collect(Collectors.toList());
	//濾出未通過活動
	List<ReportVO> listA2 = list.stream().filter(e -> e.getREPORT_STATUS().equals("a2"))
			.collect(Collectors.toList());

	pageContext.setAttribute("listA0", listA0);
	pageContext.setAttribute("listA1", listA1);
	pageContext.setAttribute("listA2", listA2);
%>




<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/report.css">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<title>檢舉列表</title>
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
					<a href="<%=request.getContextPath()%>/backstage/homepage.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/house.svg"
						alt="">
						<p>首頁公告</p></a>
				</div>

				<!-- 人員管理 -->
				<div class="worker_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/member.svg"
						alt="">
						<p>人員管理</p></a>
				</div>
				<!-- 管理員列表 -->
				<div class="worker_management_list none">
					<a href=""><p>管理員列表</p></a>
				</div>

				<!-- 檢舉列表 -->
				<div class="report_list">
					<a href="#"><img
						src="<%=request.getContextPath()%>/img/backstage-img/list.svg"
						alt="">
						<p>檢舉列表</p></a>
				</div>
				<!-- 檢舉清單  待審核名單 -->
				<div class="report_list_all none">
					<a href=""><p>檢舉清單</p></a> <a href=""><p>待審核名單</p></a>
				</div>
				<!-- 活動管理 -->
				<div class="event_management">
					<a
						href="<%=request.getContextPath()%>/backstage/event_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/events.svg"
						alt="">
						<p>活動管理</p></a>
				</div>

				<!-- 會員管理 -->
				<div class="member_management">
					<a
						href="<%=request.getContextPath()%>/backstage/member_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/people.svg"
						alt="">
						<p>會員管理</p></a>
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
				<header class="header">
					<div class="h1">
						<p>檢舉列表</p>
					</div>
					<div>
						<a href=""><img class="logo"
							src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg"
							alt="" /></a>
					</div>
				</header>
				<div class="line"></div>
				<div class="body_content_data">
					<div class="container">
						<h2></h2>
						<select>
							<option value="a0" selected>待審核</option>
							<option value="a1">通過</option>
							<option value="a2">未通過</option>
						</select>



						<ul class="responsive-table">
							<li class="table-header">
								<div class="header-1">檢舉編號</div>
								<div class="header-2">檢舉資訊</div>
								<div class="header-3">檢舉內容</div>
								<div class="header-4">審核</div>
							</li>


							<c:forEach var="reportVO" items="${listA0}">
								<li class="table-row a0" id="a0">
									<div class="col col-1 ">${reportVO.REPORT_NO}</div>
									<div class="col col-2 ">${reportVO.REPORT_MSG}</div>
									<div class="col col-3 ">${reportVO.REPORT}</div>
									<div class="col col-4">
										<span class="STATUS">待審核</span>
									</div>
									<form action="<%=request.getContextPath()%>/ReportReview"
										method="GET">
										<input type="hidden" name="reportNo"
											value="${reportVO.REPORT_NO}"> <input type="hidden"
											name="action" value="reportReview">
									</form>
								</li>
							</c:forEach>

							<c:forEach var="reportVO" items="${listA1}">
								<li class="table-row a1" id="a1">
									<div class="col col-1 ">${reportVO.REPORT_NO}</div>
									<div class="col col-2 ">${reportVO.REPORT_MSG}</div>
									<div class="col col-3 ">${reportVO.REPORT}</div>
									<div class="col col-4">
										<span class="STATUS">通過</span>
									</div>
								</li>
							</c:forEach>


							<c:forEach var="reportVO" items="${listA2}">
								<li class="table-row a2" id="a2">
									<div class="col col-1 ">${reportVO.REPORT_NO}</div>
									<div class="col col-2 ">${reportVO.REPORT_MSG}</div>
									<div class="col col-3 ">${reportVO.REPORT}</div>
									<div class="col col-4">
										<span class="STATUS">通過</span>
									</div>
								</li>
							</c:forEach>



						</ul>
					</div>


					<!-- 每一筆活動列出來 -->

				</div>

			</div>
		</div>
	</div>
	<div class="block"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/backstage-js/report.js"></script>
</body>
</html>
