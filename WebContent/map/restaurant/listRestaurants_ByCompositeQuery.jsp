<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listRestaurants_ByCompositeQuery" scope="request" type="java.util.List<RestaurantVO>" />
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />


<html>
<head><title>複合查詢 - listRestaurants_ByCompositeQuery.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>
☆萬用複合查詢  - 可由客戶端 select_page.jsp 隨意增減任何想查詢的欄位<br>
☆此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllRestaurant.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>餐廳編號</th>
		<th>餐廳名稱</th>
		<th>評價</th>
		<th>地址</th>
		<th>電話</th>
		<th>種類</th>
		<th>地區</th>
	</tr>
	<c:forEach var="restaurantVO" items="${listRestaurants_ByCompositeQuery}">
		<tr align='center' valign='middle'>
			<td>${restaurantVO.restaurantno}</td>
			<td>${restaurantVO.restaurantname}</td>
			<td>${restaurantVO.restaurantlevel}</td>
			<td>${restaurantVO.restaurantadd}</td>
			<td>${restaurantVO.restaurantnum}</td>
			<td>${restaurantVO.restauranttype}</td>			
			<td><c:forEach var="locationVO" items="${locationSvc.all}">
                    <c:if test="${restaurantVO.locationno==locationVO.locationno}">
	                    ${locationVO.locationno}【${locationVO.locationname} - ${locationVO.locationadd}】
                    </c:if>
                </c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>