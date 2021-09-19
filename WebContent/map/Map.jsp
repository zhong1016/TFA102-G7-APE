<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>艾普藝森</title>
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/bootstrap.rtl.min.css">
  <link rel="stylesheet" href="css/swiper-bundle.min.css">
<link rel="stylesheet" href="css/index-css/index.css">
<link
	href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap"
	rel="stylesheet" />
<style>
#body {
	position: relative; /*相對定位*/
	width: 100vw;
	top: 0;
	left: 0;
}

#map {
	position: absolute;
	left: 5%;
	height: 80%; /*地圖高*/
	width: 90vw;
}

.gm-style .gm-style-iw-c {
	background-color: rgba(233 198 149 / 94%);
}

.gm-style .gm-style-iw-d::-webkit-scrollbar-track, .gm-style .gm-style-iw-d::-webkit-scrollbar-track-piece
	{
	background-color: rgba(233 198 149 / 94%);
}

.gm-style .gm-style-iw-t::after {
	background: linear-gradient(45deg, rgba(233 198 149) 94%,
		rgba(255, 255, 255, 0) 51%, rgba(255, 255, 255, 0) 100%);
}
form { text-align: center; } 
</style>
</head>

<body>
	<header>
    <div class="header-div">
      <div class="header-img">
        <a href="/APE/index.html"><img class="logo" src="img/APE3.svg" alt="" /></a>
      </div>
      <div class="nav" id="header">
        <ul>
          <li><a class="a1" href="#">線上商城</a></li>
          <li>
            <a class="a1" href="ticket/Schedule.html">展覽活動</a>
            <ul class="menu-ul">
              <li class="a2"><a href="">全年檔期</a></li>
              <li class="a2"><a href="">優惠資訊</a></li>
              <li class="a2"><a href="">報名查詢</a></li>
            </ul>
          </li>
          <li><a class="a1" href="#">地圖導覽</a></li>
          <li>
            <a class="a1" href="#">廠商專區</a>
            <ul class="menu-ul">
              <li class="a2"><a href="">廠商後台</a></li>
              <li class="a2"><a href="">場地租借</a></li>
              <li class="a2"><a href="">活動查詢</a></li>
            </ul>
          </li>
          <li><a class="a1" href="#">討論區</a></li>
          <li>
            <a class="a1" href="/APE/service/service.jsp">聯絡客服</a>
            <ul class="menu-ul">
              <li class="a2"><a class="SER_MSG" href="/APE/service/ser_act.jsp" target="insideme">意見回饋</a></li>
              <li class="a2"><a class="SER_QnA" href="/APE/service/ser_qna.jsp" target="insideme">常見問答</a></li>
              <li class="a2"><a class="SER_PRO" href="/APE/service/ser_pro.jsp" target="insideme">贈品兌換</a></li>
            </ul>
          </li>
          <li><a class="a1" href="#">會員中心</a></li>
          <a class="a1-shop" href=""><img class="shop" src="img/shop.png" alt="" /></a>
        </ul>
      </div>
      <input type="checkbox" name="menu-switcher" id="menu-switcher" />
      <label for="menu-switcher" class="hamburger">
        <div class="hamburger-line"></div>
      </label>
      <ul class="menu">
        <li><a href="">線上商城</a></li>
        <li><a href="">展覽活動</a></li>
        <li><a href="/APE/map/Map.jsp">地圖導覽</a></li>
        <li><a href="">廠商專區</a></li>
        <li><a href="">討論區</a></li>
        <li><a href="/APE/service/service.jsp">聯絡客服</a></li>
        <li><a href="">會員中心</a></li>
      </ul>
    </div>
  </header>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>

		<jsp:useBean id="restaurantSvc" scope="page"
			class="com.restaurant.model.RestaurantService" />


		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/restaurant/restaurant.do">
				<b>餐廳名稱:</b> <select size="1" name="restaurantno">
					<c:forEach var="restaurantVO" items="${restaurantSvc.all}">
						<option value="${restaurantVO.restaurantno}">${restaurantVO.restaurantname}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/restaurant/restaurant.do">
				<b>餐廳種類:</b> <select size="1" name="restaurantno">
					<c:forEach var="restaurantVO" items="${restaurantSvc.all}">
						<option value="${restaurantVO.restaurantno}">${restaurantVO.restauranttype}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		<jsp:useBean id="locationSvc" scope="page"
			class="com.location.model.LocationService" />
		<li>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/location/location.do">
				<b><font color=orange>推薦展區周邊美食餐廳:</font></b> <select size="1"
					name="locationno">
					<c:forEach var="locationVO" items="${locationSvc.all}">
						<option value="${locationVO.locationno}">${locationVO.locationname}
					</c:forEach>
				</select> <input type="hidden" name="action"
					value="listRestaurants_ByLocationno_A"> <input
					type="submit" value="送出">
		</li>
	</ul>


	<div class="body">
		<div id="map"></div>
	</div>
	<script>
	function initMap() {
	    var Station_latlng = { lat: 25.052087, lng: 121.543185 };	// Tibame台北經緯度
	    var map = new google.maps.Map(document.getElementById('map'), {
	        zoom: 16,	//放大的倍率
	        center: Station_latlng	//初始化的地圖中心位置
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
                var contentString='<h1>在Tibame上課中，<br>等下要去哪看展覽?<br>順便尋找美食嗎?</h1>';
        	    var infowindow = new google.maps.InfoWindow(
        				{
        					content : contentString,
        					position : pos,
        					maxWidth : 400 ,
        					
        				});
        		infowindow.open(map, marker);
            });
        } else {
            // Browser doesn't support Geolocation
            alert("未允許或遭遇錯誤！");
        }
	}
	


	</script>
<br>
	


	<script src="js/index.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhNp82kn510sPABlVdrzn_Veryi5aVqag&callback=initMap&libraries=&v=weekly"
		async></script>
</body>

</html>