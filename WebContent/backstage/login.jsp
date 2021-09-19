
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入頁面</title>

<link href="https://fonts.googleapis.com/css2?family=Kosugi+Maru&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/backstage-css/login.css" />
</head>
<body>
    <div class="main">
      <div class="loginpage">
        <div class="login_content">
          <div class="title">
            <img src="<%=request.getContextPath()%>/img/backstage-img/APE3.svg" alt="" />
            <p>艾普藝森</p>
          </div>
          <div>
            <p>管理員登入</p>
          </div>

          <div class="loginsection">
            <form action="login" method="post">
              <div>
                <label for="account">帳號:</label>
                <input id="account" type="text" pl
                aceholder="請輸入帳號" name="account"/>
              </div>
              <div class="on" id="account_alert">
                <svg
                  fill="currentColor"

                  height="16px"
                  viewBox="0 0 24 24"
                  xmlns="https://www.w3.org/2000/svg"
                >
                  <path
                    d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"
                  ></path>
                </svg>
                <p>請輸入您的帳號</p>
              </div>

              <div>
                <label for="password">密碼:</label>
                <input id="password" type="password" placeholder="請輸入密碼"  name="password"/>
              </div>
              <div class="on" id="password_alert">
                <svg
                  fill="currentColor"
                  width="16px"
                  height="16px"
                  viewBox="0 0 24 24"
                  xmlns="https://www.w3.org/2000/svg"
                >
                  <path
                    d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"
                  ></path>
                </svg>
                <p>請輸入您的密碼</p>
              </div>
              <button type="button" >登入</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/backstage-js/login.js"></script>
  </body>
</html>