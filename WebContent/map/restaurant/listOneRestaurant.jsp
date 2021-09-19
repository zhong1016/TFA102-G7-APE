<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.location.model.*"%>

<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restaurantVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>
<%
  	LocationService locationSvc = new LocationService();
	LocationVO locationVO = locationSvc.getOneLocation(restaurantVO.getLocationno());
%>

<html>
<head>
<title>�\�U��� - listOneRestaurant.jsp</title>

<style>
table#table-1 {
	background-color: #8fbc8f7a;
	
	text-align: center;
	border-radius: 5px;
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
	margin: auto;
	border-radius: 5px;
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
	top: 20%; /*�a�Ϧ�������3/4�A�Y ������ - �a�ϰ� = �w���I���� (100%-75%=25%) */
	left: 5%;
	height: 80%; /*�a�ϰ�*/
	width: 90vw;
}
.gm-style .gm-style-iw-c{
	background-color: rgba(233 198 149 / 94%);
}
.gm-style .gm-style-iw-d::-webkit-scrollbar-track, .gm-style .gm-style-iw-d::-webkit-scrollbar-track-piece {
	background-color: rgba(233 198 149 / 94%);
}
.gm-style .gm-style-iw-t::after{
	background: linear-gradient(45deg,rgba(233 198 149 ) 94%,rgba(255,255,255,0) 51%,rgba(255,255,255,0) 100%);
}

body {
	background: #f4eccf;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h4>
					<a href="<%=request.getContextPath()%>/map/Map.jsp"><img src="https://icon-library.com/images/restaurant-icon-png/restaurant-icon-png-21.jpg"
						width="50" height="50" border="0">�\�U�d��</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�s��</th>
			<th>�\�U�W��</th>
			<th>����</th>
			<th>�\�U�a�}</th>
			<th>�\�U�q��</th>
			<th>�\�U����</th>
			<th>�i�ϦW��</th>
			<th>�\�U�Ӥ�</th>
		</tr>
		<tr>
			<td><%=restaurantVO.getRestaurantno()%></td>
			<td><%=restaurantVO.getRestaurantname()%></td>
			<td><%=restaurantVO.getRestaurantlevel()%></td>
			<td><%=restaurantVO.getRestaurantadd()%></td>
			<td><%=restaurantVO.getRestaurantnum()%></td>
			<td><%=restaurantVO.getRestauranttype()%></td>
			<td>�i<%=locationVO.getLocationname()%>�j</td>
			<td><a href="<%=request.getContextPath()%>/map/restaurant/restaurantPhoto.jsp">�Ӥ�</td>
		</tr>
	</table>


	<div class="body">
		<div id="map"></div>
	</div>

	<script>
		var geocoder;
		var map;
		function initMap() {
			 // ���J���u�A�ȻP���u��ܹϼh
		    var directionsService = new google.maps.DirectionsService();
		    var directionsDisplay = new google.maps.DirectionsRenderer({
		    	   map: map,
		    	   polylineOptions: {
		    	     strokeColor: 'red',
		    	     strokeWeight: 5
		    	   },
		    		suppressMarkers: true
		    	});
		    
			geocoder = new google.maps.Geocoder();
			var latlng = new google.maps.LatLng(25.055, 121.613);
			var mapOptions = {
				zoom : 17,//��j�����v
				center : latlng,//��l�ƪ��a�Ϥ��ߦ�m
				mapTypeId : 'terrain',
				mapTypeControl : false,
				zoomControl : true,
				scaleControl : false,
				streetViewControl : false,
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
			
			map = new google.maps.Map(document.getElementById('map'),mapOptions);
			// ��m���u�ϼh
		    directionsDisplay.setMap(map);
			var address='<%=restaurantVO.getRestaurantadd()%>';
			geocoder.geocode({'address' : address},function(results, status) {
				if (status == 'OK') {
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location,
					icon : {
						url : 'https://icon-library.com/images/restaurant-icon-png/restaurant-icon-png-21.jpg',
						scaledSize : new google.maps.Size(60, 60)
					},
					animation : google.maps.Animation.BOUNCE, // DROP���U�ӡBBOUNCE�@���u��
					draggable : false// true�Bfalse�i�_���
				});
				var infowindow = new google.maps.InfoWindow(
					{
						content : '<h1><%=restaurantVO.getRestaurantname()%> <br>�q�� : <%=restaurantVO.getRestaurantnum()%></h1>',
						position : results[0].geometry.location
					});
				infowindow.open(map, marker);

				marker.addListener('click', function() {
					infowindow.open(map, marker);
					window.location.replace('/APE/map/restaurant/review.jsp');
					
				});
			} else {
				console.log(status);
			}
		});
			if (navigator.geolocation) {
	            navigator.geolocation.getCurrentPosition(function(position) {
	                var pos = {
	                    lat: position.coords.latitude,
	                    lng: position.coords.longitude
	                };
	                var marker = new google.maps.Marker({
	                    position: pos,
	                    map: map
	                });
	                map.setCenter(pos);
	                var contentString='�ثe��m';
	        	    var infowindow = new google.maps.InfoWindow(
	        				{
	        					content : contentString,
	        					position : pos,
	        					maxWidth:400 ,
	        					
	        				});
	        		infowindow.open(map, marker);
	        		 // ���u�����]�w
	    		    var request = {
	    		        origin: pos,//<���n> �_�l��m
	    		        destination: address,//<���n> ���I��m
	    		        travelMode: 'DRIVING',//<���n> ���ʤ覡�A�� DRIVING�BBICYCLING�BTRANSIT�B WALKING�A�w�]�� DRIVING
	    		        avoidFerries:true,//��������A�i�]�w true �� false�C
	    		        avoidHighways:true,//�������t�����A�i�]�w true �� false�C
	    		        avoidTolls:true,//�������O�����A�i�]�w true �� false�C
	    		        
	    		        
	    		    };

	    		    // ø�s���u
	    		    directionsService.route(request, function (result, status) {
	    		        if (status == 'OK') {
	    		            // �^�Ǹ��u�W�C�ӨB�J���Ӹ`
	    		            console.log(result.routes[0].legs[0].steps);
	    		            directionsDisplay.setDirections(result);
	    		            
	    		        } else {
	    		            console.log(status);
	    		        }
	    		    });
	            });
	        } else {
	            // Browser doesn't support Geolocation
	            alert("�����\�ξD�J���~�I");
	        }
			
	}
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhNp82kn510sPABlVdrzn_Veryi5aVqag&callback=initMap&libraries=&v=weekly"
		async></script>


</body>
</html>