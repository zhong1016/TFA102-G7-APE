<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>艾普藝森</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
    <link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/service/service.css">
    <!-- <link rel="stylesheet" href="/css/SER_ALL.css"> -->
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>

<body>
    <%@ include file="/header.file" %>
    <section class="bg_img">
        <section class="bg_range">
            <img src="<%=request.getContextPath()%>/img/service/flower.png" alt="SER_FLW" class="service_rflower">
            <span class="service_but"></span>
            <section class="service_left">
                <ul>
                    <li>
                        <a class="ser on" href="<%=request.getContextPath()%>/service/ser_act.jsp" target="insideme">活動查詢</a>
                    </li>
                    <li>
                        <a class="ser" href="<%=request.getContextPath()%>/service/ser_msg.jsp" target="insideme">意見回饋</a>
                    </li>
                    <li>
                        <a class="ser" href="<%=request.getContextPath()%>/service/ser_qna.jsp" target="insideme">常見問答</a>
                    </li>
                    <li>
                        <a class="ser" href="<%=request.getContextPath()%>/service/ser_pro.jsp" target="insideme">贈品兌換</a>
                    </li>
                </ul>
            </section>
            <section class="service_right">
                <iframe name="insideme" class="insideme" src="<%=request.getContextPath()%>/service/ser_act.jsp"></iframe>
            </section>
        </section>
<%--         <section class="icon"><img src="<%=request.getContextPath()%>/img/APE3.svg" alt=""><span class="tooltip">May I help you?</span></section> --%>
    </section>
    <%@ include file="/footer.file" %>
    

    <script src="<%=request.getContextPath()%>/js/index.js"></script>
    <script src="<%=request.getContextPath()%>/js/service/service.js"></script>
</body>

</html>