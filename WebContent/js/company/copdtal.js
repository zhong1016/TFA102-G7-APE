$(function test() {
	$("form select").attr("disabled", true);
	$(".btnNew").attr("disabled", false);

	$(".btnNew").click(function() {
		$(".locked").attr("disabled", false);
		$(".btnNew").attr("disabled", true);
		$('.city').on('click', function copcity (e) {
			$.getJSON("../opendata109road.json", function(data) {
				var info = document.querySelector('.city');
				var btn = document.querySelector('.district');
				var len = data.length;
				var lend = 0;
				var str = "";			
				console.log(data)
				for (var i = 0; i < len; i++) {
					var dl = data[i].districts.length;
					var content = '<option value="' + data[i].name + '">'+ data[i].name + '</option>';
					str += content;
					info.innerHTML = str;
				}
			})
		});
	})
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
	 $("#memInputImg").html("");
	readURL(this);
})
function readURL(input) {
	if (input.file && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
//			 var img = $("<img width='300' height='200'>").attr('src', e.target.result);
	        $("#memInputImg").attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
//$(".copBRC").on('change', function() {
//	 $("#copInputImg").html("");
//	readURL(this);
//})
//function readURL(input) {
//	if (input.file && input.files[0]) {
//		var reader = new FileReader();
//		reader.onload = function(e) {
//			 var img = $("<img width='300' height='200'>").attr('src', e.target.result);
//	        $("#copInputImg").append(img);
//		}
//		reader.readAsDataURL(input.files[0]);
//	}
//}