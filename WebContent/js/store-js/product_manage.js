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


$(function() {

	$(".file").on('click', function() {

		let $img = $(this).next("img");

		$(this).on('change', function() {
			let reader = new FileReader();
			reader.readAsDataURL(this.files[0]);
			$(this).prev().val("change");
			console.log($(this).prev().val());

			reader.addEventListener('load', function() {
				$img.attr("src", reader.result);

			})

		})
	})

	// ===========================更新按鈕事件===============================

	$(".button-upd").on(
			'click',
			function() {

				let image_change = $($(this).closest("div.product_manager"))
						.find("input#image").val();
				console.log(image_change);

				if (image_change === "change") {  //如果有改圖片

					let pro_no = $($(this).closest("div.product_manager"))
							.find("input.pro_no").val()
					console.log(pro_no);

					let pro_name = $($(this).closest("div.product_manager"))
							.find("div.product_name").find("input.inputtext")
							.val();
					console.log(pro_name);
					
					if (pro_name == "") {
						alert("商品名稱不得為空")
						return;
					}

					let price = $($(this).closest("div.product_manager")).find(
							"div.product_price").find("input.input_price")
							.val();
					console.log(price);
					
					if (price <= 0 || price == null) {
						alert("商品價格不得小於等於0和空值")

						return;
					}

					let stock = $($(this).closest("div.product_manager")).find(
							"div.product_price").find("input.input_count")
							.val();
					console.log(stock);
					

					if (stock < 0 || stock == "") {
						alert("庫存數量不得負數等於0和空值")
						return;
					}

					let category = $($(this).closest("div.product_manager"))
							.find("div.product_gory").find("select.category")
							.val();
					console.log(category);

					let image = $($(this).closest("div.product_manager")).find(
							"div.product_pic");

					let $image = $($(image).find("img")).attr("src");
					console.log($image);

					let data = {
						"pro_no" : pro_no,
						"image" : $image,
						"pro_name" : pro_name,
						"price" : price,
						"stock" : stock,
						"category" : category,
						"imagechange" : image_change
					}
					console.log(data);

					$.ajax({
						url : "/APE/ProductUpdateServlet", // 資料請求的網址
						type : "POST", // GET | POST | PUT | DELETE | PATCH
						data : data, // 傳送資料到指定的 url
						dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
						timeout : 0, // request 可等待的毫秒數 | 0 代表不設定 timeout
						success : function(data) { // request 成功取得回應後執行

						},
						error : function(xhr) { // request 發生錯誤的話執行
							window.alert("更新成功")
							// window.reload;
						},
						complete : function(xhr) { // request 完成之後執行(在 success
							// /
							// error事件之後執行)
						}
					});

				} else {

					let pro_no = $($(this).closest("div.product_manager"))
							.find("input.pro_no").val()
					console.log(pro_no);

					let pro_name = $($(this).closest("div.product_manager"))
							.find("div.product_name").find("input.inputtext")
							.val();
					console.log(pro_name);
					
					if (pro_name == "") {
						alert("商品名稱不得為空")
						return;
					}

					let price = $($(this).closest("div.product_manager")).find(
							"div.product_price").find("input.input_price")
							.val();
					console.log(price);
					
					if (price <= 0 || price == null) {
						alert("商品價格不得小於等於0和空值")

						return;
					}

					let stock = $($(this).closest("div.product_manager")).find(
							"div.product_price").find("input.input_count")
							.val();
					console.log(stock);
					
					if (stock < 0 || stock == "") {
						alert("庫存數量不得負數等於0和空值")
						return;
					}
					

					let category = $($(this).closest("div.product_manager"))
							.find("div.product_gory").find("select.category")
							.val();
					console.log(category);

					let data = {
						"pro_no" : pro_no,
						"pro_name" : pro_name,
						"price" : price,
						"stock" : stock,
						"category" : category,
						"imagechange" : image_change
					}
					console.log(data);

					$.ajax({
						url : "/APE/ProductUpdateServlet", // 資料請求的網址
						type : "POST", // GET | POST | PUT | DELETE | PATCH
						data : data, // 傳送資料到指定的 url
						dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
						timeout : 0, // request 可等待的毫秒數 | 0 代表不設定 timeout
						success : function(data) { // request 成功取得回應後執行

						},
						error : function(xhr) { // request 發生錯誤的話執行
							window.alert("更新成功")
							 window.reload;
						},
						complete : function(xhr) { // request 完成之後執行(在 success
							// /
							// error事件之後執行)
						}

					})
				}
			});

	// =================下架商品============================

	$(".button-del").on(
			'click',

			function() {

				
				if (confirm("確定要下架嗎?") == true) {
					let pro_no = $($(this).closest("div.product_manager"))
							.find("input.pro_no").val()
					console.log(pro_no);

					let stock = $($(this).closest("div.product_manager")).find(
							"div.product_price").find("input.input_count")
							.val();
					stock = 0;
					console.log(stock);

					let state = $($(this).closest("div.product_manager")).find(
							"div.state").find("p").text();

					console.log(state)
					let data = {
						"pro_no" : pro_no,
						"state" : state
					}

					$.ajax({
						url : "/APE/ProductDelete", // 資料請求的網址
						type : "POST", // GET | POST | PUT | DELETE | PATCH
						data : data, // 傳送資料到指定的 url
						dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
						timeout : 0, // request 可等待的毫秒數 | 0 代表不設定 timeout
						success : function(data) { // request 成功取得回應後執行

						},
						error : function(xhr) { // request 發生錯誤的話執行
							window.alert("下架成功")
						},
						complete : function(xhr) { // request 完成之後執行(在 success
							// /
							// error事件之後執行)
						}
					});

				}
			})
});