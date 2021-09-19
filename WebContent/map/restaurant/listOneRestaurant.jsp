<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.restaurant.model.*"%>
<%@ page import="com.location.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	RestaurantVO restaurantVO = (RestaurantVO) request.getAttribute("restaurantVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%
  	LocationService locationSvc = new LocationService();
	LocationVO locationVO = locationSvc.getOneLocation(restaurantVO.getLocationno());
%>

<html>
<head>
<title>餐廳資料 - listOneRestaurant.jsp</title>

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
	position: relative; /*相對定位*/
	height: 100%;
	width: 100vw;
	top: 0;
	left: 0;
}

#map {
	position: absolute;
	top: 20%; /*地圖佔頁面的3/4，即 頁面高 - 地圖高 = 定位點高度 (100%-75%=25%) */
	left: 5%;
	height: 80%; /*地圖高*/
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
						width="50" height="50" border="0">餐廳查詢</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>編號</th>
			<th>餐廳名稱</th>
			<th>評價</th>
			<th>餐廳地址</th>
			<th>餐廳電話</th>
			<th>餐廳種類</th>
			<th>展區名稱</th>
			<th>餐廳照片</th>
		</tr>
		<tr>
			<td><%=restaurantVO.getRestaurantno()%></td>
			<td><%=restaurantVO.getRestaurantname()%></td>
			<td><%=restaurantVO.getRestaurantlevel()%></td>
			<td><%=restaurantVO.getRestaurantadd()%></td>
			<td><%=restaurantVO.getRestaurantnum()%></td>
			<td><%=restaurantVO.getRestauranttype()%></td>
			<td>【<%=locationVO.getLocationname()%>】</td>
			<td><a href="<%=request.getContextPath()%>/map/restaurant/restaurantPhoto.jsp">照片</td>
		</tr>
	</table>


	<div class="body">
		<div id="map"></div>
	</div>

	<script>
		var geocoder;
		var map;
		function initMap() {
			 // 載入路線服務與路線顯示圖層
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
				zoom : 17,//放大的倍率
				center : latlng,//初始化的地圖中心位置
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
			// 放置路線圖層
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
					animation : google.maps.Animation.BOUNCE, // DROP掉下來、BOUNCE一直彈跳
					draggable : false// true、false可否拖拉
				});
				var infowindow = new google.maps.InfoWindow(
					{
						content : '<h1><%=restaurantVO.getRestaurantname()%> <br>電話 : <%=restaurantVO.getRestaurantnum()%></h1>',
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
	                var contentString='目前位置';
	        	    var infowindow = new google.maps.InfoWindow(
	        				{
	        					content : contentString,
	        					position : pos,
	        					maxWidth:400 ,
	        					
	        				});
	        		infowindow.open(map, marker);
	        		 // 路線相關設定
	    		    var request = {
	    		        origin: pos,//<必要> 起始位置
	    		        destination: address,//<必要> 終點位置
	    		        travelMode: 'DRIVING',//<必要> 移動方式，有 DRIVING、BICYCLING、TRANSIT、 WALKING，預設為 DRIVING
	    		        avoidFerries:true,//忽略渡輪，可設定 true 或 false。
	    		        avoidHighways:true,//忽略高速公路，可設定 true 或 false。
	    		        avoidTolls:true,//忽略收費公路，可設定 true 或 false。
	    		        
	    		        
	    		    };

	    		    // 繪製路線
	    		    directionsService.route(request, function (result, status) {
	    		        if (status == 'OK') {
	    		            // 回傳路線上每個步驟的細節
	    		            console.log(result.routes[0].legs[0].steps);
	    		            directionsDisplay.setDirections(result);
	    		            
	    		        } else {
	    		            console.log(status);
	    		        }
	    		    });
	            });
	        } else {
	            // Browser doesn't support Geolocation
	            alert("未允許或遭遇錯誤！");
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