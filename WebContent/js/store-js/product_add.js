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

			reader.addEventListener('load', function() {
				$img.attr("src", reader.result);

			})

		})

	})

	$(".button").on(
			'click',
			function() {

				let image = $($(this).closest("div.product_manager")).find(
						"div.product_pic");

				let $image = $($(image).find("img")).attr("src");
				
				console.log("image="+image)

				if($image == ""){
					alert("請新增圖片")
					return;
				}
				
				
				
				let name = $($(this).closest("div.product_manager")).find(
						"div.product_name").find("input.inputtext").val();
				
				if (name == "") {
					alert("商品名稱不得為空")
					return;
				}

				let price = $($(this).closest("div.product_manager")).find(
						"div.product_price").find("input.input_price").val();
				
				if (price <= 0 || price == null) {
					alert("商品價格不得小於等於0和空值")

					return;
				}
				console.log("price=" + price);

				let category = $($(this).closest("div.product_manager")).find(
						"div.product_gory").find("select.category").val();

				let stock = $($(this).closest("div.product_manager")).find(
						"div.product_price").find("input.input_count").val();
				
				if (stock < 0 || stock == "") {
					alert("庫存數量不得負數等於0和空值")
					return;
				}

				let data = {
					"image" : $image,
					"name" : name,
					"price" : price,
					"stock" : stock,
					"category" : category
				}
				console.log(data);

				$.ajax({
					url : "/APE/ProductServlet", // 資料請求的網址
					type : "POST", // GET | POST | PUT | DELETE | PATCH
					data : data, // 傳送資料到指定的 url
					dataType : "json", // 預期會接收到回傳資料的格式： json | xml | html
					timeout : 0, // request 可等待的毫秒數 | 0 代表不設定 timeout
					success : function(res) { // request 成功取得回應後執行

					},
					error : function(xhr) { // request 發生錯誤的話執行
						window.alert("新增成功");
						window.location.reload();

					},
					complete : function(xhr) { // request 完成之後執行(在 success /
						// error事件之後執行)
					}
				});

			})

});