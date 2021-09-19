<%@ page contentType="text/html ; charset=UTF-8;" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="java.text.*"%>
<%@ page import="com.manager_msg.model.*"%>
<%@ page import="com.manager.model.*"%>
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
	RestaurantService restaurantSvc = new RestaurantService();
	List<RestaurantVO> listRestaurant = restaurantSvc.getAll();
	pageContext.setAttribute("list", listRestaurant);
%>
<jsp:useBean id="locationSvc" scope="page"
	class="com.location.model.LocationService" />
<html lang="en">
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
div.management_list{
    border: 1px solid grey;
    width: 100%;
    max-height: 65%;
    overflow:auto ;
    padding:1.5% 1.5% 1.5% 1.5%;
    margin:0px;
}
</style>
<title>餐廳列表</a></title>
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
						src="<%=request.getContextPath()%>/img/backstage-img/exhibition.svg"
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
						<a href="<%=request.getContextPath()%>/map/MapManagement.jsp"><img
							class="logo" src="img\APE3.svg" alt="" /></a>
					</div>
				</header>
				<div class="line"></div>
				<div class="plus_management">
					<a
						href="<%=request.getContextPath()%>/map/location/addLocation.jsp">
						<img src="./img/plus.svg" alt="" />
						<p style="font-weight:bold;font-size: 2rem;text-align: center;">新增展區</p>
					</a>
				</div>
				<!-- 管理員明細列表 -->

				<div class="management_list">
					<table>
						<tr>
							<th class="id">地區編號</th>
							<th class="name">地區名稱</th>
							<th>地區地址</th>
							<th>修改</th>
							<th>刪除<font color=red>(關聯測試與交易-小心)</font></th>

						</tr>

						<c:forEach var="locationVO" items="${locationSvc.all}">
							<tr>
								<td>${locationVO.locationno}</td>
								<td>${locationVO.locationname}</td>
								<td>${locationVO.locationadd}</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/map/location/location.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改">
										<input type="hidden" name="locationno" value="${locationVO.locationno}"> 
										<input type="hidden" name="action" value="getOne_For_Update">
										<input type="hidden" name="requestURL"
											value="<%=request.getServletPath()%>">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/map/location/location.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="刪除"> <input type="hidden"
											name="locationno" value="${locationVO.locationno}"> <input
											type="hidden" name="action" value="delete_Location">
									</FORM>
								</td>

							</tr>
						</c:forEach>
					</table>
<%
						if (request.getAttribute("listRestaurants_ByLocationno") != null) {
					%>
					<jsp:include page="listRestaurants_ByLocationno.jsp" />
					<%
						}
					%>

				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/backstage-js/homepage.js"></script>
</body>
</html>
