//變數宣告(後面用的到)
var value ;
var array_value = new Array();
var array_title = new Array();
var data_obj
var title_obj
var JSONdata 
var textarea
var msgno

// 檢舉類別→取值放入
function check_value(){
    var id = document.getElementsByName('vehicle');
        
    // 取checkBox的值
    for(var i = 0 ; i < id.length ; i++){
        if(id[i].checked){
            array_value.push(id[i].value);   
        }else{
            array_value.push("");
        }
     }

     // 標題值
     var id_title = document.getElementsByName('rep_content');

     for( let z = 0 ; z< id_title.length ; z++){
         if(id_title[z].checked){
             array_title.push(id_title[z].value);
         }else{
             array_title.push("");
         }
     }
     textarea =  $("#re_msg").val();

     if((textarea.trim()) == ""){
        textarea = ""
     }
  
     console.log("這玩意是:" + $("#MSGNO").val());
     // checkBox的val放入object
    data_obj = {
        "a1" : array_value[0],
        "a2" : array_value[1],
        "a3" : array_value[2],
        "a4" : array_value[3],
        "a5" : array_value[4],
        "a6" : array_title[0],
        "a7" : array_title[1],
        "a8" : textarea , // 檢舉內容
        "a9" : $("#MSGNO").val()
    };

   

    // JSONdata = JSON.stringify(data_obj); //轉成JSON格式
};  




    
$('#ret').on('click' , function(){
    if((window.confirm('確認離開嗎?')) == true){
        window,close();
    }else{
        alert('請再度確認');
        }

});


$('#sub').on('click' , function(){
    check_value();
    console.log("+===============")
    console.log(data_obj)
    console.log("+===============")
        
    if((window.confirm('確認送出嗎?')) == true){
    }else{
        alert('請再度確認');
        window.location.reload();
        return;
    }



        if(array_title[0] == "" && array_title[1] == ""){
            alert('請勾選檢舉主題')
            window.location.reload(); // 刷新頁面的js
            return
        };

        if(array_value[0] == "" && array_value[1] == "" && array_value[2] == "" && array_value[3] == "" && array_value[4] == ""){
            alert('請勾選檢舉內容')
            window.location.reload(); // 刷新頁面 js功能
            return;
        }else{
            
            
            $.ajax({
                method : 'post',
                url : 'http://localhost:8081/APE/reportAPI',
                data :  data_obj,title_obj,
                dataType : 'json', 
                success : function(){
                   alert('成功囉')
                    // console.log("res" , res)
                },
                error : function(xhr){
                    console.log('error');
                    console.log(xhr);  
                },
                complete : function(){
                    alert('已送出回應')
                    window.location.replace("http://localhost:8081/APE/articleMain/fourm.jsp");
                    console.log('這是值:' +$("#msgno").val())
                } 
                });

                    
        }
        
  
          
});
