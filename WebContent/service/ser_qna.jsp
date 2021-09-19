<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ser.model.*"%>
<%
    SerService serSvc = new SerService();
    List<SerQAVO> list = serSvc.getAllQA();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/serAll.css">
	<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<p class="serleaf"></p>
	<section>
		<ul class="list">
			<c:forEach var="serQAVO" items="${list}" >
				<li><a class="link_title" href="#">
						<button type="button" class="switch_btn">
							<span class="-plus">+</span><span class="-minus">-</span>
						</button> ${serQAVO.serQus} 
				</a>
					<div class="inner_block">${serQAVO.serAns}</div></li>
			</c:forEach>
		</ul>
	</section>
	<p class="serleaf"></p>

	<script src="<%=request.getContextPath()%>/js/service/serqna.js"></script>
</body>

</html>
