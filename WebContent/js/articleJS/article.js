
//前端邏輯操控
/*
var d = document.getElementById('editor1'); 
      $('#subbtn').on('click' , function(){
        var data = CKEDITOR.instances.editor1.getData();
        console.log(data);
        if(data != ""){
          if((window.confirm('確認送出嗎?')) == true){
                alert('送出成功')
                window.close();
            }else{
                alert('請再度確認');
            }
        }else{
          alert('請輸入文章內文')
        }
           
      });

      $('#return').on('click' , function(){
            if((window.confirm('確認離開當前頁面嗎?')) ==true ){
              if((window.confirm('離開後，已編輯將會消失，確認離開嗎?')) == true){
                window.close();
              }else{
                alert('請繼續編輯')
              }
            }else{
              alert('請繼續編輯');
            }

      });
*/

//取值

var type ;
var area = document.getElementById('post_type').value;
var text1 = document.getElementById('text')
var test1 = document.getElementById('editor1')
var str = "";
var send_obj;
var area_value
var s1 ; 
var s4 ;



//取得主題名稱值
function main() {
   s1 = CKEDITOR.instances.editor1.getData();



//以下取SELECT的值
var options=$("#post_type option:selected").val();
var s3 = options;
 s4 = text.value;
var ot = $("#post_area_type option:selected").val();
var s2 = ot ;

  send_obj = {
      "t1" : s1 ,   //文章內文
      "t2" : s2 ,   //文章區域
      "t3" : s3 ,   //文章類別
      "t4" : s4     //文章主題
  }
//   console.log(send_obj);
}

//SELECT值取結束


$('#subbtn').on('click' , function(){
    main() //取主題值
    console.log(send_obj);
    
    
    
    if(s4 == ""){
    	alert("請輸入文章主題");
    	return;
    }
    
    if(s1 == ""){
    	alert("請輸入文章內文");
    	return;
    }
    
    
    
    
    //ajax

    
    $.ajax({
        method : "post",
        url : "http://localhost:8081/APE/articleAPI" ,
        data : send_obj,
        dataType : 'json',
        success : function () {
            
        } ,

        error : function(){
    
        } ,
        
        complete : function(){
            alert('送出成功')
            window.location.replace("http://localhost:8081/APE/articleMain/fourm.jsp");
        }

    });
    
});
