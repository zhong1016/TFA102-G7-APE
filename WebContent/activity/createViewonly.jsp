<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.* "%>
<%@ page import="com.activity.model.ActivityService"%>
<%@ page import="com.activity.model.ActivityVO"%>
<%@ page import="com.activitytype.model.ActivitytypeVO"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>createViewonly</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/header.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick-theme.css" />
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/activity-js/slick.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/pageCss/createViewonly.css">

</head>

<body>
	<%@ include file="/header.file"%>

	<div class="div_bg">
		<img src="<%=request.getContextPath()%>/img/activity-img/banner2.png" class="bg_1" alt="">
	</div>
	<div class="bg_range">
		<div class="create_data">
			<div class="create_infor">

				<!--                 <div style="font-size: 20px;">請填寫以下資料</div> -->
				<div>
					活動名稱:
					<input type="text" value="${activityVO.activityName}">
				</div>
				<div>
					開始日期:
					<input type="date" id="startdate" value="${activityVO.startDate}">
				</div>
				<div>
					結束日期:
					<input type="date" id="closedate" value="${activityVO.closeDate}">
				</div>
				<div>
					合計天數:
					<input type="text" style="width: 50px" id="dayCount" value="${activityVO.dayCount}">
					天
				</div>
				<div>
					活動時間:
					<input type="text" placeholder="ex:9am-8pm" value="${activityVO.activityHours}">
				</div>
				<div>
					攤位數量:
					<input type="text" style="width: 50px" class="rentCount" value="${activityVO.rentCount}">
				</div>



				<div>
					活動地址:
					<input type="text" style="width: 300px" value="${activityVO.address}">
				</div>

				<div id="radio">
					<label>活動類型:</label>
					<br>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="1">
						<span class="round button">手作文創</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="2">
						<span class="round button">生活雜貨</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="3">
						<span class="round button">小農商品</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="4">
						<span class="round button">餐飲食品</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="5">
						<span class="round button">寵物用品</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="6">
						<span class="round button">城市特色</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="7">
						<span class="round button">二手市集</span>
					</label>
					<label>
						<input type="radio" name="ACT_TYPE_NO" value="8">
						<span class="round button">異國特色</span>
					</label>


				</div>

				<div>
					欲出租攤位價格:
					<input type="text" style="width: 50px" value="${activityVO.price}">
					元／天
				</div>


				<div>活動簡介:</div>
				<textarea placeholder="請填寫活動簡介...">${activityVO.introduction}</textarea>

			</div>
			<div class="create_pic">
				<!-- <div class="pic"></div> -->
				<div class="preview" id="preview">
					<span class="pic_text">
						<img src="<%=request.getContextPath()%>/activity/ActivityPic?activityNo=${activityVO.activityNo}">
					</span>
				</div>
				<!--                 <input type="file" accept="image/*" class="button_file" id="p_file"> -->

			</div>
		</div>
		<div class="greenleaf">
			<img src="<%=request.getContextPath()%>/img/activity-img/greenleaf_long.png" alt="">
		</div>
		<div class="rent_range">
			<div class="countRow">
				請填入數量選取欲出租範圍
				<div class="rentRow1">
					橫列數量:
					<input type="text" style="width: 50px;" id="rentRow" value="">
				</div>
				<div class="rentCol1">
					直行數量:
					<input type="text" style="width: 50px;" id="rentCol" value="">
				</div>
				<div class="cheack">
					<button class="countCheack" id="btn">預覽</button>
				</div>

				<!-- 攤位長字串 -->
				<!-- <textarea name="seatLong" id="seatLong" rows="4" cols="50"></textarea> -->
				<!-- 列數陣列 -->
				<!-- <textarea name="rowsNum" id="rowsNum" rows="4" cols="50"></textarea> -->
				<!-- 號陣列 -->
				<!-- <textarea name="columnsNum" id="columnsNum" rows="4" cols="50"></textarea> -->
				<!-- 可出租攤位陣列 -->
				<!-- <textarea name="rent_available" id="rent_available" rows="4" cols="50"></textarea> -->

				<!-- activity抓資料 value抓資料 -->
				<input type="hidden" id="rent_available" value="${activityVO.rentString}">
				<input type="hidden" id="rent_rows" value="${activityVO.rowNum}">
				<input type="hidden" id="rent_columns" value="${activityVO.columnsNum}">
				<!-- 已出租攤位陣列 -->
				<input type="hidden" id="reservation" value="${activityVO.reservationAll}">
				<input type="hidden" value="${activityVO.createDate}">
			</div>
			<div class="rent" id="rent">
				<!-- 顯示出攤位的區塊 -->
			</div>
		</div>
		<div class="insert_save">
			<button class="button1" id="btn1">送出申請</button>
		</div>
		<div class="insert_save">
			<button class="button1" id="save">儲存</button>
		</div>
		<!-- <div class="note">*提出申請後請至專區確認審查狀態，審查將於活動開始時間前一個月完成並開放會員租借攤位，活動審查通過後將無法調整活動資訊。</div> -->
	</div>
	<div class="flower_pic">
		<img src="<%=request.getContextPath()%>/img/activity-img/flower6.png" alt="flower">
	</div>
	<div class="block2"></div>
	<nav id="pagetop" class="block_pagetop">
		<a href="#">
			top
			<span></span>
		</a>
	</nav>


	<%@ include file="/footer.file"%>





	<!-- <script src="js/bootstrap.bundle.min.js"></script> -->
	<script src="<%=request.getContextPath()%>/js/activity-js/sweetalert.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/activity-js/jquery.seat-charts.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/header.js"></script>
	<script type="text/javascript">
		$(function() {

			var view = true;
			if (view) {
				$("input").attr("disabled", true);
				$("textarea").attr("disabled", true);
				$(".countCheack").css("display", "none");
				$(".button1").css("display", "none");
				var maparray = $("#rent_available").val().split(",");
				var rowArr = $("#rent_rows").val().split(",");
				var colArr = $("#rent_columns").val().split(",");
				var reserArr = $("#reservation").val().split(",");
				// console.log(maparray);
				var sc = $("#rent").seatCharts(
						{

							map : maparray,
							naming : {
								top : false,
								left : false,
								rows : rowArr,
								columns : colArr,
								getLabel : function(character, row, column) {
									return row + '_' + column;
								}
							},
							legend : { //定義圖例
								node : $('#legend'),

								items : [ [ 'a', 'available', '可選租' ],
										[ 'a', 'unavailable', '不出租' ], ]
							},
						});
				sc.get(reserArr).status('unavailable');

			} else {

				//宣告
				var preview_el = document.getElementById("preview"); //預覽圖
				var p_file_el = document.getElementById("p_file"); //預覽圖
				var rentNo;

				var preview_img = function(file) {
					var img_node = document.createElement("img");
					var reader = new FileReader(); // 用來讀取檔案
					reader.addEventListener("load", function() {
						// console.log(reader.result);
						let img_node = document.createElement("img");
						img_node.setAttribute("src", reader.result); // <img src="abdafaewre">
						img_node.setAttribute("class", "preview_img"); // <img src="abdafaewre" class="preview_img">
						preview_el.innerHTML = '';
						preview_el.append(img_node);
					});
					reader.readAsDataURL(file); // 讀取檔案
				};

				p_file_el
						.addEventListener(
								"change",
								function(e) {
									if (this.files.length > 0) {
										preview_img(this.files[0]);
										$("#preview").addClass("preview_on");

									} else {
										preview_el.innerHTML = '<span class="pic_text">預覽圖</span>';
									}
								});
				// totop
				$("#pagetop").addClass("hide");
				$("#pagetop").click(function(e) {
					e.preventDefault();
					$("html,body").animate({
						scrollTop : 0,
					}, 600);
				});
				// totop
				$(window).scroll(function() {
					if ($(window).scrollTop() > 200) {

						if ($("#pagetop").hasClass("hide")) {
							$("#pagetop").toggleClass("hide");
						}
					} else {

						$("#pagetop").addClass("hide");
					}
				});

			}
			;

			var activityTypeNo = '${activityVO.activityTypeNo}';
			if (activityTypeNo) {
				$('#radio input[type=radio]').each(function(idx, ele) {
					if (activityTypeNo == $(ele).val()) {
						$(ele).attr('checked', true);
					}
				});
			}

			var rowNum = '${activityVO.rowNum}';
			var rowArray = rowNum.split(',');
			$("#rentRow").val(rowArray[rowArray.length - 1]);

			var colNum = '${activityVO.columnsNum}';
			var colArray = colNum.split(',');
			$("#rentCol").val(colArray[colArray.length - 1]);

		});
	</script>

</body>

</html>