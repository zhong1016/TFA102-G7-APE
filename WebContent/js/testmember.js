var do_deletion = function () {
  $.ajax({
    url: "http://localhost:8081/APE/members/MemServlet", // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    data: "memloginid=loginOut",
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      console.log(data);
      if ([data.check] == 1) {
        console.log("1");
        // header.innerHTML +='<span class="header-singin">'+ [data.name] +'&emsp;|&emsp;<span id="Signout" href="#">登出</span></span>';
        $("#header").append(
          '<span class="header-singin">' +
          [data.name] +
          '&emsp;|&emsp;<span id="Signout" href="#">登出</span></span>'
        );
      } else if ([data.check] == 2) {
        console.log("2");
        location.href = "/APE/index.html";
      }
    },
    error: function (xhr) {
      alert("伺服器忙碌中");
    },
  });
};

$("body").on("click", "#Signout", function () {
  var yes = confirm("Sign Up？");
  if (yes) {
    console.log("yes");
    do_deletion();
  } else {
    alert(":D");
  }
});

$("body").on("click", "#Signin", function () {
  console.log("123");
  $.ajax({
    url: "http://localhost:8081/APE/members/MemServlet", // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    data: "memloginid=checkLogin",
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      if ([data.check] == 1) {
        console.log("1");
        location.href = [data.url];
      } else if ([data.check] == 2) {
        console.log("2");
        $("#header").append(
          '<span class="header-singin"><a href="/APE/members/Member.jsp">登入</a>&emsp;|&emsp;<a href="/APE/members/Member.jsp">註冊</a></span>'
        );
      }
    },
    error: function (xhr) {
      alert("伺服器忙碌中");
    },
  });
});

$(document).ready(function () {
  $.ajax({
    url: "http://localhost:8081/APE/members/MemServlet", // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    data: "memloginid=checkLogin",
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      if ([data.check] == 1) {
        console.log("1");
        $("#header").append(
          '<span class="header-singin">' +
          [data.name] +
          '&emsp;|&emsp;<span id="Signout" href="#">登出</span></span>'
        );
        test();
      } else if ([data.check] == 2) {
        console.log("2");
        $("#header").append(
          '<span class="header-singin"><a href="/APE/members/Member.jsp">登入</a>&emsp;|&emsp;<a href="/APE/members/Member.jsp">註冊</a></span>'
        );
        test();
      }
    },
    error: function (xhr) {
      alert("伺服器忙碌中");
    },
  });
});

function test() {
  var firstLi = document.querySelectorAll("header ul li");
  for (var i = 0; i < firstLi.length; i++) {
    firstLi[i].onmouseover = function () {
      if (this.querySelector("ul")) {
        var secUL = this.querySelector("ul");
        var liLength = secUL.querySelectorAll("li").length;
        secUL.style.height = 42 * liLength + "px";
      }
    };
    firstLi[i].onmouseout = function () {
      if (this.querySelector("ul")) {
        var secUL = this.querySelector("ul");
        secUL.style.height = "0px";
      }
    };
  }
}
// function insertAfter(referenceNode, newNode) {
// 	referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
//   }
