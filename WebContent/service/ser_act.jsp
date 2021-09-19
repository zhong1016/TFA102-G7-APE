<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>艾普藝森</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/serAll.css">
    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>

<body>
    <select class="custom_select type">
        <option value="1">手作文創</option>
    </select>
    <div class="serleaf"></div>
    <div id="serActList" >
    </div>
    <script src="<%=request.getContextPath()%>/js/index.js"></script>
    <script src="<%=request.getContextPath()%>/js/service/ser_act.js"></script>
</body>

</html>