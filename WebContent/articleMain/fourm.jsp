<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.article.model.ArticleVO"%>
<%@page import="com.article.model.ArticleDAO"%>
<%@page import="com.mem.model.MemDAO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArticleDAO dao = new ArticleDAO();
	List<ArticleVO> list = new ArrayList();
	// ArticleVO avo = new ArticleVO();
	list = dao.getAll();
	pageContext.setAttribute("list", list);

	ArticleDAO hdao = new ArticleDAO();
	List<ArticleVO> hlist = new ArrayList(); //熱門文章
	hlist = hdao.getAllByCount();
	pageContext.setAttribute("hlist", hlist);
	MemVO mvo = new MemVO();
	MemDAO mdao = new MemDAO();

	ArticleDAO Adao = new ArticleDAO();
	List<ArticleVO> Alist = new ArrayList(); //分區文章
	String area = request.getParameter("area");
	Alist = Adao.getAllByArea(area);
	ArticleVO aaavo = new ArticleVO();
	session.setAttribute("k1", "jjj");

	Object nnn = session.getAttribute("k1");
%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>艾普藝森-討論區</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/article/index.css" />
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
</head>
<body>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<%@ include file="/header.file"%>

	<section id="hotTopic">
		<div id="aside">
			<br> 今<br> <br> 日<br> <br> 熱<br> <br>
			門<br>
		</div>
		<div id="table_div">
			<table id="hotArticle">
				<tr>

					<th class="top_sty"><b>類別</b></th>
					<th class="top_topic"><b>主題</b></th>
					<th class="top_num"><b>推推</b></th>
					<th class="top_author"><b>發文者</b></th>
					<th class="top_time"><b>時間</b></th>
				</tr>

				<jsp:useBean id="memSvc" scope="page"
					class="com.mem.model.MemService" />
				<c:forEach var="havo" items="${hlist}">
					<tr class="test">
						<td class="hot_sty">
							<P class="hot_a">${havo.getARTICLE_CLASS()}</p>
						</td>
						<td class="hot_topic">
							<p class="hot_a">${havo.getARTICLE()}</p>
						</td>
						<td class="hot_num">
							<p href="#" class="hot_a">${havo.getCOUNT()}</p>
						</td>
						<td class="hot_author">
							<p href="#" class="hot_a">

								<c:forEach var="hmemVO" items="${memSvc.all}">

									<c:if test="${hmemVO.userNo == havo.getUSER_NO()}">
                  					${hmemVO.userfName}
            
                  		</c:if>
								</c:forEach>
							</p>
						</td>
						<td class="hot_time"><p href="#" class="hot_a">${havo.getARTICLE_TIME()}</p></td>
						<form action="<%=request.getContextPath()%>/control" method="GET">
							<INPUT TYPE="hidden" NAME="articleNo"
								value="${havo.getARTICLE_NO()}"></INPUT>
						</form>
					</tr>
				</c:forEach>


			</table>
		</div>
		<div id="img_div">
			<a href="https://tw.yahoo.com/" target="_blank"><img
				src="<%=request.getContextPath()%>/img/ArticleIMG/廠商文宣示範.png"
				alt="adv." id="client_img"></a> <a href="https://www.youtube.com/"
				target="_blank"><img
				src="<%=request.getContextPath()%>/img/ArticleIMG/文宣4.png"
				alt="adv." id="client_img_1"></a>
		</div>
	</section>

	<main>
		<div class="area_btn">

			<button type="button">
				<a href="#" class="for"><span class="bas">大台北地區</span></a>
			</button>


			<button class="tfabtn" type="button">
				<a href="#" class="for"><span class="bas">桃、竹、苗</span></a>
			</button>
			<form class="btnform" style="display: inline-block; width: 0"
				action="<%=request.getContextPath()%>/AreaControl" method="GET">
				<INPUT class="tao" TYPE="hidden" NAME="area" value="tao"></INPUT>
			</form>

			<button type="button">
				<a href="#" class="for"><span class="bas">中部生活圈</span></a>
			</button>
			<button type="button">
				<a href="#" class="for"><span class="bas">雲、嘉、南</span></a>
			</button>
			<button type="button">
				<a href="#" class="for"><span class="bas">大高屏地區</span></a>
			</button>
			<button type="button">
				<a href="#" class="for"><span class="bas">東部/外島</span></a>
			</button>
			<button type="button" class="article_post">
				<a href="http://localhost:8081/APE/articleMain/article.jsp"
					target="_blank" id="post"><span class="basPost">我要發文!</span></a>
			</button>
		</div>
		<div class="main_content">
			<div class="main_article">
				<table id="ttt">
					<tr id="all1">
						<th class="sty" id="sty_id"><b> 類別</b></th>
						<th class="topic" id="topic_id"><b>主題</b></th>
						<th class="num" id="num_id"><b>推推</b></th>
						<th class="author" id="author_id"><b>發文者</b></th>
						<th class="time" id="time_id"><b>時間</b></th>
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
							<td class="num tran"><p class="a_link">${avo.COUNT}</p></td>
							<td class="author tran" style="display: none"><p
									class="a_link">${avo.ARTICLE_NO}</p></td>
							<%--                     <td class="author tran"><p class="a_link">${avo.USER_NO}</p></td> --%>
							<td class="author tran"><p class="a_link">

									<c:forEach var="memVO" items="${memSvc.all}">

										<c:if test="${memVO.userNo == avo.getUSER_NO()}">
                  		${memVO.userfName}
            
                  		</c:if>


									</c:forEach>




									<%--                     ${mdao.findByUesr(${avo.USER_NO})} --%>

									<%--                     	<c:if test="(${avo.USER_NO})!=null" > --%>
									<%--                     		(mdao.findByUesr(${avo.USER_NO})).getUserfName() --%>
									<%--                     	</c:if> --%>
								</p></td>
							<td class="time tran"><p class="a_link">${avo.ARTICLE_TIME}</p></td>
							<form action="<%=request.getContextPath()%>/control" method="GET">
								<INPUT TYPE="hidden" NAME="articleNo" value="${avo.ARTICLE_NO}"></INPUT>
							</form>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="page2.file"%>

			</div>


			<div class="main_asside">
				<img src="<%=request.getContextPath()%>/img/ArticleIMG/pc1.png"
					class="pc_img" alt="1"> <img
					src="<%=request.getContextPath()%>/img/ArticleIMG/pc2.jpg"
					class="pc_img" alt="1"> <img
					src="<%=request.getContextPath()%>/img/ArticleIMG/pc3.jpg"
					class="pc_img" alt="1">
				<div class="chat">
					<iframe class="ccc"
						src="http://localhost:8081/APE/articleMain/index.html"
						width="100%" height="100%"></iframe>
				</div>
			</div>
		</div>


	</main>
	<%@ include file="/footer.file"%>
	<script src="<%=request.getContextPath()%>/js/header.js"></script>

	<script src="<%=request.getContextPath()%>/js/articleJS/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/test.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/fourmAPI.js"></script>


</body>

</html>
