<%@page import="com.mysql.cj.jdbc.Blob"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.article.model.ArticleVO"%>
<%@page import="com.article.model.ArticleDAO"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.mem.model.MemDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	ArticleDAO dao = new ArticleDAO();
	List<ArticleVO> list = new ArrayList();
	// ArticleVO avo = new ArticleVO();
	if(session.getAttribute("memVO")==null){
	%>
	<script>
	alert("非會員無法查看，請事先登入")
	window.location.replace("http://localhost:8081/APE/members/Member.jsp");
	</script>
	<%
	return;
}
	
	String member;
	MemVO mvo = new MemVO();
	mvo = (MemVO)session.getAttribute("memVO");
	Integer mvoname = mvo.getUserNo();
	
	list = dao.getAllByUSERNO(mvoname);
	pageContext.setAttribute("list", list);

	ArticleDAO hdao = new ArticleDAO();
	List<ArticleVO> hlist = new ArrayList(); //熱門文章
	hlist = hdao.getAllByCount();

	ArticleDAO Adao = new ArticleDAO();
	List<ArticleVO> Alist = new ArrayList(); //分區文章
	String area = request.getParameter("area");
	Alist = Adao.getAllByArea(area);
	
	String name = mvo.getUserlName();
	pageContext.setAttribute("name", name);
	
	byte[] pic = mvo.getUserPic();
	pageContext.setAttribute("pic", pic);
%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>艾普藝森 會員前台</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/article/indexa.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/article/backenda.css">
</head>

<body>
	<%@ include file="/header.file"%>
	<div class="warp">
		<button type="button" class="btn" id="goPre"></button>
		<button type="button" class="btn" id="goNext"></button>
	</div>



	<!-- 前台面板 -->
	<div class="body-content">

		<!-- 面板列 -->
		<div class="body_content_list">
			<div class="name">
				<img
					src="<%=request.getContextPath()%>/memberPic"
					alt="">
				<p>${name}</p>
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

			<table id="ttt">
				<tr id="all1">
					<th class="sty" id="sty_id"><b> 類別</b></th>
					<th class="topic" id="topic_id"><b>主題</b></th>
					<th class="num" id="num_id"><b>回應</b></th>
					<th class="ori"><b>原文</b></th>
					<th class="delete"><b>刪除</b></th>

				</tr>



				<%@ include file="page1.file"%>
				<c:forEach var="avo" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<%--                 <% for(ArticleVO avo : list ) { %>          --%>
					<tr class="test">
						<td class="sty tran">
							<p class="a_link">${avo.ARTICLE_CLASS}</p>
						</td>
						<td class="topic tran">
							<p class="a_link">${avo.ARTICLE}</p>
						</td>
						<td class="author tran" style="display: none"><p
								class="a_link">${avo.ARTICLE_NO}</p></td>
						<td class="author tran"><p class="a_link">${avo.COUNT }</p></td>
						<form action="<%=request.getContextPath()%>/control" method="GET">
						<td class="author tran" id="orignal"><button type="submit">
								原文</button></td>

						<INPUT TYPE="hidden" NAME="articleNo" value="${avo.ARTICLE_NO}"></INPUT>
						</form>
						<form action="<%=request.getContextPath()%>/frontControl"
							method="GET">
						<td class="author tran" id="del"><button type="submit">刪除
							</button></td>

						<INPUT TYPE="hidden" NAME="articleNo" value="${avo.ARTICLE_NO}"></INPUT>
						</form>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>





		</div>
	</div>
	
	

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/main/js/articleJS/indexa.js"></script>
	<script
		src="<%=request.getContextPath()%>/main/js/articleJS/backenda.js"></script>
</body>
</html>