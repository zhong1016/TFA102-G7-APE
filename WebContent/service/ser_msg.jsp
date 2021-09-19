<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/serAll.css">
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>

<body>


	<p class="serleaf"></p>
	<form>
		<label class="input-label">反應類型：
			<input class="" type="text" name="servName" placeholder="問題類型（例：活動報名問題）" autocomplete="off"/><br />
		</label>
		<label class="input-label">反應內容：
			<input class="" type="text" name="servMain" placeholder="問題詳述" autocomplete="off"/><br />
		</label>
		<label class="input-label">回覆信箱：
			<input class="" type="text" name="servMsg" placeholder="Email" autocomplete="off"/><br />
		</label>
		<label class="input-label">問題照片：
			<input type="file" class="file locked" accept="image/*,.pdf" name="userPic" disabled>
		</label>
		<input type="hidden" name="memloginid" value="serInsert">
		<input type="submit" value="送出">
	</form>
	<p class="serleaf"></p>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_act.js"></script>
</body>

</html>
