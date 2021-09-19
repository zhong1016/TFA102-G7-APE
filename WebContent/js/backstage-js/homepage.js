$(function(){

    $(document).on("keydown", function (e) {
        if (e.which == 13) {
            $("div.text_button button").click();
        } 
    });
    // console.log(mydate.toLocaleString());
    $("div.text_button button").on('click', function(){
        let text_area = ($("div.text_area textarea").val()).trim();
        
        if(text_area != ""){
        	let mydate = Date.now();

            let data = {
                "content":text_area,
                "time":mydate
            }
            // console.log(data.content);
            // console.log(data.time);
                
            // $("div.message_board table")
            // $("div.message_board table").append(table_content);
            $("div.text_area textarea").val("");

            $.ajax({
                url: "/APE/Homepage",           // 資料請求的網址
                type: "POST",                  // GET | POST | PUT | DELETE | PATCH
                data: data,                  // 傳送資料到指定的 url
                dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
                timeout: 0,                   // request 可等待的毫秒數 | 0 代表不設定 timeout
               
                success: function(data){      // request 成功取得回應後執行
                	
                	 let table_content = `<tr class="table_content" >
                         <td>${data.nickname}</td>
                         <td>${data.content}</td>
                         <td>${data.time}</td>
                     </tr>`;
                	 $("div.message_board table").append(table_content);
                	               	 
                },
                error: function(xhr){         // request 發生錯誤的話執行
                  alert("AJAX失敗");
                },
                complete: function(xhr){      // request 完成之後執行(在 success / error 事件之後執行)
                }
              });
         }

    });

})