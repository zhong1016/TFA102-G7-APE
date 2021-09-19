<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.* "%>



<html>
<head>
<title>Google Maps Place</title>
<link rel="canonical"
	href="https://letswrite.tw/google-map-api-place-api/">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
.container {
	padding-top: 30px;
	padding-bottom: 30px;
}

#map {
	background: #CCC;
	height: 0%;
}

ul {
	padding-top: 16px;
}

.media img {
	max-width: 64px;
}

.media h5, p {
	font-size: 2rem;
}

.mdeia p {
	margin-bottom: 6px;
}

.media h6 {
	font-size: 12px;
	color: #CCC;
}

.fixed-bottom {
	position: fixed;
	left: 16px;
	bottom: 0;
	max-width: 320px;
}

body {
	background: #f4eccf;
}
</style>

<link rel="shortcut icon"
	href="https://letswritetw.github.io/letswritetw/dist/img/logo_512.png" />
<!-- Google Tag Manager-->

</head>
<body>

	<!-- Google Tag Manager (noscript)-->
	<noscript>
		<iframe src="https://www.googletagmanager.com/ns.html?id=GTM-PGQ9WQT"
			height="0" width="0" style="display: none; visibility: hidden"></iframe>
	</noscript>



	<div id="app" class="container">
		<div>
			<a href="/APE/map/Map.jsp"><img src="https://icon-library.com/images/restaurant-icon-png/restaurant-icon-png-21.jpg"
						width="50" height="50" border="0">餐廳查詢</a>
		<!-- 搜尋框 -->
		<div class="row">
			<div class="col google-map">
				<h5>Search：</h5>
				<div class="form-group">
					<input type="text" class="form-control" ref="site" v-model="site">
				</div>
			</div>
		</div>
		<div></div>

		<hr>

		<!-- 放評論摘要的div -->
		<div class="row" v-if="place != null">
			<div class="col" v-if="place.reviews != null">
				<h5>評論：</h5>
				<div class="row" v-for="p in place.reviews">
					<div class="col">
						<ul class="list-unstyled">
							<li class="media"><img :src="p.profile_photo_url"
								class="mr-3">
								<div class="media-body">
									<h5 class="mt-0 mb-1">
										<a target="_blank" :href="p.author_url">{{ p.author_name
											}}</a>
									</h5>
									<p>{{ p.text }}</p>
									<h6>{{ p.relative_time_description }}</h6>
								</div></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- 放google map的div -->
		<div class="row">
			<div class="col google-map">
				<h5></h5>
				<div id="map" class="embed-responsive embed-responsive-16by9"></div>
			</div>
		</div>
	</div>

	<!-- 將 YOUR_API_KEY 替換成你的 API Key 即可 -->
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDhNp82kn510sPABlVdrzn_Veryi5aVqag&libraries=places"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.6.10/vue.min.js"></script>

	<!-- map -->
	<script>
      const googleMap = new Vue({
        el: '#app',
        data: {
          map: null,
          autocomplete: null,
          site: '', // place API要綁定的搜尋框
          place: null // 存place確定後回傳的資料
        },
        methods: {
          // init google map
          initMap() {

            let location = {
              lat: 25.0374865,
              lng: 121.5647688
            };

            this.map = new google.maps.Map(document.getElementById('map'), {
              center: location,
              zoom: 16
            });
          },
          // 地址自動完成 + 地圖的中心移到輸入結果的地址上
          siteAuto() {

            let options = {
              componentRestrictions: { country: 'tw' } // 限制在台灣範圍
            };
            this.autocomplete = new google.maps.places.Autocomplete(this.$refs.site, options);
            this.autocomplete.addListener('place_changed', () => {
              this.place = this.autocomplete.getPlace();
              if(this.place.geometry) {
                let searchCenter = this.place.geometry.location;
                this.map.panTo(searchCenter); // panTo是平滑移動、setCenter是直接改變地圖中心
                
                // 放置標記
                let marker = new google.maps.Marker({
                  position: searchCenter,
                  map: this.map
                });

                // info window
                let infowindow = new google.maps.InfoWindow({
                  content: this.place.formatted_address
                });
                infowindow.open(this.map, marker);

              }
            });
          }
        },
        mounted() {
          window.addEventListener('load', () => {

            this.initMap();
            this.siteAuto();

          });
        }
      })
    </script>

</body>
</html>