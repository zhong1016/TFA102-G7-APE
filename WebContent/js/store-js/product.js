let shopcar =$(".shop")

shopcar.on("click",function(){
   
    $("#car").css("display", "block");

});

$("#shoplogo").mouseout(function () {
    $("#car").css("display", "none");
  });
$("#shoplogo").mouseover(function () {
    $("#car").css("display", "block");
  });
//為你的按鈕註冊事件
let a = document.querySelector("#car")
a.addEventListener('click', addproduct)

//let b = document.querySelector("#follow")
//b.addEventListener('click', followproduct)

let c = document.querySelector("#buy")
c.addEventListener('click', buyproduct)

function addproduct() {
	alert("1")
	document.querySelector('#fs_alert').style.display = 'block';

	document.querySelector('#alert_ok').addEventListener('click', function() {
		document.querySelector('#fs_alert').style.display = 'none'
	})

//	let productNo = $('.product_content').find('#PNO').val();
//	let productName = $('.product_content').find('#PNAME').val();
//	console.log(productNo);
//	console.log(productName);
//	let data = {
//		"productNo" : productNo,
//		"productName" : productName
//	}
//	$.ajax({
//		url : "/APE/Product", // 資料請求的網址
//		type : "POST", // GET | POST | PUT | DELETE | PATCH
//		data : data, // 傳送資料到指定的 url
//		dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
//		timeout : 0, // request 可等待的毫秒數 | 0 代表不設定 timeout
//		success : function(data) { // request 成功取得回應後執行
//
//			let num = 0;
//			for ( var js2 in data) {
//				num++;
//			}
//			$("#car").text("");
//			for (let i = 1; i <= num; i++) {
//				let li = `<li>${data[i]}</li>`
//				$("#car").append(li);
//			}
//		},
//		error : function(xhr) { // request 發生錯誤的話執行
//		},
//		complete : function(xhr) { // request 完成之後執行(在 success
//		}
//
//	})

}

//function followproduct() {
//	document.querySelector('#fs_alert1').style.display = 'block';
//	document.querySelector('.btn_s1').addEventListener('click', function() {
//		document.querySelector('#fs_alert1').style.display = 'none'
//	})
//
//}

function buyproduct() {
	location.href = "/order.html"
}

var firstLi = document.querySelectorAll("header ul li");
for (var i = 0; i < firstLi.length; i++) {
	firstLi[i].onmouseover = function() {
		if (this.querySelector("ul")) {
			var secUL = this.querySelector("ul");
			var liLength = secUL.querySelectorAll('li').length;
			secUL.style.height = 38 * liLength + 'px';
		}
	}
	firstLi[i].onmouseout = function() {
		if (this.querySelector("ul")) {
			var secUL = this.querySelector("ul");
			secUL.style.height = '0px';
		}
	}
}

