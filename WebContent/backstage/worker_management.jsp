<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.manager_msg.model.*"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}

	Manager_msgJDBCDAO dao = new Manager_msgJDBCDAO();
	List<Manager_msgVO> list = dao.getAll();
	pageContext.setAttribute("list", list);
	ManagerVO managerVO = (ManagerVO) session.getAttribute("manager");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/worker_management.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<title>人員管理</title>
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
						alt="" />
						<p>首頁公告</p></a>
				</div>

				<!-- 人員管理 -->
				<div class="worker_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/member.svg"
						alt="" />
						<p>人員管理</p></a>
				</div>
				<!-- 管理員列表 -->
				<div class="worker_management_list">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management1.jsp"><p>管理員列表</p></a>
				</div>

				<!-- 檢舉列表 -->
				<div class="report_list">
					<a href="<%=request.getContextPath()%>/backstage/report.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/list.svg"
						alt="" />
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
						alt="" />
						<p>活動管理</p></a>
				</div>

				<!-- 會員管理 -->
				<div class="member_management">
					<a
						href="<%=request.getContextPath()%>/backstage/member_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/people.svg"
						alt="" />
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
						<p>人員管理</p>
					</div>
					<div>
						<a href=""><img class="logo"
							src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg"
							alt="" /></a>
					</div>
				</header>
				<div class="line"></div>
				<div class="worker_data">
					<p>管理員資料</p>
				</div>

				<div class="worker_name">
					<p>${manager.nickname}</p>
				</div>

				<div class="worker_maindata">
					<img id="preview" src="<%=request.getContextPath()%>/PicReader" />
					<table>
						<tr>
							<th>帳號:</th>
							<td class="account">${manager.account}</td>
						</tr>
						<tr>
							<th>暱稱:</th>
							<td class="name">${manager.nickname}</td>
						</tr>
						<tr>
							<th>電話:</th>
							<td class="phone">${manager.phone}</td>
						</tr>
						<tr>
							<th>Email:</th>
							<td class="email">${manager.email}</td>
						</tr>
					</table>
				</div>
				<div class="border">
					<input type="file" accept="image/*,.pdf" id="file" />
					<div class="modify">
						<div class="modify-1">
							<p>
								<img
									src="<%=request.getContextPath()%>/img/backstage-img/pen.svg"
									alt="" />修改個人資料
							</p>
						</div>
						<div class="modify_save">
							<p>
								<img
									src="<%=request.getContextPath()%>/img/backstage-img/pen.svg"
									alt="" />儲存修改
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/backstage-js/workermanagement.js"></script>
</body>
</html>
