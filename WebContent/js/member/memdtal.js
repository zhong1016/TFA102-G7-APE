$(function test() {
	$("form select").attr("disabled", true);
	$(".btnNew").attr("disabled", false);

	$(".btnNew").click(function() {
		$(".locked").attr("disabled", false);
//		$(".btnNew").attr("disabled", true);
		$('.city').on('focus', function copcity (e) {	
			$.getJSON("../opendata109road.json", function(data) {
				var info = document.querySelector('#city');
				var btn = document.querySelector('.district');
				var len = data.length;
				var lend = 0;
				var str = "";			
				console.log(data)
				for (var i = 0; i < len; i++) {
//					var dl = data[i].districts.length;
					var content = '<option value="' + data[i].name + '">'+ data[i].name + '</option>';
					str += content;
					info.innerHTML = str;
				}
			})
		});
	})
	$(".password").on('click', function() {
		$(".password").attr("disabled", true);
		$("div.memPswdChange").fadeIn();
	});

	$("button.btn_modal_close").on("click", function(){
	  $("div.memPswdChange").fadeOut();
	});
});
var strs = document.getElementsByClassName('city')[0];
console.log(strs);
strs.addEventListener('change', function(e) {
	let selectedValue = e.target.value;
	$.getJSON("../opendata109road.json", function(data) {
		var district = document.querySelector('.district');
		var str = "";
		for (var i = 0; i < data.length; i++) {
			if (data[i].name == selectedValue) {
				for (var j = 0; j < data[i].districts.length; j++) {
					var content = '<option value="'+ data[i].districts[j].name + '">'+ data[i].districts[j].name + '</option>';
					str += content;
					district.innerHTML = str;
				}
			}
		}
	})
})

$(".file").on('change', function() {
	let reader = new FileReader();
	reader.readAsDataURL(this.files[0]);
	// console.log($("#preview").attr("src"));
	reader.addEventListener('load', function() {
		$("#memInputImg").attr("src", reader.result);
		// 圖片變動後將image變數變更為change
		image_change = "change";
	})
})

$(".copBRC").on('change', function() {
	let reader = new FileReader();
	reader.readAsDataURL(this.files[0]);
	// console.log($("#preview").attr("src"));
	reader.addEventListener('load', function() {
		$("#copInputImg").attr("src", reader.result);
		// 圖片變動後將image變數變更為change
		image_change = "change";
	})
})
