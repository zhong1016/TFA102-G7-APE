<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.manager.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="java.util.stream.Collectors"%>
<%@ page import="com.activity.model.*"%>

<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}

	ActivityService activitySvc = new ActivityService();
	List<ActivityVO> list = activitySvc.getAll();

	List<ActivityVO> listA0 = list.stream().filter(e -> e.getStatus().equals("a0"))
			.collect(Collectors.toList());

	List<ActivityVO> listA1 = list.stream().filter(e -> e.getStatus().equals("a1"))
			.collect(Collectors.toList());

	List<ActivityVO> listA2 = list.stream().filter(e -> e.getStatus().equals("a2"))
			.collect(Collectors.toList());

	List<ActivityVO> listA3 = list.stream().filter(e -> e.getStatus().equals("a3"))
			.collect(Collectors.toList());

	pageContext.setAttribute("listA0", listA0);
	pageContext.setAttribute("listA1", listA1);
	pageContext.setAttribute("listA2", listA2);
	pageContext.setAttribute("listA3", listA3);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/backendmanagercheack1.css">
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

				<div class="name">
					<img src="<%=request.getContextPath()%>/PicReader" alt="" />
					<p>${manager.nickname}</p>
				</div>



				<div class="homepage">
					<a href="<%=request.getContextPath()%>/backstage/homepage.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/house.svg"
						alt="">
						<p>首頁公告</p></a>
				</div>

				<div class="worker_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/member.svg"
						alt="">
						<p>人員管理</p></a>
				</div>

				<div class="worker_management_list none">
					<a href=""><p>管理員列表</p></a>
				</div>

				<div class="report_list">
					<a href="<%=request.getContextPath()%>/backstage/report.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/list.svg"
						alt="">
						<p>檢舉列表</p></a>
				</div>

				<div class="report_list_all none">
					<a href=""><p>檢舉清單</p></a> <a href=""><p>待審核名單</p></a>
				</div>

				<div class="event_management">
					<a
						href="<%=request.getContextPath()%>/backstage/event_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/events.svg">
						<p>活動管理</p></a>
				</div>


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


			<div class="panel-text">
				<header class="header">
					<div class="h1">
						<p></p>
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
						<h2>活動管理</h2>
						<select>
							<option value="a0" selected>待審核</option>
							<option value="a1">通過</option>
							<option value="a2">未通過</option>
							<option value="a3">編輯中</option>
						</select>


						<div class="over">
							<ul class="responsive-table">
								<li class="table-header">
									<div class="header-1">活動名稱</div>
									<div class="header-2">活動日期</div>
									<div class="header-3">活動類型</div>
									<div class="header-4">活動簡介</div>
									<div class="header-5">活動示意</div>
									<div class="header-6">審核</div>
								</li>

								<jsp:useBean id="acttypeDAO" scope="page"
									class="com.activitytype.model.ActivitytypeJDBCDAO" />

								<c:forEach var="activityVO" items="${listA0}">
									<li class="table-row a0" id="a0">
										<div class="col col-1" data-label="ACTIVITY_NAME">${activityVO.activityName}</div>
										<div class="col col-2" data-label="ACTIVITY_DATE">${activityVO.startDate}
											${activityVO.closeDate}</div>
										<div class="col col-3" data-label="ACT_TYPE_NAME">
											<c:forEach var="acttypeVO" items="${acttypeDAO.all}">
												<c:if
													test="${(activityVO.activityTypeNo==acttypeVO.activityTypeNo)}">${acttypeVO.activityTypeName}
												</c:if>
											</c:forEach>
										</div>
										<div class="col col-4" data-label="INTRODUCTION">${activityVO.introduction}</div>
										<div class="col col-5" data-label="ACTIVITY_PIC">
											<img
												src="<%=request.getContextPath()%>/ActPicReader?activityNo1=${activityVO.activityNo}"
												alt="">
										</div>
										<div class="col col-6" data-label="STATUS">
											<span class="STATUS">待審核</span>
										</div>
										<form action="<%=request.getContextPath()%>/EventManagement"
											method="GET">
											<input type="hidden" name="activityNo"
												value="${activityVO.activityNo}">
										</form>
									</li>
								</c:forEach>



								<c:forEach var="activityVO" items="${listA1}">
									<li class="table-row a1" id="a1">
										<div class="col col-1 " data-label="ACTIVITY_NAME">${activityVO.activityName}</div>
										<div class="col col-2 " data-label="ACTIVITY_DATE">${activityVO.startDate}
											${activityVO.closeDate}</div>
										<div class="col col-3 " data-label="ACT_TYPE_NAME">
											<c:forEach var="acttypeVO" items="${acttypeDAO.all}">
												<c:if
													test="${(activityVO.activityTypeNo==acttypeVO.activityTypeNo)}">${acttypeVO.activityTypeName}
												</c:if>
											</c:forEach>

										</div>
										<div class="col col-4 " data-label="INTRODUCTION">${activityVO.introduction}</div>
										<div class="col col-5 " data-label="ACTIVITY_PIC">
											<img
												src="<%=request.getContextPath()%>/ActPicReader?activityNo1=${activityVO.activityNo}"
												alt="">
										</div>
										<div class="col col-6 " data-label="STATUS">
											<span class="STATUS">通過</span>
										</div>
									</li>
								</c:forEach>


								<c:forEach var="activityVO" items="${listA2}">
									<li class="table-row a2" id="a2">
										<div class="col col-1 " data-label="ACTIVITY_NAME">${activityVO.activityName}</div>
										<div class="col col-2 " data-label="ACTIVITY_DATE">${activityVO.startDate}
											${activityVO.closeDate}</div>
										<div class="col col-3 " data-label="ACT_TYPE_NAME">
											<c:forEach var="acttypeVO" items="${acttypeDAO.all}">
												<c:if
													test="${(activityVO.activityTypeNo==acttypeVO.activityTypeNo)}">${acttypeVO.activityTypeName}
												</c:if>
											</c:forEach>
										</div>
										<div class="col col-4 " data-label="INTRODUCTION">${activityVO.introduction}</div>
										<div class="col col-5 " data-label="ACTIVITY_PIC">
											<img
												src="<%=request.getContextPath()%>/ActPicReader?activityNo1=${activityVO.activityNo}"
												alt="">
										</div>
										<div class="col col-6 " data-label="STATUS">
											<span class="STATUS">未通過</span>
										</div>
									</li>
								</c:forEach>
								<c:forEach var="activityVO" items="${listA3}">
									<li class="table-row a3" id="a3">
										<div class="col col-1 " data-label="ACTIVITY_NAME">${activityVO.activityName}</div>
										<div class="col col-2 " data-label="ACTIVITY_DATE">${activityVO.startDate}
											${activityVO.closeDate}</div>
										<div class="col col-3 " data-label="ACT_TYPE_NAME">
											<c:forEach var="acttypeVO" items="${acttypeDAO.all}">
												<c:if
													test="${(activityVO.activityTypeNo==acttypeVO.activityTypeNo)}">${acttypeVO.activityTypeName}
												</c:if>
											</c:forEach>


										</div>
										<div class="col col-4 " data-label="INTRODUCTION">${activityVO.introduction}</div>
										<div class="col col-5 " data-label="ACTIVITY_PIC">
											<img
												src="<%=request.getContextPath()%>/ActPicReader?activityNo1=${activityVO.activityNo}"
												alt="">
										</div>
										<div class="col col-6 " data-label="STATUS">
											<span class="STATUS">編輯中</span>
										</div>
									</li>
								</c:forEach>

							</ul>
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
		src="<%=request.getContextPath()%>/js/backstage-js/events_management.js"></script>
</body>
</html>
