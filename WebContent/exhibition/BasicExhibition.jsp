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
<title>展覽管理</title>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="../css/bootstrap.rtl.min.css" />
<link rel="stylesheet" href="../css/exhibition-css/exhibition.css" />
<link rel="stylesheet" href="../css/exhibition-css/exhibition2.css" />
</head>

<body>
	<div class="block1"></div>
	<!-- <div class="bg"><img src="<%=request.getContextPath()%>/backstage/img\bg.png" alt=""></div> -->
	<!-- <div class="bg"><img src="../img/exhibition-img/bg.png" alt="" /></div> -->
	<div class="bg">
		<img src="<%=request.getContextPath()%>/img/backstage-img/bg.png">
	</div>
	<div class="main">
		<div class="main-div">
			<div class="panel-div">
				<!-- 姓名圖片 -->
				<div class="name">
					<img src="<%=request.getContextPath()%>/PicReader" />
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
					<a href="#"><img
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
				<header class="header" style="border-bottom: solid 5px gray">
					<div class="h1">
						<p>展覽管理</p>
					</div>
					<div>
						<a href=""><img class="logo"
							src="../img/exhibition-img/APE3.svg" alt="" /></a>
					</div>
				</header>
				<h1 style="text-align: center; margin-top: 2%">展覽頁面管理</h1>
				<div style="width: 98%; margin-top: 2%" id="exhibition">
					<div class="col-12 d-flex justify-content-center">
						<select
							style="margin: 0 50px; padding: 10px 50px; font-size: 1.5rem"
							name="area" v-model="area" @click="search">
							<option
								style="margin: 0 50px; padding: 10px 50px; font-size: 1.5rem"
								value="E0">北區</option>
							<option
								style="margin: 0 50px; padding: 10px 50px; font-size: 1.5rem"
								value="E1">中區</option>
							<option
								style="margin: 0 50px; padding: 10px 50px; font-size: 1.5rem"
								value="E2">南區</option>
						</select>
						<button @click="add"
							style="width: 80px; margin: 0 50px; font-size: 1.5rem">
							新增</button>
					</div>
					<!-- border: solid 2px gray; -->
					<div class="row" style="margin: 2% 0 0 1%">
						<div class="d-flex"
							style="background-color: lightgray; margin-bottom: 2%">
							<h1 style="text-align: center; margin-top: 7px" class="col-4">
								展覽圖片</h1>
							<h1 style="text-align: center; margin-top: 7px" class="col-4">
								展覽標題</h1>
							<h1 style="text-align: center; margin-top: 7px" class="col-4">
								展覽文章</h1>
						</div>
						<div class="row"
							style="margin-top: 2%; border-bottom: 10px grey solid"
							v-for="(EXH,index) in exh">
							<div class="col-5" style="z-index: 10">
								<div style="margin-left: 1%" class="w-100 h-100">
									<img style="width: 350px; height: 200px" :src="EXH.img" alt="" />
									<img v-if="image" :src="image" width="100%"
										:class="display(index)" /> <input type="file"
										@change="fileimg" :class="display(index)" />
								</div>
							</div>
							<div class="col-3 d-flex justify-content-center">
								<div style="margin-left: 1%">
									<h3>
										標題：<br>
										<h5>{{EXH.Topic}}</h5>
									</h3>
									<h3>
										時間：<br>
										<h5>{{EXH.Date}}</h5>
									</h3>
									<input style="margin-top: 30%; font-size: 1.2rem" type="text"
										v-model="EXH.Topic" :class="display(index)" /> <input
										style="margin-top: 10%; font-size: 1.2rem" type="text"
										v-model="EXH.Date" :class="display(index)" />
								</div>
							</div>
							<div class="col-4 d-flex align-items-center"
								style="overflow: auto">
								<div style="margin-left: 1%; word-break: break-all"
									class="w-100">
									<p style="font-size: 1rem">文章內容：{{EXH.Content}}</p>
									<textarea name="" id="" cols="35" rows="5"
										v-model="EXH.Content" :class="display(index)"></textarea>
								</div>
							</div>
							<div class="d-flex justify-content-center"
								style="margin-bottom: 1%">
								<button style="width: 100px; margin: 0 20px"
									@click="update(index)">修改</button>
								<button style="width: 100px; margin: 0 20px"
									@click="checkupdate(index)" :class="display(index)">
									確認修改</button>
								<span style="display: none">{{EXH.No}}</span>
							</div>
							<div class="back" :class="{display:bl}">
								<div class="back-max">
									<div class="back-max-update">
										<div class="container" style="height: 100%">
											<div class="row h-100 align-items-center">
												<div class="d-flex" style="background-color: lightgray">
													<h1 style="text-align: center; margin-top: 7px"
														class="col-4">新增圖片</h1>
													<h1 style="text-align: center; margin-top: 7px"
														class="col-4">新增標題</h1>
													<h1 style="text-align: center; margin-top: 7px"
														class="col-4">新增文章</h1>
												</div>
												<div class="col-4">
													<img v-if="image" :src="image" width="100%" /> <input
														type="file" @change="fileSelected" />
												</div>
												<div class="col-4">
													<textarea name="addtopic" id="" cols="30" rows="10"
														v-model="addtopic"></textarea>
												</div>
												<div class="col-4">
													<textarea name="addcontent" id="" cols="30" rows="10"
														v-model="addcontent"></textarea>
												</div>
											</div>
											<button @click="check">確認新增</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="block"></div>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script src="../js/bootstrap.bundle.min.js"></script>
	<script src="../js/jquery-3.6.0.min.js"></script>
	<script src="../js/vue.js"></script>
	<script src="../js/exhibition-js/BasicExhibition.js"></script>
</body>
</html>