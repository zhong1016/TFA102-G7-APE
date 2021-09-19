<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cop.model.*"%>
<%@ page import="java.util.*"%>

<%
	if (session.getAttribute("memVO") == null && session.getAttribute("copVO") == null ) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/members/Member.jsp");
		dispatcher.forward(request, response);
		return;
	}else if(session.getAttribute("memVO") != null){
		RequestDispatcher dispatcher = request.getRequestDispatcher("/members/MemDtal.jsp");
		dispatcher.forward(request, response);
		return;
	}
	CopVO copVO = (CopVO) request.getSession().getAttribute("copVO"); //MemServlet.java(Concroller)
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>艾普藝森</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
	<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet" />
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/article/backenda.css">
	<link href="<%=request.getContextPath()%>/css/member/memdtal.css" rel="stylesheet">
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
	
</head>

<body>
	<%@ include file="/header.file" %>
	<div class="warp">
		<button type="button" class="btn" id="goPre"></button>
		<button type="button" class="btn" id="goNext"></button>
	</div>



	<!-- 前台面板 -->
	<div class="body-content">

		<!-- 面板列 -->
		<div class="body_content_list">
			<div class="name">
				<img id="memInputImg"
					src="<%=request.getContextPath()%>/copPicReader?companyNo=<%=copVO.getCompanyNo()%>&PIC=COMPANY_PIC"
					alt="">
				<p>${copVO.companyName}</p>
			</div>

			<!--會員資料 -->
			<div class="body_member_list">
				<img
					src="<%=request.getContextPath()%>/img/ArticleIMG/people.svg"
					alt=""> <a href="#">會員資料</a>
			</div>
			<!-- 買家專區 -->
			<div class="body_buyer_list">
				<img
					src="<%=request.getContextPath()%>/img/ArticleIMG/cart.svg"
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
					src="<%=request.getContextPath()%>/img/ArticleIMG/shop.svg"
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
					src="<%=request.getContextPath()%>/img/ArticleIMG/stall.svg"
					alt=""> <a href="#">攤位管理</a>
			</div>
			<!-- 我的票券 -->
			<div class="body_ticket_list">
				<img
					src="<%=request.getContextPath()%>/img/ArticleIMG/ticket.svg"
					alt=""> <a href="#">我的票券</a>
			</div>


			<!--文章管理 -->
			<div class="body_article_list">
				<img
					src="<%=request.getContextPath()%>/img/ArticleIMG/list.svg"
					alt=""> <a href="#">文章管理</a>
			</div>
		</div>


		<!-- 面板內容 -->
		<div class="body_content_data">

			<c:if test="${not empty errorMsgs}">
		<div>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty errorMsgs}">
		<div>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<article>
		<section class="Mer_bg">
			<section class="mem_range">
<%-- 				<img src="<%=request.getContextPath()%>/img/member/memFlower.png" alt="SER_FLW" class="service_rflower"> --%>
<!-- 				<span class="service_but"></span> -->
				<form action="CopServlet" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="coploginid" value="update">
<!-- 					<section class="memdtal"> -->
<!-- 						<section class="Data-Items"> -->
<!-- 							<label class="input-label"> -->
<%-- <%-- 								<input id="memInputImg" class="memimg" type="image" disabled src="<%=request.getContextPath()%>/copPicReader?companyNo=<%=copVO.getCompanyNo()%>&PIC=COMPANY_PIC" placeholder="大頭貼"> --%> --%>
<!-- <!-- 								 --> -->
								
<!-- 							</label> -->
<!-- 						</section> -->
<!-- 					</section> -->
					<section class="memdtal">
						<section class="Data-Items">
							<input class="companyNo" type="text" value="${copVO.companyNo}" disabled placeholder="會員編號" /><br />
							<input class="email locked" type="email" name="companyEmail" value="${copVO.companyEmail}" disabled placeholder="Email Address 電子信箱" /><br />
							<input class="locked" type="text" name="companyName" value="${copVO.companyName}" disabled placeholder="Family Name 姓氏" /><br />
							<input class="" type="text" value="${copVO.companyId}" disabled placeholder="ID 身分證字號" /><br /> 
							
							<select name="companyAddressCity" class="city memarrd locked" >
								<option value="${copVO.companyAddressCity}">${copVO.companyAddressCity}</option>
							</select> 
							<select name="companyAddressDistrict" class="district memarrd locked" >
								<option value="${copVO.companyAddressDistrict}">${copVO.companyAddressDistrict}</option>
							</select> 
							<input class="locked" name="companyAddress" type="text" value="${copVO.companyAddress}" disabled placeholder="Address 地址" /><br />
							<input type="file" class="file locked" accept="image/*,.pdf" name="companyPic" disabled>
							<input type="file" class="copBRC locked" accept="image/*,.pdf" name="companyBRC" disabled>
							
						</section>
					</section>
					<section class="memdtal ">
						<section class="Data-Items ">
							<input class="" type="text" value="${copVO.companyId}" disabled placeholder="Company Name 會員帳號" /><br /> 
							<input class="locked password" type="password" value="${copVO.companyPassword}" disabled placeholder="Pass Word 會員密碼" /><br /> 
							<input class="locked" type="phone" name="companyPhone" value="${copVO.companyPhone}" disabled placeholder="Telephone 手機" /><br /> 					
							<input class="" type="text" value="${copVO.createTime}" disabled placeholder="更新時間" /> <br />
							<input class="" type="text" value="${copVO.status}" disabled placeholder="Gender 性別" /><br /> 
							<img id="copInputImg" class="memimg" type="image" disabled src="<%=request.getContextPath()%>/copPicReader?companyNo=<%=copVO.getCompanyNo()%>&PIC=COMPANY_BRC" placeholder="GUI" /><br /> 
							<p>
								前往 <a href="#">會員中心</a>
							</p>
							<p>
								<label for=""> 
									<input class="btnSubmit" type="submit" value="儲存"> 
									<input type="hidden" name="coploginid" value="update">
								</label>
							</p>
							<p>
								<button type="button" class="btnNew">修改</button>
							</p>
						</section>
					</section>
				</form>
					<form action="CopServlet" method="POST" enctype="multipart/form-data">
						<div class="memPswdChange" Style="display: none">
							<article>
								<input class="mempswd locked" type="password" name="companyPassword" disabled placeholder="Pass Word 會員密碼" /><br />
								<label for="">請輸入新密碼:
									<input class="btnSubmit" type="submit" value="儲存"> 
									<input type="hidden" name="coploginid" value="changePassword">
									<button type="button" class="btn_modal_close btnNew">關閉</button>
								</label>
							</article>
						</div>
					</form>
                </div>
			</section>
		</section>
	</article>

		</div>
	</div>
	
	<%@ include file="/footer.file" %>
	
	<script src="<%=request.getContextPath()%>/main/js/articleJS/backenda.js"></script>
	<script src="<%=request.getContextPath()%>/js/member/memdtal.js"></script>
</body>

