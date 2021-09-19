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

// 信用卡資訊
$(function(){
	$("input.card").on("keydown", function(e) {
		// console.log(e.which);
		if ((e.which >= 48 && e.which <= 57) || e.which == 8) {

			// console.log(e.target.value.length);
			if (e.target.value.length == 0 && e.which == 8) {
				$(this).prev().focus();
			}
		} else {
			e.preventDefault();
		}
	});
	


	$("input.card").on("keyup", function(e) {

		// \D 代表非數字字元，g 代表全域尋找
		// let str = e.target.value;
		// console.log(e.target.value);
		let str = (e.target.value).replace(/\D/g, "");

		$(this).val(str);

		// console.log(str.length);
		if (str.length == 4) {
			$(this).next().focus();
		}
	});
})



let del =document.querySelectorAll(".delete-button")
del.forEach(item => {
    item.addEventListener('click',delete_line)
})

function delete_line(){
    $(this).parents('.order_content').remove()
    	
}

let bought = document.querySelector(".checkout")
bought.addEventListener('click',checkout)


function checkout(){
    document.querySelector(".member").className="member_click"
    document.querySelector(".step_num").className="step_num_OK"
}

let z = document.querySelector(".checkbutton")
z.addEventListener('click',pay)

function pay(){
	 		document.querySelector(".pay").className="pay_click";
		    document.querySelector(".step_num").className="step_num_OK";
    
		  
	    	
	  

    	
}

let o =document.querySelector(".OK")
o.addEventListener('click',buyok)


  	console.log($("input.card").val())


// 購物車結帳

function buyok(){
	
	if($("input.card").val()==""){
		alert("請輸入信用卡號")
		return;
	}
		
	// 幾項商品
	let count = $("select.order-count");
	
	// 長度
	let countLength = count.length;
	// 數量陣列
	let countArray = [];
			for(let i = 0; i<countLength; i++){
				countArray[i] = count[i].value
			}
	console.log(countArray);
	
	// 傳送訂購人資料
    let name = $("#name").val();
   
	
	let phone =$("#phone").val();
	
	
	let address =$("#address").val();
	
	
	let data = {
			"name":name,
			"phone":phone,
			"address":address
			
	}

	console.log(data)
	
	let data1 = {
  "information": data,
  "count": countArray
	}
	
	
	$.ajax({
		url : "/APE/CheckOut", // 資料請求的網址
		type : "POST", // GET | POST | PUT | DELETE | PATCH
		data : JSON.stringify(data1), // 傳送資料到指定的 url
		dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
		timeout : 0, // request 可等待的毫秒數 | 0 代表不設定 timeout
		success : function(data) { // request 成功取得回應後執行
			
		},
		error : function(xhr) { // request 發生錯誤的話執行
			
			
		},
		complete : function(xhr) { // request 完成之後執行(在 success
		}

	})
	
	alert("購買成功");
	window.location.assign("http://localhost:8081/APE/store/buyer_manage.jsp");
	}


















var firstLi = document.querySelectorAll("header ul li");
for (var i = 0; i < firstLi.length; i++) {
    firstLi[i].onmouseover = function () {
        if (this.querySelector("ul")) {
            var secUL = this.querySelector("ul");
            var liLength = secUL.querySelectorAll('li').length;
            secUL.style.height = 38 * liLength + 'px';
        }
    }
    firstLi[i].onmouseout = function () {
        if (this.querySelector("ul")) {
            var secUL = this.querySelector("ul");
            secUL.style.height = '0px';
        }
    }
}
$("#shoplogo").mouseout(function () {
    $("#car").css("display", "none");
  });
$("#shoplogo").mouseover(function () {
    $("#car").css("display", "block");
  });

