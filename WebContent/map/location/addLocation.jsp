<%@ page contentType="text/html ; charset=UTF-8;" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.manager_msg.model.*"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.location.model.*"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", -1);
%>
<%
 	if (session.getAttribute("manager") == null) {
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
 		dispatcher.forward(request, response);
 	}

 	Manager_msgJNDIDAO dao = new Manager_msgJNDIDAO();
 	List<Manager_msgVO> list = dao.getAll();
 	pageContext.setAttribute("list", list);
 	ManagerVO managerVO = (ManagerVO) session.getAttribute("manager");
%>
<%
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restaurantVO");
%>
<%
	LocationVO locationVO = (LocationVO) request.getAttribute("locationVO");
%>
<head>
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-store">
<META HTTP-EQUIV="expires" CONTENT="0">
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/homepage.css">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<style>
#pet-select{
height:34px;
margin-left: 5%
}
th{
font-size: 1.5rem;
}
tr{
height: 60px
}
</style>

<title>展區資料新增 - addrestaurant.jsp</title>
</head>
<body>
<div class="block1"></div>
<div class="bg">
		<img src="<%=request.getContextPath()%>/img/backstage-img/bg.png">
	</div>
	<div class="main">
		<div class="main-div">
			<div class="panel-div">
				<!-- 姓名圖片 -->
				<div class="name">
					<img src="<%=request.getContextPath()%>/PicReader" />
					<p><%=managerVO.getNickname()%></p>
				</div>

				<!-- 功能版面 -->

				<!-- 首頁公告 -->
				<div class="homepage">
					<a href="#"><img
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
					<a href="#"><p>管理員列表</p></a>
				</div>

				<!-- 檢舉列表 -->
				<div class="report_list">
					<a href="<%=request.getContextPath()%>/backstage/report.jsp"><img
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
					<a href="<%=request.getContextPath()%>/exhibition/BasicExhibition.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/people.svg"
						alt="" />
						<p>展覽管理</p> </a>
				</div>
				<!-- 餐廳管理 -->
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
					<a href="<%=request.getContextPath()%>/map/restaurant/listAllRestaurant.jsp">
						<p>餐廳列表</p>
					</div>
					<div class="h1">
					<a href="<%=request.getContextPath()%>/map/location/listAllLocation.jsp">
						<p>展區列表</p>
					</div>
					<div>
						<a href="<%=request.getContextPath()%>/map/MapManagement.jsp"><img class="logo" src="img\APE3.svg"
							alt="" /></a>
					</div>
				</header>
				<div class="line"></div>
				<!-- 註冊面板 -->
				<div class="plus_management">
					<a
						href="<%=request.getContextPath()%>/map/location/addLocation.jsp">
						<img src="./img/plus.svg" alt="" />
						<p style="font-size: 2rem;font-weight: bold;text-align: center;">新增展區</p>
					</a>
				</div>
<div class="line"></div>



				<div class="register_data">
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>


					<form METHOD="post" action="location.do" name="form1">


						<table style="margin: auto;margin-top: 5%">
							<tr>
								<th>名稱:</th>
								<td><input type="TEXT" name="locationname"
									value="<%=(locationVO == null) ? "" : locationVO.getLocationname()%>" /></td>
							</tr>
							
							<tr>
								<th>地址:</th>
								<td><input type="TEXT" name="locationadd"
									value="<%=(locationVO == null) ? "" : locationVO.getLocationadd()%>" /></td>
							</tr>
							
						</table>
						<input type="hidden" name="action" value="insert">
						<div style="display: flex;width: 100%"><button style="margin:2% auto 0 auto;font-size: 2rem" type="submit">新增</button></div>
					</form>
</body>
</html>