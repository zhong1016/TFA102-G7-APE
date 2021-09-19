$(function() {

	$("div.modify-1").on('click', function() {

		if ($("td.name input").length) {
			let name = $("td.name input").val().trim();
			let phone = $("td.phone input").val().trim();
			let email = $("td.email input").val().trim();
		
			
			let nameReg = /^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$/;
			let phoneReg = /^[(0-9)]{10}$/;

			let alertMessage = "";
			
			//比對暱稱
			if (!name.match(nameReg) && name != "") {
				alertMessage += '暱稱:只能是英文字母、數字和_ , 且長度必需在2到10之間' + '\n';
			} else if (name == "") {
				alertMessage += '暱稱請勿為空' + '\n';
			}
						
			//比對電話號碼
			
			if(!phone.match(phoneReg) && phone != ""){
				alertMessage += '電話:電話長度為10' + '\n';
			} else if (phone == ""){
				alertMessage += '電話:請勿為空';
			}
			
			//驗證email不為空
			if(email == ""){
				alertMessage += 'Email:請勿為空'
			}
			
			if(alertMessage.trim() == ""){
				$("td.name").text(name);
				$("td.phone").text(phone);
				$("td.email").text(email);		
			}else{
				alert(alertMessage);
			}					
			
		} else {

			let name = ($("td.name").text()).trim();
			let phone = ($("td.phone").text()).trim();
			let email = ($("td.email").text()).trim()

			

				let modify_name = `<input  type="text" value="${name}">`;
				let modify_phone = `<input  type="text" value="${phone}">`;
				let modify_email = `<input  type="text" value="${email}">`;

				$("td.name").html(modify_name);
				$("td.phone").html(modify_phone);
				$("td.email").html(modify_email);

		}
	})

	var image_change = "unchange";
	$("div.modify_save")
			.on(
					'click',
					function() {

						if (!$("td.email > input").length
								&& image_change === "change") {
							alert("更新成功");
							console.log(image_change);
							let name = $("td.name").text();
							let phone = $("td.phone").text();
							let email = $("td.email").text();
							let image = $("#preview").attr("src");
							let data = {
								"name" : name,
								"phone" : phone,
								"email" : email,
								"image" : image,
								"image_change" : image_change
							}
							$.ajax({
								url : "/APE/Worker_management", // 資料請求的網址
								type : "POST", // GET | POST | PUT | DELETE |
								// PATCH
								data : data, // 傳送資料到指定的 url
								dataType : "json", // 預期會接收到回傳資料的格式： json | xml
								// | html
								timeout : 0, // request 可等待的毫秒數 | 0 代表不設定
								// timeout
								success : function(data) { // request 成功取得回應後執行
								},
								error : function(xhr) { // request 發生錯誤的話執行
									window.location.reload();
								},
								complete : function(xhr) { // request 完成之後執行(在
									// success / error
									// 事件之後執行)
								}
							});

						} else if (!$("td.email > input").length
								&& image_change === "unchange") {

							alert("更新成功");
							console.log(image_change);
							let name = $("td.name").text();
							let phone = $("td.phone").text();
							let email = $("td.email").text();
							let data = {
								"name" : name,
								"phone" : phone,
								"email" : email,
								"image_change" : image_change
							}
							$.ajax({
								url : "/APE/Worker_management", // 資料請求的網址
								type : "POST", // GET | POST | PUT | DELETE |
								// PATCH
								data : data, // 傳送資料到指定的 url
								dataType : "json", // 預期會接收到回傳資料的格式： json | xml
								// | html
								timeout : 0, // request 可等待的毫秒數 | 0 代表不設定
								// timeout
								success : function(data) { // request 成功取得回應後執行
								},
								error : function(xhr) { // request 發生錯誤的話執行

								},
								complete : function(xhr) { // request 完成之後執行(在
									// success / error
									// 事件之後執行)
								}
							});

						} else {
							alert("請先確認修改內容");
						}

					})

	// 預覽圖讀取
	$("#file").on('change', function() {
		let reader = new FileReader();
		reader.readAsDataURL(this.files[0]);

		// console.log($("#preview").attr("src"));
		reader.addEventListener('load', function() {
			$("#preview").attr("src", reader.result);
			// 圖片變動後將image變數變更為change
			image_change = "change";
		})
	})

})