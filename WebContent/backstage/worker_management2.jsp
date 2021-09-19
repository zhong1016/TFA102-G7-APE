<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager_msg.model.*"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>

<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}
	ManagerVO managerVO = (ManagerVO) request.getAttribute("ManagerVO");
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
	href="<%=request.getContextPath()%>/css/backstage-css/worker_management2.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<title>管理員新增</title>
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
				<div class="worker_management_list ">
					<a href="<%=request.getContextPath()%>/backstage/worker_management1.jsp"><p>管理員列表</p></a>
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
					<a href=""><img
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
						<p>新增管理員</p>
					</div>
					<div>
						<a href=""><img class="logo"
							src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg"
							alt="" /></a>
					</div>
				</header>
				<div class="line"></div>

				<div class="update_manager">

					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>


					<form action="<%=request.getContextPath()%>/ManagerUpdate"
						method="post">
						<table>
							<tr>
								<td>帳號:</td>
								<td><input type="text" name="account"
									value="<%=(managerVO == null) ? "" : managerVO.getAccount()%>" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>密碼:</td>
								<td><input type="password" name="password"
									value="<%=(managerVO == null) ? "" : managerVO.getPassword()%>" autocomplete="off" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>確認密碼:</td>
								<td><input type="password" name="passwordConfirm"
									value="<%=(managerVO == null) ? "" : ""%>" />
								<td></td>
								</td>
							</tr>
							<tr>
								<td>暱稱:</td>
								<td><input type="text" name="nickname"
									value="<%=(managerVO == null) ? "綠油油" : managerVO.getNickname()%>" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>電話:</td>
								<td><input type="text" name="phone"
									value="<%=(managerVO == null) ? "0931726555" : managerVO.getPhone()%>" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>信箱:</td>
								<td><input type="text" name="email"
									value="<%=(managerVO == null) ? "xx841995@gmail.com" : managerVO.getEmail()%>" />
								<td></td>
								</td>
							</tr>
						</table>
						<input type="hidden" name="action" value="addManager"> <input
							type="hidden" name="empno" value=""> <input type="submit"
							value="新增">
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
</body>
</html>
