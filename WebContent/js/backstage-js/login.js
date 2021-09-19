$(function(){

	
	$(document).on("keydown",function(e){
		if(e.which == 13){
			$("button").click();		
		}
	})
	
	
    $("button").on("click", function(){
    
        if($("#password").val().trim() == ""){
            $("#password_alert").removeClass("on");
            $("#password").addClass("alert");
        }else{
            $("#password_alert").addClass("on");
            $("#password").removeClass("alert");
        }
        if($("#account").val().trim() == ""){
            $("#account_alert").removeClass("on");
            $("#account").addClass("alert");
        }else{
            $("#account_alert").addClass("on");
            $("#account").removeClass("alert");
        }
        
        
        
        if($("#password").val().trim() !== "" && $("#account").val().trim() !== ""){
            let account = $("#account").val().trim();
            let password =$("#password").val().trim();
            let data = {
            	'account':account,
            	'password':password        		
            }
            $.ajax({
                url: "/APE/login",           // 資料請求的網址
                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                data: data,                  // 傳送資料到指定的 url
                dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
                timeout: 0,                   // request 可等待的毫秒數 | 0 代表不設定 timeout
                success: function(data){      // request 成功取得回應後執行

                	if(data.verify == 'denied'){
                		alert("帳號或密碼錯誤,請重新輸入")
                		$("#password").val("");
                		$("#account").val("");
                	}else if(data.verify =="access"){
                		alert("登入成功,即將為您跳轉頁面");
                		window.location.href='/APE/backstage/homepage.jsp';
                	}              	
                },
                error: function(xhr){         // request 發生錯誤的話執行
                  alert("AJAX失敗");
                },
                complete: function(xhr){      // request 完成之後執行(在 success / error 事件之後執行)
                }
              });
        }
             
        
    })
    
    
    



})