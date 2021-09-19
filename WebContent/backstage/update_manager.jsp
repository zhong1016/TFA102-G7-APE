<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/update_manager.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<title>管理員修改頁面</title>
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
						alt="" />
						<p>首頁公告</p>
					</a>
				</div>

				<!-- 人員管理 -->
				<div class="worker_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/member.svg"
						alt="" />
						<p>人員管理</p>
					</a>
				</div>
				<!-- 管理員列表 -->
				<div class="worker_management_list ">
					<a href="<%=request.getContextPath()%>/backstage/worker_management1.jsp">
						<p>管理員列表</p>
					</a>
				</div>

				<!-- 檢舉列表 -->
				<div class="report_list">
					<a href="<%=request.getContextPath()%>/backstage/report.jsp"> <img
						src="<%=request.getContextPath()%>/img/backstage-img/list.svg"
						alt="" />
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
						href="<%=request.getContextPath()%>/backstage/FinalReport/event_management.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/events.svg"
						alt="" />
						<p>活動管理</p>
					</a>
				</div>

				<!-- 會員管理 -->
				<div class="member_management">
					<a
						href="<%=request.getContextPath()%>/backstage/member_management.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/people.svg"
						alt="" />
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
				<header class="header">
					<div class="h1">
						<p>資料修改</p>
					</div>
					<div>
						<a href=""> <img class="logo"
							src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg"
							alt="" />
						</a>
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
								<td>管理員編號:</td>
								<td>${ManagerVO.manager_no}</td>
							</tr>
							<tr>
								<td>帳號:</td>
								<td><input type="text" name="account"
									value="${ManagerVO.account}" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>密碼:</td>
								<td><input type="text" name="password"
									value="${ManagerVO.password}" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>暱稱:</td>
								<td><input type="text" name="nickname"
									value="${ManagerVO.nickname}" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>電話:</td>
								<td><input type="text" name="phone"
									value="${ManagerVO.phone}" />
								<td></td>
								</td>
							</tr>

							<tr>
								<td>信箱:</td>
								<td><input type="text" name="email"
									value="${ManagerVO.email}" />
								<td></td>
								</td>
							</tr>
						</table>
						<input type="hidden" name="action" value="update"> <input
							type="hidden" name="managerNo" value="${ManagerVO.manager_no}">
						<input type="submit" value="送出修改">
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
</body>
</html>
