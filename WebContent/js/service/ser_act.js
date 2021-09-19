function init() {
	$.ajax({
	      url: "/APE/service/SerServlet", // 資料請求的網址
	      type: "GET", // GET | POST | PUT | DELETE | PATCH
	      data: "serSearch=searchAcitivityType",
	      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
	      success: 
    	  function (data) {
			var str=""
			var info = document.querySelector('.type');
			console.log(info.value);
			for (const [key, value] of Object.entries(data)) {
				var content = '<option value="' + `${key}` + '">'+ `${value}` + '</option>';
				str += content;
				info.innerHTML = str;
				}
	      	},
	      error: function (xhr) {
	        alert("伺服器忙碌中");
	      },
	    });
	$.ajax({
	      url: "/APE/service/SerServlet", // 資料請求的網址
	      type: "GET", // GET | POST | PUT | DELETE | PATCH
	      data: "serSearch=searchAcitivity&type=1",
	      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
	      success: 
	  	  function (data) {
			list_html="";
			for(var i in data){
			    var key = i;
			    var val = data[i];
			    console.log(val);
		        list_html += `
					<span>${val} </span></br>`
			}
		    $('#serActList').html(list_html);
	      },
	      error: function (xhr) {
	        alert("伺服器忙碌中");
	      },
	});
};

$(".type").on('change', function(){
	var info = document.querySelector('.type').value;
	$.ajax({
	      url: "/APE/service/SerServlet", // 資料請求的網址
	      type: "GET", // GET | POST | PUT | DELETE | PATCH
	      data: "serSearch=searchAcitivity&type="+ info,
	      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
	      success: 
	  	  function (data) {
			list_html="";
			for(var i in data){
			    var key = i;
			    var val = data[i];
			    console.log(val);
		        list_html += `
					<span>${val} </span></br>`
			}
		    $('#serActList').html(list_html);
	      },
	      error: function (xhr) {
	        alert("伺服器忙碌中");
	      },
	});
})
window.addEventListener('load', init);