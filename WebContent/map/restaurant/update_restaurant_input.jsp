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
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restaurantVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
div.management_list{
    border: 1px solid grey;
    width: 100%;
    max-height: 65%;
    overflow:auto ;
    padding:1.5% 1.5% 1.5% 1.5%;
    margin:0px;
}
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
<title>餐廳修改</title>
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
							class="logo" src="/APE/map/restaurant/img\APE3.svg" alt="" /></a>
					</div>
				</header>
				<div class="line"></div>
				<FORM METHOD="post" ACTION="restaurant.do" name="form1">
					<div class="line"></div>
					<div class="worker_data">
						<th>餐廳編號:<font color=red></font></th>
						<td><%=restaurantVO.getRestaurantno()%></td>
					</div>
					<div class="worker_maindata">

						<table style="margin: auto;margin-top: 5%">
							<tr>
								<th>餐廳名稱:</th>
								<td class="name"><input type="TEXT" name="restaurantname"
									size="30" value="<%=restaurantVO.getRestaurantname()%>" /></td>
							</tr>
							<tr>
								<th>餐廳評價:</th>
								<td class="name"><input type="TEXT" name="restaurantlevel"
									size="30" value="<%=restaurantVO.getRestaurantlevel()%>" /></td>
							</tr>
							<tr>
								<th>餐廳電話:</th>
								<td class="phone"><input type="TEXT" name="restaurantnum"
									size="30" value="<%=restaurantVO.getRestaurantnum()%>" /></td>
							</tr>
							<tr>
								<th>餐廳地址:</th>
								<td class="email"><input type="TEXT" name="restaurantadd"
									size="30" value="<%=restaurantVO.getRestaurantadd()%>" /></td>
							</tr>
							<tr>
								<th>餐廳種類:</th>
								<td><input type="TEXT" name="restauranttype" size="30"
									value="<%=restaurantVO.getRestauranttype()%>" /></td>
							</tr>
							<jsp:useBean id="locationSvc" scope="page"
								class="com.location.model.LocationService" />
							<tr>
								<th>鄰近展區:<font color=red></font></th>
								<td><select size="1" name="locationno" id="pet-select">
										<c:forEach var="locationVO" items="${locationSvc.all}">
											<option value="${locationVO.locationno}"
												${(restaurantVO.locationno==locationVO.locationno)?'selected':'' }>${locationVO.locationname}
										</c:forEach>
								</select></td>
							</tr>
						</table>
					</div>
					<input type="hidden" name="action" value="update"> <input
						type="hidden" name="restaurantno"
						value="<%=restaurantVO.getRestaurantno()%>"> <input
						type="hidden" name="requestURL"
						value="<%=request.getParameter("requestURL")%>">
					<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
					<input type="hidden" name="whichPage"
						value="<%=request.getParameter("whichPage")%>">
					<!--只用於:istAllEmp.jsp-->
					<div style="display: flex;width: 100%"><input style="margin:2% auto 0 auto;font-size: 2rem"  type="submit" value="送出修改"></div>
				</FORM>

			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="./js/workermanagement.js"></script>
</body>
</html>
