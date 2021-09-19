
function initMap() {
    var latlng = { lat: 25.046891, lng: 121.516602 }; // 給一個初始位置
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 14, //放大的倍率
        center: latlng //初始化的地圖中心位置
    });
    var marker = new google.maps.Marker({
        position: latlng,
        map: map
    });
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            var marker = new google.maps.Marker({
                position: pos,
                icon: 'marker.png',
                map: map
            });
            map.setZoom(17);
            map.setCenter(pos);
        });
    } else {
        // Browser doesn't support Geolocation
        alert("未允許或遭遇錯誤！");
    }
} //init_end