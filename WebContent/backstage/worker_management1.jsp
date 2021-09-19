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
	ManagerJDBCDAO managerDAO = new ManagerJDBCDAO();
	List<ManagerVO> list = managerDAO.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/worker_management1.css">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<title>管理員列表</title>
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
				<div class="worker_management_list">
					<a href="#">
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
				<header class="header">
					<div class="h1">
						<p>管理員列表</p>
					</div>
					<div>
						<a href=""> <img class="logo"
							src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg"
							alt="" />
						</a>
					</div>
				</header>
				<div class="line"></div>
				<div class="plus_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management2.jsp">
						<img
						src="<%=request.getContextPath()%>/img/backstage-img/plus.svg"
						alt="" />
						<p>新增管理員</p>
					</a>

				</div>
				<!-- 管理員明細列表 -->

				<div class="management_list">
					<table>
						<tr>
							<th class="id">編號</th>
							<th class="name">暱稱</th>
							<th class="account">帳號</th>
							<th class="password">密碼</th>
							<th class="phone">電話</th>
							<th class="email">信箱</th>
							<th class="update">修改</th>
							<th class="delete">刪除</th>
						</tr>

						<c:forEach var="managerVO" items="${list}">
							<tr>
								<td>${managerVO.manager_no}</td>
								<td>${managerVO.nickname}</td>
								<td>${managerVO.account}</td>
								<td><c:if
										test="${managerVO.manager_no == manager.manager_no || manager.manager_no == 1}">          	
                				${managerVO.password}
       							</c:if></td>
								<td>${managerVO.phone}</td>
								<td>${managerVO.email}</td>
								<td><c:if
										test="${managerVO.manager_no == manager.manager_no || manager.manager_no == 1}">
										<form action="<%=request.getContextPath()%>/ManagerUpdate"
											method="get">
											<input type="submit" value="修改"> <input type="hidden"
												name="manager_no" value="${managerVO.manager_no}"> <input
												type="hidden" name="action" value="getOne_For_Update">
										</form>
									</c:if></td>
								<td><c:if
										test="${manager.manager_no != managerVO.manager_no && manager.manager_no == 1}">
										<form action="<%=request.getContextPath()%>/ManagerUpdate"
											method="get">
											<input type="button" value="刪除"> <input type="hidden"
												name="manager_no" value="${managerVO.manager_no}"> <input
												type="hidden" name="action" value="delete">
										</form>
									</c:if></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/backstage-js/worker_management1.js"></script>


</body>
</html>
