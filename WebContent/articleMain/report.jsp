<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%
	Integer no = (Integer) request.getAttribute("No");
	System.out.println("這是一個值:" + request.getAttribute("No"));
	request.setAttribute("no", request.getAttribute("No"));
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>檢舉視窗</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/article/report0712.css">
</head>
<body>
	<div class="report_div">
		<div class="report_content">請選擇檢舉內容</div>
		<div class="choose_div">

			<div class="type_div">
				<input type="checkbox" name="rep_content" class="art" id="repc"
					value="文章"> <label id="label_art" for="repc">文章</label> <input
					type="checkbox" name="rep_content" class="msg" id="repmsg"
					value="留言"> <label id="label_msg" for="repmsg">留言</label>
			</div>

		</div>

		<div class="type_div2">
			<input type="checkbox" id="vehicle1" name="vehicle" value="不雅言論">
			<label for="vehicle1"> 不雅言論</label><br> <input type="checkbox"
				id="vehicle2" name="vehicle" value="歧視/惡意言論"> <label
				for="vehicle2"> 歧視/惡意言論</label><br> <input type="checkbox"
				id="vehicle3" name="vehicle" value="政治色彩濃厚"> <label
				for="vehicle3"> 政治色彩濃厚</label><br> <input type="checkbox"
				id="vehicle4" name="vehicle" value="違法行為"> <label
				for="vehicle4"> 違法行為</label><br> <input type="checkbox"
				id="vehicle5" name="vehicle" value="其它"> <label
				for="vehicle5"> 其它</label><br> 
				<input style="display: none" type="text" id="MSGNO" name="rptmsgno"
				value="<%=request.getAttribute("No")%>">
		</div>

		<div class="msg_div">
			<textarea name="ed" id="re_msg" cols="30" rows="10"
				placeholder="請輸入檢舉說明..."></textarea>
		</div>
		<div class="check">

			<button id="sub" type="submit">送出</button>
			<button id="ret" type="submit">退回</button>

		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/articleJS/report.js"></script>
</body>
</html>