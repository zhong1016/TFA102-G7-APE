<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.cop.model.CopVO"%>

<%
	if (session.getAttribute("copVO") == null) {
		session.setAttribute("location", request.getRequestURI());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/members/Member.jsp");
		dispatcher.forward(request, response);
		return;
	}
	CopVO copVO = (CopVO) session.getAttribute("copVO");
	Integer companyNo = copVO.getCompanyNo();
	
	pageContext.setAttribute("companyNo", companyNo);
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>廠商審核總頁</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/header.css">
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" /> --%>
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/backend.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/activity-css/pageCss/backendvendorcheack1.css">

</head>

<body>
	<%@ include file="/header.file"%>

	<!-- 	<div class="warp"> -->
	<!-- 		<button type="button" class="btn" id="goPre"></button> -->
	<!-- 		<button type="button" class="btn" id="goNext"></button> -->
	<!-- 	</div> -->



	<!-- 前台面板 -->
	<div class="body-content">

		<!-- 面板列 -->
		<div class="body_content_list">
			<div class="name">
				<img
					src="<%=request.getContextPath()%>/CopPic1?companyNo=${companyNo}"
					alt="">
				<p>黃澄澄</p>
			</div>

			<!--會員資料 -->
			<div class="body_member_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/people.svg"
					alt=""> <a href="#">會員資料</a>
			</div>
			<!-- 買家專區 -->
			<div class="body_buyer_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/cart.svg"
					alt=""> <a href="#" class="buyer_click">買家專區</a>
			</div>
			<div class="body_buyer_list_all on">
				<ul>
					<li><a href="">訂單查詢</a></li>
					<li><a href="">商品追蹤清單</a></li>
				</ul>
			</div>
			<!--賣家專區  -->
			<div class="body_seller_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/shop.svg"
					alt=""> <a href="#" class="seller_click">賣家專區</a>
			</div>
			<div class="body_seller_list_all on">
				<ul>
					<li><a href="#">訂單查詢</a></li>
					<li><a href="#">商品管理</a></li>
					<li><a href="#">商品上架</a></li>
				</ul>
			</div>

			<!-- 攤位管理 -->
			<div class="body_stall_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/stall.svg"
					alt=""> <a href="<%=request.getContextPath()%>/activity/vendorcheackpageall.jsp">攤位管理</a>
			</div>
			<!-- 我的票券 -->
			<div class="body_ticket_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/ticket.svg"
					alt=""> <a href="#">我的票券</a>
			</div>


			<!--文章管理 -->
			<div class="body_article_list">
				<img
					src="<%=request.getContextPath()%>/img/activity-img/imgBackend/list.svg"
					alt=""> <a href="#">文章管理</a>
			</div>


		</div>


		<!-- 面板內容 -->
		<div class="body_content_data">
			<div class="container">
				<h2>攤位管理</h2>
				<select id="myInput">
					<option>待審核</option>
					<option>通過</option>
					<option>未通過</option>
				</select>
				<table id="myTable">
					<tr class="table-header">
						<th class="header">活動名稱</th>
						<th class="header">活動日期</th>
						<th class="header">攤位名稱</th>
						<th class="header">攤位簡介</th>
						<th class="header">攤位圖片</th>
						<th class="header">審核</th>
					</tr>
					<c:forEach var="rentVO" items="${rentVOList}">
						<tr class="table-row">
							<td class="col col-1" data-label="ACTIVITY_NAME">${activityVO.activityName}</td>
							<td class="col col-2" data-label="ACTIVITY_DATE">${activityVO.startDate}<p>&nbsp;</p>${activityVO.closeDate}</td>
							<td class="col col-3" data-label="RENT_NAME">${rentVO.rentName}</td>
							<td class="col col-4" data-label="RENT_INTRODUCTION">${rentVO.rentIntroduction}</td>
							<td class="col col-5" data-label="RENT_PIC"><img
								src="<%=request.getContextPath()%>/rent/RentPic?rentNo=${rentVO.rentNo}">
							</td>
							<td class="col col-6" data-label="STATUS">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/rent/RentServlet.do">
									<input type="hidden" name="rentNo" value="${rentVO.rentNo}">
									<input type="hidden" name="activityNo"
										value="${activityVO.activityNo}"> <input type="hidden"
										name="action" value="get_rent_deteail">
									<button class="STATUS" type="submit">
										<c:forEach var="rentEntry" items="${rentMap}">
											<c:if test="${rentVO.status == rentEntry.key}">${rentEntry.value}</c:if>
										</c:forEach>
									</button>
								</FORM>
							</td>

						</tr>
					</c:forEach>
				</table>
			</div>
		</div>

	</div>
	<%@ include file="/footer.file"%>

	<script src="<%=request.getContextPath()%>/js/header.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<%-- 	<script src="<%=request.getContextPath()%>/js/index.js"></script> --%>
	<script src="<%=request.getContextPath()%>/js/backend.js"></script>


	<script>
		$(function() {

			$("#myInput").on("change", function() {

				var input, filter, table, tr, td, i, txtValue;
				input = document.getElementById("myInput");
				filter = input.value;
				table = document.getElementById("myTable");
				tr = table.getElementsByTagName("tr");
				for (i = 0; i < tr.length; i++) {
					td = tr[i].getElementsByTagName("td")[5];
					if (td) {
						txtValue = td.innerText.trim();
						if (txtValue === filter) {
							tr[i].style.display = "";
						} else {
							tr[i].style.display = "none";
						}
					}
				}
			});

		});
	</script>
</body>

</html>