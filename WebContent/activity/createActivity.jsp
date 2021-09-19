<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>createActivity</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/activity-css/header.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/slick-theme.css" />
<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">

<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/activity-js/slick.min.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/activity-css/pageCss/createActivity.css" />

</head>

<body>
	<%@ include file="/header.file"%>



	<div class="div_bg">
		<img src="<%=request.getContextPath()%>/img/activity-img/banner2.png" class="bg_1" alt="">
	</div>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<div
			style="position: relative; left: 77px; padding: 0 30px; width: 400px; background-color: rgba(255, 255, 255, 0.3); border-radius: 50px; bottom: 30px;">
			<font style="color: red;">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red;">${message}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>


	<FORM METHOD="post" ACTION="ActivityServlet.do" id="form" enctype="multipart/form-data">
		<div class="bg_range">
  <div class="create_data">
        <div class="create_infor">

            <div style="font-size: 20px;">請填寫以下資料</div>
            <div>
                活動名稱:
                <input type="text" value="${activityVO.activityName}" name="activityName" class="activityName">
            </div>
            <div>
                開始日期:
                <input type="date" id="startdate" value="${activityVO.startDate}" name="startDate" class="startDate">
            </div>
            <div>
                結束日期:
                <input type="date" id="closedate" value="${activityVO.closeDate}" name="closeDate"  class="closeDate">
            </div>
            <div>
                合計天數:
                <input type="text" style="width: 50px" id="dayCount" value="${activityVO.dayCount}" name="dayCount"  class="dayCount">
                天
            </div>
            <div>
                活動時間:
                <input type="text" placeholder="ex:9am-8pm" value="${activityVO.activityHours}" name="activityHours"  class="activityHours">
            </div>
            <div>
                攤位數量:
                <input type="text" id="rentCount" style="width: 50px" class="rentCount" value="${activityVO.rentCount}"
                    name="rentCount"  class="rentCount">
            </div>




            <div>
                活動地址:
                <input type="text" style="width: 300px" value="${activityVO.address}" name="address"  class="address">
            </div>

            <div id="radio">
                <label>活動類型:</label>
                <br>
                <label>
                    <input type="radio" value="1" name="activityTypeNo">
                    <span class="round button">手作文創</span>
                </label>
                <label>
                    <input type="radio" value="2" name="activityTypeNo">
                    <span class="round button">生活雜貨</span>
                </label>
                <label>
                    <input type="radio" value="3" name="activityTypeNo">
                    <span class="round button">小農商品</span>
                </label>
                <label>
                    <input type="radio" value="4" name="activityTypeNo">
                    <span class="round button">餐飲食品</span>
                </label>
                <label>
                    <input type="radio" value="5" name="activityTypeNo">
                    <span class="round button">寵物用品</span>
                </label>
                <label>
                    <input type="radio" value="6" name="activityTypeNo">
                    <span class="round button">城市特色</span>
                </label>
                <label>
                    <input type="radio" value="7" name="activityTypeNo">
                    <span class="round button">二手市集</span>
                </label>
                <label>
                    <input type="radio" value="8" name="activityTypeNo">
                    <span class="round button">異國特色</span>
                </label>


            </div>

            <div>
                欲出租攤位價格:
                <input type="text" style="width: 50px" value="${activityVO.price}" name="price"  class="price">
                元／天
            </div>
            <div>活動簡介:</div>
            <textarea placeholder="請填寫活動簡介..." name="introduction"  class="introduction">${activityVO.introduction}</textarea>
            <div id="auto_input">填入</div>


        </div>
        <div class="create_pic">
            <div class="preview" id="preview">
                <span class="pic_text" value="">預覽圖</span>
                <c:if test="${not empty activityVO.activityNo}">
                    <img src="<%=request.getContextPath()%>/activity/ActivityPic?activityNo=${activityVO.activityNo}"
                        class="updatePic">
                </c:if>
            </div>
            <input type="file" accept="image/*" class="button_file" id="p_file" name="activityPic">

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
						<input type="text" style="width: 50px;" id="rentRow" value="5">
					</div>
					<div class="rentCol1">
						直行數量:
						<input type="text" style="width: 50px;" id="rentCol" value="5">
					</div>
					<div class="cheack">
						<button type='button' class="countCheack" id="btn">預覽</button>
					</div>

					<!-- 					攤位長字串 -->
					<textarea name="rentString" id="seatLong" rows="4" cols="50" style="display: none;"></textarea>
					<!-- 					列數陣列 -->
					<textarea name="rowNum" id="rowsNum" rows="4" cols="50" style="display: none;"></textarea>
					<!-- 					號陣列 -->
					<textarea name="columnsNum" id="columnsNum" rows="4" cols="50" style="display: none;"></textarea>
					<!-- 					可出租攤位陣列 -->
					<textarea name="available" id="rent_available" rows="4" cols="50" style="display: none;"></textarea>

					<!-- activity抓資料 value抓資料 -->
					<input type="hidden" id="rent_available" value="${activityVO.available}">
					<input type="hidden" id="rent_rows" value="${activityVO.rowNum}">
					<input type="hidden" id="rent_columns" value="${activityVO.columnsNum}">
					<!-- 已出租攤位陣列 -->
					<%-- 					<input type="hidden" id="reservation" value="${activityVO.reservationAll}"> --%>
					<input type="hidden" value="${activityVO.createDate}" name="createDate">
				</div>
				<div class="rent" id="rent">
					<!-- 顯示出攤位的區塊 -->
				</div>
			</div>
			<div class="insert_save">
				<button type="button" class="button1" id="btn_submit">送出申請</button>
			</div>
			<div class="insert_save">
				<button type="button" class="button1" id="btn_save">儲存</button>
			</div>
			<input type="hidden" id="action" name="action" value="">
			<input type="hidden" name="activityNo" value="${activityVO.activityNo}">
			<!-- <div class="note">*提出申請後請至專區確認審查狀態，審查將於活動開始時間前一個月完成並開放會員租借攤位，活動審查通過後將無法調整活動資訊。</div> -->
		</div>
	</FORM>

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
			$("#auto_input").on("click" , function(){
                $(".activityName").val("職人手作展");
                $(".activityHours").val("10am-08pm");
                $(".rentCount").val("14");
                $(".address").val("台北市信義區南山廣場");
                $(".price").val("300");
                $(".introduction").val("從漫遊、移動出發，尋找隱藏在城市中的文創記憶並透過特展、市集、劇場、工作坊、導覽等方式，邀請各個世代參與了解手作文化。透過職人的創意、靈巧的雙手製作成的各種物品，體會創作者的用心。");

	           });
			//預覽圖
			var preview_el = document.getElementById("preview");
			var p_file_el = document.getElementById("p_file");
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

			p_file_el.addEventListener("change", function(e) {
				if (this.files.length > 0) {
					preview_img(this.files[0]);
					$("#preview").addClass("preview_on");

				} else {
					preview_el.innerHTML = '<span class="pic_text">預覽圖</span>';
				}
			});
			// totop的速度
			$("#pagetop").addClass("hide");
			$("#pagetop").click(function(e) {
				e.preventDefault();
				$("html,body").animate({
					scrollTop : 0,
				}, 600);
			});
			// 頁面下拉到某個位置才出現totop
			$(window).scroll(function() {
				if ($(window).scrollTop() > 200) {

					if ($("#pagetop").hasClass("hide")) {
						$("#pagetop").toggleClass("hide");
					}
				} else {

					$("#pagetop").addClass("hide");
				}
			});

			//點選預覽出現的位置數量提供選擇

			$("#btn").on("click", function() {
				makeSeat();
			});

			var makeSeat = function() {
				var k = 0;
				// var m = 0;
				var rentRow = parseInt($("#rentRow").val());
				var rentCol = parseInt($("#rentCol").val());

				$("#rent").text('');

				rentNo = 0;
				for (var i = 0; i < rentRow; i++) {

					k = k + 1;
					// m = -1;

					$("#rent")
							.append(
									`<div class='row' id='row-${"${i}"}' rentRow='${"${k}"}'></div>`);

					for (var j = 0; j < rentCol; j++) {
						rentNo++;
						// m = m + 1;
						$(`#row-${"${i}"}`)
								.append(
										`<div class='norent pointer' attr='${"${rentNo}"}' id='${"${i + 1}_${j + 1}"}'></div><input type="hidden" name="norent" value="_">`);
					}

					if (i < rentRow - 1) {
						$("#rent")
								.append(
										'<input type="hidden" name="norent" value=",">');
					}

				}
			};

			//選擇可開放租借攤位的顏色標記
			$("body").on("click", ".pointer", changeSeat);
			function changeSeat() {

				if ($(this).next().val() == "_") {
					$(this).addClass("rent_available");
					$(this).next().val("a");
				} else if ($(this).next().val() == "a") {
					$(this).removeClass("rent_available");
					$(this).next().val("_");
				}
				// a : 提供出租 _ :不提供出租

			}
			//判斷點選哪一個按鈕後去執行insertOrSaveFun(參數)動態更改$('#action').val('submit');
			var valArr = new Array;
			$("#btn_submit").on("click", function() {
				insertOrSaveFun(true); //STATUS = r0 (待審核)
			});
			$("#btn_save").on("click", function() {
				insertOrSaveFun(false); //STATUS = r3 (編輯中)
			});

			//點選按鈕後產生的出借資訊(位置字串、行數、列數)
			var insertOrSaveFun = function(isSubmit) {
				$('input[name=norent]').each(function(i) {
					valArr[i] = $(this).val();

				});
				var priv = valArr;
				var rentString = "";

				$.each(priv, function(key, val) {
					rentString += val;
				});
				$("#seatLong").val(rentString);

				var rowsNum = []; //橫列
				$(".row").each(function(idx, ele) {
					rowsNum.push($(ele).attr("rentRow"));
				});
				$("#rowsNum").val(rowsNum);

				var columnsNum = []; //直行

				$("#row-0 > .norent").each(function(idx, ele) {
					columnsNum.push($(ele).attr("attr"));

				});
				$("#columnsNum").val(columnsNum);

				var rent_available = [];
				$(".rent_available").each(function(idx, ele) {
					rent_available.push($(ele).attr("id"));
				});
				$("#rent_available").val(rent_available);

				if (isSubmit) {
					$('#action').val('submit');
				} else {
					$('#action').val('save');
				}
				if (rent_available
						&& rent_available.length != $('#rentCount').val()) {
					swal("", "請選擇剛好的攤位數量", "error");
					// 					alert('請選擇剛好' + $('#rentCount').val() + '數量');
					return;
				}
				console.log(rentString);
				console.log(rowsNum);
				console.log(columnsNum);
				console.log(rent_available);
				$('#form').submit();

			}

			//日期判斷
			function cheackdate() {
				var date_now = new Date();
				var year = date_now.getFullYear();
				var month = date_now.getMonth() + 1 < 10 ? "0"
						+ (date_now.getMonth() + 1) : (date_now.getMonth() + 1);
				var date = date_now.getDate() < 10 ? "0" + date_now.getDate()
						: date_now.getDate();
				//input min属性
				$("#startdate").attr("min", year + "-" + month + "-" + date);
			}
			$("#startdate").on("click", function() {
				cheackdate();
			})

			$("#startdate,#closedate").on(
					"blur",
					function() {
						$("#dayCount").val('');
						var date1 = $("#startdate").val();
						var date2 = $("#closedate").val();
						if (!date1 || !date2) {
							return;
						}
						var y1 = date1.split("-")[0];
						var m1 = date1.split("-")[1];
						var d1 = date1.split("-")[2];
						var time1 = new Date(y1, m1, d1);
						var y2 = date2.split("-")[0];
						var m2 = date2.split("-")[1];
						var d2 = date2.split("-")[2];
						var time2 = new Date(y2, m2, d2);
						var now = new Date();
						//             console.log(time1);
						//             console.log(now.getFullYear(),(now.getMonth()+1),now.getDate());
						var Difference_In_Days = (time2.getTime() - time1
								.getTime())
								/ (1000 * 3600 * 24) + 1;
						if (!Difference_In_Days || Difference_In_Days < 0) {
							swal("日期錯誤", "請再次輸入", "error");
							$("#startdate,#closedate").val("");
							return;
						}

						$("#dayCount").val(Difference_In_Days);

					});

			//如果是更新時帶出資料動態點選活動類型
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

			if (rowNum != null || colNum != null) {
				makeSeat();

			}
			;
			var rent_available = '${activityVO.available}'
			if (rent_available) {
				rent_availableArr = rent_available.split(",");
				jQuery.each(rent_availableArr, function(idx, val) {

					console.log(val);
					$("#" + val).get(0).click();
				});
			};
			
		});
	</script>
</body>

</html>