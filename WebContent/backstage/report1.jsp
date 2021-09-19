<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/report1.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />

<title>檢舉審核</title>
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
				<div class="worker_management_list none">
					<a href=""><p>管理員列表</p></a>
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
						<p>審核作業</p>
					</div>
					<div>
						<a href=""><img class="logo"
							src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg"
							alt="" /></a>
					</div>
				</header>
				<div class="line"></div>

				<div class="report_content">
					<div>檢舉資料</div>
					<div>
						<table>
							<tr>
								<th>檢舉單號:</th>
								<td>${reportVO.REPORT_NO}</td>
							</tr>
							<tr>
								<th>留言編號:</th>
								<td>${reportVO.MSG_NO}</td>
							</tr>
							<tr>
								<th>檢舉內容:</th>
								<td>${reportVO.REPORT}</td>
							</tr>
							<tr>
								<th>檢舉資訊:</th>
								<td>${reportVO.REPORT_MSG}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="line"></div>
				<div class="footer">
					<div>審核結果</div>
					<form action="<%=request.getContextPath()%>/ReportReview"
						method="post">
						<div>
							<input type="radio" id="pass" name="verify" value="pass" /> <label
								for="pass">審核通過</label> <input type="radio" id="fail"
								name="verify" value="fail" checked /> <label for="fail">審核未通過</label>
							<input type="hidden" name="action" value="reportVerify">
							<input type="hidden" name="reportNo"
								value="${reportVO.REPORT_NO}">
						</div>
						<div>
							<textarea name="opinion" id="" cols="50" rows="5"
								placeholder="審核意見欄">
本平台不可發表不雅言論</textarea>
							<button type="submit" class="submit">提交</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
</body>
</html>
