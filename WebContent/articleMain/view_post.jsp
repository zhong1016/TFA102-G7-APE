<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.article.model.AmessageVO"%>
<%@page import="com.article.model.AmessageDAO"%>
<%@page import="com.mem.model.MemDAO"%>
<%@page import="com.mem.model.MemVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	AmessageDAO dao = new AmessageDAO(); //留言物件
	AmessageVO avo = new AmessageVO(); //留言VO
	Integer No = (Integer) request.getAttribute("No");
	List<AmessageVO> list1 = new ArrayList(); //list
	list1 = dao.getAllByNo(No);
	pageContext.setAttribute("list1", list1);

	if (session.getAttribute("memVO") == null) {
%>
<script>
	window.onload = function() {
		document.getElementsByClassName('rpt').disabled = true;
		document.getElementsByClassName('report_btn').disabled = true;
		document.getElementsByClassName('push').disabled = false;
		console.log("這邊有執行到")
	}
</script>
<%
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/article/view_post 0801.css">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<title>瀏覽畫面</title>
</head>
<body>

	<%@ include file="/header.file"%>

	<section id="main_section">
		<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
		<div class="type_author">
			<div class="type">文章類別:${articleVO.ARTICLE_CLASS}</div>
			<div class="author">
				作者:
				<c:forEach var="memVO" items="${memSvc.all}">

					<c:if test="${memVO.userNo == articleVO.getUSER_NO()}">
                  		${memVO.userfName}
            
                  		</c:if>


				</c:forEach>
			</div>
			<div class="time">2021</div>

			<button class="push">推推</button>
			<button class="report_btn">檢舉</button>

		</div>
		<div id="main" class="main">文章主題:${articleVO.ARTICLE_MAIN}</div>

		<div class="main_article"
			style="padding: 0 15px; color: darkslategray;">${articleVO.ARTICLE}</div>

		<div id="img_article">
			<a href="https://24h.pchome.com.tw/" target="_blank"><img
				src="<%=request.getContextPath()%>/img/ArticleIMG/文宣3.png" alt="Abc"
				class="img_1_article"></a> <a href="https://www.momoshop.com.tw/"
				target="_blank"><img
				src="<%=request.getContextPath()%>/img/ArticleIMG/文宣4.png" alt="Abc"
				class="img_2_article"></a>
		</div>

		<div id="zoom">
			<iframe class="ccc"
				src="http://localhost:8081/APE/articleMain/index.html" width="100%"
				height="100%"></iframe>
		</div>


	</section>

	<div id="msg_div">
		<div id="msg_div_ch">
			<input type="text" name="msg" id="msg_input" placeholder="請輸入留言...">
			<button type="submit" id="msgbtn">送出</button>
		</div>

		<form style="display: inline;" action="<%=request.getContextPath()%>/amessageAPI">
			<%-- 		action="<%=request.getContextPath()%>/amessageAPI" --%>
			<input id="subinput" TYPE="hidden" NAME="articleNo"
				value="${articleVO.ARTICLE_NO }"></input>
		</form>
		
		
		
		
		
		
<%-- 		<% --%>

<%-- 		%> --%>
		
		
<jsp:useBean id="memSv" scope="page" class="com.mem.model.MemService" />
<c:forEach var="vo" items="${list1}">
		<div class="msg_ch">

			<div class="main_msg">
				<div class="msg_author">
					<p>
					
					<c:forEach var="hmemVO" items="${memSv.all}">

						<c:if test="${hmemVO.userNo == vo.getUSER_NO()}">
                  					${hmemVO.userfName}
            
                  		</c:if>
					</c:forEach>
					
					
					
					</p>
				</div>
				<div id="msgno" style="display:none;">${vo.getMSG_NO()}</div>
				<div class="author_msg">
					<p>${vo.getMSG()}</p>
				</div>
				<div class="msg_time">
					<p style="font-size: 8px">${vo.getMSG_TM()}</p>
				</div>
			</div>

			<div class="report_div">
			<form  id="rptform"action="<%=request.getContextPath()%>/reportControl" method="GET">
				<button type="submit" id="report" class="rpt">檢舉</button>
				<INPUT style="display:none;" TYPE="hidden" NAME="rptno" value="${vo.getMSG_NO()}"></INPUT>
			</form>
			</div>

		</div>
		</c:forEach>
<%-- 		<% --%>
		
<%-- 		%> --%>
		
		
		
		
		
		
		
		

	</div>


	<%@ include file="/footer.file"%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/view_post.js"></script>
	<script>
		document.querySelector('.ccc').addEventListener('click', function() {
			alert("加入聊天室~")
			window.open("http://localhost:8081/APE/articleMain/index.html")
		})
	</script>



</body>
</html>