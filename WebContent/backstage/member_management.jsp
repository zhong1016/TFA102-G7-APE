<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.cop.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>






<%
	if (session.getAttribute("manager") == null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/backstage/login.jsp");
		dispatcher.forward(request, response);
	}

	MemService memSvc = new MemService();
	List<MemVO> memList = memSvc.getAll();
	pageContext.setAttribute("memList", memList);

	CopDAO dao = new CopDAO();
	List<CopVO> copList = dao.getAll();
	pageContext.setAttribute("copList", copList);
	System.out.println(copList);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/basic.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage-css/member_management.css">

<title>會員管理</title>
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
						alt="">
						<p valign="bottom">首頁公告</p> </a>
				</div>

				<!-- 人員管理 -->
				<div class="worker_management">
					<a
						href="<%=request.getContextPath()%>/backstage/worker_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/member.svg"
						alt="">
						<p>人員管理</p> </a>
				</div>
				<!-- 管理員列表 -->
				<div class="worker_management_list none">
					<a href="">
						<p>管理員列表</p>
					</a>
				</div>

				<!-- 檢舉列表 -->
				<div class="report_list">
					<a href="#"><img
						src="<%=request.getContextPath()%>/img/backstage-img/list.svg"
						alt="">
						<p>檢舉列表</p> </a>
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
						href="<%=request.getContextPath()%>/backstage/event_management.jsp"><img
						src="<%=request.getContextPath()%>/img/backstage-img/events.svg"
						alt="">
						<p>活動管理</p> </a>
				</div>

				<!-- 會員管理 -->
				<div class="member_management">
					<a href="#"><img
						src="<%=request.getContextPath()%>/img/backstage-img/people.svg"
						alt="">
						<p>會員管理</p> </a>
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
						<p>會員列表</p>
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
						<h2></h2>
						<select>
							<option value="a0" selected>一般會員</option>
							<option value="a1">廠商</option>
						</select>



						<ul class="responsive-table">
							<li class="table-header">
								<div class="header-1">會員/廠商編號</div>
								<div class="header-2">會員帳號</div>
								<div class="header-3">會員姓名</div>
								<div class="header-4">會員種類</div>
							</li>

							<c:forEach var="memVO" items="${memList}">
								<li class="table-row a0 b0" id="a0">
									<div class="col col-1 ">${memVO.userNo}</div>
									<div class="col col-2 ">${memVO.userId}</div>
									<div class="col col-3 ">${memVO.userlName}${memVO.userfName}</div>
									<div class="col col-4">會員</div> <input type="hidden"
									class="mem_id" value="${memVO.userNo}"> <input
									type="hidden" class="mem_account" value="${memVO.userId}">
									<input type="hidden" class="mem_password"
									value="${memVO.userPassword}"> <input type="hidden"
									class="mem_lastname" value="${memVO.userlName}"> <input
									type="hidden" class="mem_firstname" value="${memVO.userfName}">
									<input type="hidden" class="mem_phone"
									value="${memVO.userPhone}"> <input type="hidden"
									class="mem_address"
									value="${memVO.userAddressCity}${memVO.userAddressDistrict}${memVO.userAddress}">
									<input type="hidden" class="mem_email"
									value="${memVO.userEmail}"> <input type="hidden"
									class="mem_addtime" value="${memVO.createTime}"> <input
									type="hidden" class="mem_verify" value="${memVO.status}">

								</li>
							</c:forEach>


							<c:forEach var="copVO" items="${copList}">
								<li class="table-row a1 b1" id="a1">
									<div class="col col-1 ">${copVO.companyNo}</div>
									<div class="col col-2 ">${copVO.companyId}</div>
									<div class="col col-3 ">${copVO.companyName}</div>
									<div class="col col-4">廠商</div> <input type="hidden"
									class="cop_id" value="${copVO.companyNo}"> <input
									type="hidden" class="cop_account" value="${copVO.companyId}">
									<input type="hidden" class="cop_password"
									value="${copVO.companyPassword}"> <input type="hidden"
									class="cop_name" value="${copVO.companyName}"> <input
									type="hidden" class="cop_phone" value="${copVO.companyPhone}">
									<input type="hidden" class="cop_address"
									value="${copVO.companyAddressCity}${copVO.companyAddressCity}${copVO.companyAddress}">
									<input type="hidden" class="cop_email"
									value="${copVO.companyEmail}"> <input type="hidden"
									class="cop_addtime" value="${copVO.createTime}"> <input
									type="hidden" class="cop_verify" value="${copVO.status}">
								</li>
							</c:forEach>

						</ul>
					</div>

				</div>
			</div>

		</div>

	</div>

	<div class="modal" tabindex="-1" id="myModal0">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"></h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<div class="head">會員資料</div>
					<table class="member">
						<tr>
							<th>會員編號:</th>
							<td class="mem_id"></td>
						</tr>
						<tr>
							<th>會員頭貼:</th>
							<td class="mem_pic"><img src=""></td>
						</tr>
						<tr>
							<th>會員帳號:</th>
							<td class="mem_account"></td>
						</tr>
						<tr>
							<th>會員密碼:</th>
							<td class="mem_password"></td>
						</tr>
						<tr>
							<th>會員姓氏:</th>
							<td class="mem_lastname"></td>
						</tr>
						<tr>
							<th>會員名字:</th>
							<td class="mem_firstname"></td>
						</tr>
						<tr>
							<th>會員手機:</th>
							<td class="mem_phone"></td>
						</tr>
						<tr>
							<th>會員地址:</th>
							<td class="mem_address"></td>
						</tr>
						<tr>
							<th>會員信箱:</th>
							<td class="mem_email"></td>
						</tr>
						<tr>
							<th>加入時間:</th>
							<td class="mem_addtime"></td>
						</tr>
						<tr>
							<th>驗證狀態:</th>
							<td class="mem_verify"></td>
						</tr>
					</table>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal" tabindex="-1" id="myModal1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title"></h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">

					<div class="head">廠商資料</div>
					<table class="member">
						<tr>
							<th>廠商編號:</th>
							<td class="cop_id"></td>
						</tr>
						<tr>
							<th>廠商統編:</th>
							<td class="cop_account"></td>
						</tr>
						<tr>
							<th>廠商圖片:</th>
							<td class="cop_pic"><img src=""></td>
						</tr>
						<tr>
							<th>廠商圖片1:</th>
							<td class="cop_pic1"><img src=""></td>
						</tr>
						<tr>
							<th>廠商名字:</th>
							<td class="cop_name"></td>
						</tr>
						<tr>
							<th>廠商電話:</th>
							<td class="cop_phone"></td>
						</tr>
						<tr>
							<th>廠商地址:</th>
							<td class="cop_address"></td>
						</tr>
						<tr>
							<th>廠商信箱:</th>
							<td class="cop_email"></td>
						</tr>
						<tr>
							<th>創建時間:</th>
							<td class="cop_addtime"></td>
						</tr>
						<tr>
							<th>驗證狀態:</th>
							<td class="cop_verify"></td>
						</tr>
					</table>


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="block"></div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/js/backstage-js/member_management.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			function showModal0() {
				$("#myModal0").modal('show');

			}
			function showModal1() {
				$("#myModal1").modal('show');
			}
			//一般會員讀取
			console.log($("li.table-row.b0"));
			$("li.table-row.b0").each(function() {
				$(this).on("click", function() {

					let id = $(this).find("input.mem_id").val();
					let account = $(this).find("input.mem_account").val()
					let password = $(this).find("input.mem_password").val();
					let lastname = $(this).find("input.mem_lastname").val();
					let firstname = $(this).find("input.mem_firstname").val();
					let phone = $(this).find("input.mem_phone").val();
					let address = $(this).find("input.mem_address").val();
					let email = $(this).find("input.mem_email").val();
					let time = $(this).find("input.mem_addtime").val();
					let verify = $(this).find("input.mem_verify").val();
					// 					console.log($("td.mem_id").text())
					$("td.mem_id").text(id);
					$("td.mem_account").text(account);
					$("td.mem_password").text(password);
					$("td.mem_lastname").text(lastname);
					$("td.mem_firstname").text(firstname);
					$("td.mem_phone").text(phone);
					$("td.mem_address").text(address);
					$("td.mem_email").text(email);
					$("td.mem_addtime").text(time);
					if (verify == 0) {
						$("td.mem_verify").text('未驗證');
					} else {
						$("td.mem_verify").text('已驗證');
					}

					//讀取圖片
					let image = "/APE/MemPic?userNo=" + id;
					$("td.mem_pic").find("img").attr("src", image);
					showModal0();

				})
			})

			//廠商讀取
			$("li.table-row.b1").each(function() {
				$(this).on("click", function() {
					let id = $(this).find("input.cop_id").val();
					let account = $(this).find("input.cop_account").val()
					let password = $(this).find("input.cop_password").val();
					let name = $(this).find("input.cop_name").val();
					let phone = $(this).find("input.cop_phone").val();
					let address = $(this).find("input.cop_address").val();
					let email = $(this).find("input.cop_email").val();
					let time = $(this).find("input.cop_addtime").val();
					let verify = $(this).find("input.cop_verify").val();

					$("td.cop_id").text(id);
					$("td.cop_account").text(account);
					$("td.cop_password").text(password);
					$("td.cop_name").text(name);
					$("td.cop_phone").text(phone);
					$("td.cop_address").text(address);
					$("td.cop_email").text(email);
					$("td.cop_addtime").text(time);
					if (verify == 0) {
						$("td.cop_verify").text('未驗證');
					} else {
						$("td.cop_verify").text('已驗證');
					}

					//讀取圖片
					let image = "/APE/CopPic?companyNo=" + id;
					$("td.cop_pic").find("img").attr("src", image);

					//讀取圖片1
					let image1 = "/APE/CopPic1?companyNo=" + id;
					$("td.cop_pic1").find("img").attr("src", image1);

					showModal1();

				})
			})
		})
	</script>
</body>

</html>