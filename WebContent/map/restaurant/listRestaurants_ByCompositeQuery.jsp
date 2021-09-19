<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listRestaurants_ByCompositeQuery" scope="request" type="java.util.List<RestaurantVO>" />
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />


<html>
<head><title>�ƦX�d�� - listRestaurants_ByCompositeQuery.jsp</title>

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
���U�νƦX�d��  - �i�ѫȤ�� select_page.jsp �H�N�W�����Q�d�ߪ����<br>
�������u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllRestaurant.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>�\�U�s��</th>
		<th>�\�U�W��</th>
		<th>����</th>
		<th>�a�}</th>
		<th>�q��</th>
		<th>����</th>
		<th>�a��</th>
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
	                    ${locationVO.locationno}�i${locationVO.locationname} - ${locationVO.locationadd}�j
                    </c:if>
                </c:forEach>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>