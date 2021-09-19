<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.location.model.*"%>

<jsp:useBean id="listRestaurants_ByLocationno" scope="request" type="java.util.Set<RestaurantVO>" />
<jsp:useBean id="locationSvc" scope="page" class="com.location.model.LocationService" />

<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title>�i���\�U</title>
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
	width: 960px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin: auto
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

body {
	background: #f4eccf;
}

#body {
	position: relative; /*�۹�w��*/
	height: 100%;
	width: 100vw;
	top: 0;
	left: 0;
}

#map {
	position: absolute;
	left: 5%;
	height: 80%; /*�a�ϰ�*/
	width: 90vw;
}

.gm-style .gm-style-iw-c {
	background-color: rgba(233 198 149/ 94%);
}

.gm-style .gm-style-iw-d::-webkit-scrollbar-track, .gm-style .gm-style-iw-d::-webkit-scrollbar-track-piece
	{
	background-color: rgba(233 198 149/ 94%);
}

.gm-style .gm-style-iw-t::after {
	background: linear-gradient(45deg, rgba(233 198 149) 94%,
		rgba(255, 255, 255, 0) 51%, rgba(255, 255, 255, 0) 100%);
}

body {
	background: #f4eccf;
}
</style>
</head>
<body>


	<table id="table-1">
		<tr>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/map/Map.jsp"><img
						src="https://icon-library.com/images/restaurant-icon-png/restaurant-icon-png-21.jpg" width="50" height="50" border="0">�\�U�d��</a>
				</h4>
			</td>
		</tr>
	</table>


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="management_list">
		<table>
			<tr>

				<th>�s��</th>
				<th>�\�U�W��</th>
				<th>����</th>
				<th>�\�U�a�}</th>
				<th>�\�U�q��</th>
				<th>�\�U����</th>
				<th>�i�ϦW��</th>
			</tr>
			<c:forEach var="restaurantVO" items="${listRestaurants_ByLocationno}">
				<tr
					${(restaurantVO.restaurantno==param.restaurantno) ? 'bgcolor=#CCCCFF':''}>
					<!--�N�ק諸���@���[�J����-->
					<td><a
						href="location.do?restaurantno=${restaurantVO.restaurantno}&action=getOne_For_Display">${restaurantVO.restaurantno}</a>
					</td>
					<td>${restaurantVO.restaurantname}</td>
					<td>${restaurantVO.restaurantlevel}</td>
					<td>${restaurantVO.restaurantadd}</td>
					<td>${restaurantVO.restaurantnum}</td>
					<td>${restaurantVO.restauranttype}</td>
					<td><c:forEach var="locationVO" items="${locationSvc.all}">
							<c:if test="${restaurantVO.locationno==locationVO.locationno}">
								�i<font color=orange>${locationVO.locationname}</font>�j
                    		</c:if>
						</c:forEach></td>
				</tr>
				
			</c:forEach>
		</table>
		<%
			if (request.getAttribute("restaurantVO") != null) {
		%><jsp:include page="/map/restaurant/listOneRestaurant.jsp" />
		<%
			}
		%>
		<div class="body">
			<div id="map"></div>
		</div>

		<script>
		<% 
			int i =0;
		%>
			var geocoder;
			var map;
			var i = 0;
			function initMap() {
				geocoder = new google.maps.Geocoder();
				var mapOptions = {
					zoom : 17,//��j�����v
					center : new google.maps.LatLng(25.055, 121.613),//��l�ƪ��a�Ϥ��ߦ�m
					mapTypeId : 'terrain',
					mapTypeControl : false,//�a�ι�&�ìP�Ϥ���
					zoomControl : true, //����Y�񱱨
					scaleControl : false,
					streetViewControl : false,//��
					rotateControl : false,
					fullscreenControl : true,
					styles : [ {
						featureType : "poi.business",
						elementType : "labels",
						stylers : [ {
							visibility : "off"
						} ]
					} ],
				}
				map = new google.maps.Map(document.getElementById('map'),
						mapOptions);
				
				addMarker0();
				addMarker1();
				addMarker2();
				addMarker3();
				addMarker4();
				addMarker5();
				addMarker6();
				map.setCenter('${locationVO.locationname}');
					
		}

			
			<c:forEach var="restaurantVO" items="${listRestaurants_ByLocationno}">
				
				function addMarker<%=i++ %>() {
				geocoder.geocode({'address' : '${restaurantVO.restaurantadd}'},function(results, status) {
					
					
						map.setCenter(results[0].geometry.location);
			
						
						 var marker = new google.maps.Marker({
							map : map,
							position : results[0].geometry.location,
							icon : {
								url : 'https://icon-library.com/images/restaurant-icon-png/restaurant-icon-png-21.jpg',
								scaledSize : new google.maps.Size(50, 50)
								},
								animation : google.maps.Animation.BOUNCE, // DROP���U�ӡBBOUNCE�@���u��
								draggable : true// true�Bfalse�i�_���
						});
						var infowindow = new google.maps.InfoWindow({
							content : '${restaurantVO.restaurantname}<br>�q��: ${restaurantVO.restaurantnum}',
							position : results[0].geometry.location
						});
// 						infowindow.open(map, marker);
						marker.addListener('click',function() {
							infowindow.open(map,marker);
						});
				

				});

			}
				
				</c:forEach>	
</script>


		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhNp82kn510sPABlVdrzn_Veryi5aVqag&callback=initMap&libraries=&v=weekly"
			async></script>
</body>
</html>