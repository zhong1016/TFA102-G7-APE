const path = document.location.pathname.substring(1);
const projectName = path.substring(0,path.indexOf("/"));

var do_deletion = function() {
	$.ajax({
	      url: "/APE/members/MemServlet", // 資料請求的網址
	      type: "GET", // GET | POST | PUT | DELETE | PATCH
	      data: "memloginid=loginOut",
	      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
	      success: 
	    	  function (data) {
				if( [data.check] == 1){
					console.log("1");
					$('#header').append('<span class="header-singin myMOUSE" id="Signin">'+ [data.name]+'&emsp;|&emsp;<span id="Signout" class="header-singin myMOUSE">登出</span></span>');				
				} else if([data.check] == 2){
					console.log("2");
					location.href='/APE/index.html';
				}
	     	 },
	      error: function (xhr) {
	        alert("伺服器忙碌中");
	      },
	    });
}

$('body').on('click', '#Signout', function() {
	var yes = confirm('Sign Up？');
	if (yes) {
		console.log('yes');
		do_deletion();
	} else {
	    alert(':D');
	}
}); 

$('body').on('click', '#Signin', function() {
	console.log('123')
	$.ajax({
	      url: "/APE/members/MemServlet", // 資料請求的網址
	      type: "GET", // GET | POST | PUT | DELETE | PATCH
	      data: "memloginid=checkLogin",
	      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
	      success: 
	    	  function (data) {
				if( [data.check] == 1){
					console.log("1");
					location.href=[data.url];			
				} else if([data.check] == 2){
					console.log("2");
					$('#header').append('<span class="header-singin"><a href="/APE/members/Member.jsp">登入</a>&emsp;|&emsp;<a href="/APE/members/Member.jsp">註冊</a></span>');
				}
	     	 },
	      error: function (xhr) {
	        alert("伺服器忙碌中");
	      },
	    });
}); 

//const ulElement= document.getElementById("beforeLogin");

$(document).ready(function () {
    $.ajax({
      url: "/APE/members/MemServlet", // 資料請求的網址
      type: "GET", // GET | POST | PUT | DELETE | PATCH
      data: "memloginid=checkLogin",
      dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
      success: 
    	  function (data) {
			if( [data.check] == 1){
				$('#header').append('<span class="header-singin myMOUSE" id="Signin">'+ [data.name]+'&emsp;|&emsp;<span id="Signout" class="myMOUSE">登出</span></span>');
			} else if([data.check] == 2){
				 $('#header').append('<span class="header-singin"><a href="/APE/members/Member.jsp">登入</a>&emsp;|&emsp;<a href="/APE/members/Member.jsp">註冊</a></span>');
//				console.log("2");	
//				const spanElement=document.createElement("span");
//				spanElement.setAttribute("class","header-singin");
//				
//				const ancherElement=document.createElement("a");
//				ancherElement.setAttribute("href", `/${projectName}/members/Member.jsp`);
//				ancherElement.innerText="登入 | ";
//				
//				const ancherElement2=document.createElement("a");
//				ancherElement2.setAttribute("href",	`/${projectName}/members/Member.jsp`);
//				ancherElement2.innerText="註冊";
//				
//				ancherElement.append(ancherElement2);
//				spanElement.appendChild(ancherElement);
//
//				ulElement.append(spanElement);
//				insertAfter(ulElement,spanElement );
			}
     	 },
      error: function (xhr) {
        alert("伺服器忙碌中");
      },
    });
});

function insertAfter(referenceNode, newNode) {
	referenceNode.parentNode.insertBefore(newNode, referenceNode.nextSibling);
  }