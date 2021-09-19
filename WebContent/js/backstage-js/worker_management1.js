$(function(){


      //刪除鍵綁定事件
    $("input[value='刪除']").on('click', function(){
        
        if(confirm("請確認是否刪除") == true){
            $(this).closest("form").submit();
        }else{
            console.log("取消");
        }
    
    })


})





