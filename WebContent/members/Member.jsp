<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="zh-TW">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>艾普藝森</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css" />
    <link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/member.css">
    <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>

<body>
    <%@ include file="/header.file" %>
    <c:if test="${not empty errorMsgs}">
		<section>
			<font style="color: red">錯誤訊息:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</section>
	</c:if>
    <section class="Mer_bg">
        <section class="mem_range">
            <img src="<%=request.getContextPath()%>/img/member/memFlower.png" alt="SER_FLW" class="service_rflower">
            <span class="service_but"></span>
            <section class="memleft">
                <section class="mempanel active" id="memlogin">
                    <form action="<%=request.getContextPath()%>/members/MemServlet" method="POST" enctype="multipart/form-data">
                    	<section class="center1">
	                    	<input type="hidden" name="redirctUrl" value="<%=request.getParameter("url")%>">
	                        <label for="MemId" class="input-label">登入帳號
	                            <input type="text" name="userId" placeholder="ID number" autocomplete="off">
	                       		<span class="mempop">請輸入身分證字號</span>
	                        </label>
	                        <label for="password" class="input-label">登入密碼
	                            <input type="password" class="password" name="userPassword" placeholder="Password">
	                        </label>
	                        <a href=".memPswdChange" class="input-label Forgotpassword">Forgot password?</a>   
	                        <section class="button1">         
		                        <label for="memlogin" class="input-label">
									<input type="hidden" name="memloginid" value="logInMem">
									<input type="submit" class="input-button" value="SING IN">						
		                        </label>
	                        </section>
                        </section>
                    </form>                  
                </section>
                <section class="mempanel" id="coplogin">
                    <form action="<%=request.getContextPath()%>/company/CopServlet" method="POST" enctype="multipart/form-data">
                    	<section class="center1">
	                        <label for="email" class="input-label">登入帳號
	                        	<input type="text" name="companyId" class="email" placeholder="Email" autocomplete="off">
	                        	<span class="mempop">請輸入統一編號</span>
	                        </label>
	                        <label for="password" class="input-label">登入密碼
	                        	<input type="password" name="companyPassword" class="password" placeholder="Password">
	                        </label>
	                        <a href=".copPswdChange" class="input-label fgpsd">Forgot password?</a>
	                        <section class="button1">
		                        <label for="memlogin" class="input-label">
									<input type="hidden" name="coploginid" value="logInCop">
									<input type="submit" class="input-button" value="SING IN">
		                        </label>
	                        </section>
                        </section>
                    </form>
                </section>
                <ul class="memlist">
                    <li class="active">
                        <a class="memcontrol" href="#memlogin"><img src="<%=request.getContextPath()%>/img/member/MemSignin-title.png" alt="membersignin"><span>登入會員</span></a>
                    </li>
                    <li>
                        <a class="memcontrol" href="#coplogin"><img src="<%=request.getContextPath()%>/img/member/MemSignin-title.png" alt="membersignin"><span>登入廠商</span></a>
                    </li>
                </ul>
            </section>
            <section class="memright">
                <section class="memse active" id="memSingUp">
                    <form action="<%=request.getContextPath()%>/members/MemServlet" method="POST" enctype="multipart/form-data">
	                    <section class="center1">
	                        <label for="MemId" class="input-label">登入帳號
	                            <input type="text" class="text" name="userId" placeholder="ID number" autocomplete="off">
	                        </label>
	                        <label for="password" class="input-label">登入密碼
	                            <input type="password" class="password"name="userPassword" placeholder="Password" >
	                        </label>
	                        <label for="email" class="input-label">登入信箱
	                            <input type="email" class="password" name="userEmail" placeholder="Email" autocomplete="off">
	                        </label>
	                        <section class="button1">
		                        <label for="memlogin" class="input-label">
									<input type="hidden" name="memloginid" value="SingupMem">
									<input type="submit" class="input-button" value="會員SING UP">
		                        </label>
	                        </section>
                        </section>
                    </form>
                </section>
                <section class="memse" id="copSignUp">
                    <form action="<%=request.getContextPath()%>/company/CopServlet" method="POST" enctype="multipart/form-data">
                    	<section class="center1">
	                        <label for="MemId" class="input-label">登入帳號
	                            <input type="text" class="text" name="companyId" placeholder="GUI number" autocomplete="off">
	                        </label>
	                        <label for="password" class="input-label">登入密碼
	                            <input type="password" class="password" name="companyPassword" placeholder="Password">
	                        </label>
	                        <label for="email" class="input-label">登入信箱
	                            <input type="email" class="password" name="companyEmail" placeholder="Email" autocomplete="off">
	                        </label>
	                        <section class="button1">
		                        <label for="memlogin" class="input-label">
									<input type="hidden" name="coploginid" value="SingupCop">
									<input type="submit" class="input-button" value="SING UP">
		                        </label>
		                    </section>
                        </section>
                    </form>
                </section>
                <ul class="memrightul">
                    <li class="active">
                        <a class="memrightli" href="#memSingUp"><img src="<%=request.getContextPath()%>/img/member/MemRegister.png" alt="register"><span>會員註冊</span></a>
                    </li>
                    <li>
                        <a class="memrightli" href="#copSignUp"><img src="<%=request.getContextPath()%>/img/member/MemRegister.png" alt="register"><span>廠商註冊</span></a>
                    </li>
                </ul>
                
            </section>
        </section>
    </section>
	<form class="forget" action="<%=request.getContextPath()%>/members/MemServlet" method="POST" enctype="multipart/form-data">
		<div class="memPswdChange" Style="display: none">
			<article>
				<label for="memlogin" class="input-label">
				 	<input type="text" name="userId" placeholder="ID number" autocomplete="off">
<!-- 				 	<input type="text" name="userPhone" placeholder="ID number" autocomplete="off"> -->
				 	<input type="text" name="userEmail" placeholder="Email" autocomplete="off">
					<input type="hidden" name="memloginid" value="forgetPasswordMem">
					<button class="input-button membtn">送出</button>
					<button type="button" class="btn_modal_close btnNew membtn">關閉</button>
				</label>
			</article>
		</div>               
	</form>
	<form class="forget" action="<%=request.getContextPath()%>/company/CopServlet" method="POST" enctype="multipart/form-data">
		<div class="copPswdChange" Style="display: none">
			<article>
				<label for="memlogin" class="input-label">
				 	<input type="text" name="companyId" placeholder="GUI number" autocomplete="off">
				 	<input type="text" name="companyEmail" placeholder="Email" autocomplete="off">
					<input type="hidden" name="coploginid" value="forgetPasswordCop">
					<button class="input-button membtn">送出</button>
					<button type="button" class="btnmodalclose btnNew membtn">關閉</button>
				</label>
			</article>
		</div>               
	</form>
    <%@ include file="/footer.file" %>
    <script src="<%=request.getContextPath()%>/js/header.js"></script>
    <script src="<%=request.getContextPath()%>/js/member/member.js"></script>
    <script src="<%=request.getContextPath()%>/js/member/memHeader.js"></script>
</body>

</html>