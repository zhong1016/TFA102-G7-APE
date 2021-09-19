var b;
var count;

$('.push').on('click' , function(){

    $.ajax({
  	  url : "http://localhost:8081/APE/countAPI" ,
  	  method : 'post' ,
  	  data : {count : b} ,
  	  dataType : 'json' ,
  	  success : function(res){
  		  if(res == false){
  			  console.log("這是回應回來的Boolean" + res);
  			  alert("非會員無法按讚");
  			  
  			  if(window.confirm("請問是否要登入?")){
  				window.location.replace("http://localhost:8081/APE/members/Member.jsp");
  				return;
  			  }else{
  				 alert("僅限瀏覽")
  				return;
  			  }
  			  
  			  
  			  
  		  }
  			  
  	      alert('推薦成功')           
  	  },
  	  error : function(res){
  		 alert('推薦成功') 
  
  	  },

  	})

})



$(document).ready(function(){
	
	console.log("value="+$("#subinput"))
	var url = location.href;
	
	if(url.indexOf('?')!=-1)
	{
	    var ary1 = url.split('?');
	    var ary2 = ary1[1].split('&');

	    console.log(url)
	    console.log(ary2,ary1);
	    var ary3 = ary2[0].split('=');
	    b=ary3[1];
	    console.log(ary3[1]);
	    }
	$.ajax({
  url : "http://localhost:8081/APE/viewPostAPI" ,
  method : 'post' ,
  data : {name :  b} ,
  dataType : 'json' ,
  success : function(res){
      $('.type').html('文章類別:'+ (res.a1))// 文章類別
      $('.time').html(res.a3)// 時間
      $('.main').html("文章主題:"+(res.a2))
      $('.main_article').html((res.a4))
//      $('.author').html(("作者:"+res.a5))
      console.log('a5='+(res.a5))
      console.log('a4='+(res.a4))
  },
  error : function(res){

      console.log('error')
      console.log(res.type1)
  },
})
$("#msgbtn").on('click' , function(){
	var abc = $('#subinput').val(); // hidden值
	var def = $('#msg_input').val(); //輸入值
    var abcde = Date.now();
    var newDate = new Date(abcde);
    var year = newDate.getFullYear();
    var month = newDate.getMonth() +1 ;
    var day = newDate.getDate();
    var dayHours = newDate.getHours();
    var dayMin = newDate.getMinutes();
    var daySec = newDate.getSeconds();
    var finalytime = (year+"-"+month+"-"+day+" "+dayHours+":" +dayMin +":" + daySec);
    
	
	
	  // 留言
	
	
	 let str = `
	 <div class="msg_ch">
	            
	 <div class="main_msg">
	 <div class="msg_author">
	 你:
	 </div>
	 <div class="author_msg">
		${def}
	 </div>
	 <div class="msg_time">
	 ${finalytime}
	 </div>
	 </div>
	 <div class="report_div">
	 <button type="submit" id="report" class="rpt" >檢舉</button>
	 </div>
	 `
	
	let dataobj1 = {
		"k1" : abc ,
		"k2" : def
	}
	
    let a = $("#msg_input").val().trim();
    if(a != ""){
        $("#msg_div").append(str);
        $('#msg_input').val("");
    }else{
        alert('請輸入留言');
        $('#msg_input').val("");
        return;
    }
    
    
    $.ajax({
    	  url : "http://localhost:8081/APE/amessageAPI" ,
    	  method : 'post' ,
    	  data : dataobj1 ,
    	  dataType : 'json' ,
    	  success : function(res){
  			  if(window.confirm("請問是否要登入?")){
    				window.location.replace("http://localhost:8081/APE/members/Member.jsp");
    				return;
    			  }else{
    				 alert("僅限瀏覽")
    				return;
    			  }
    			  
    		  
    		  
    	      alert('成功');           
    	  } ,
    	  error : function(res){

    	      console.log('error')
    
    	  }
    	})
    
});


	
	

    


$('.report_btn').on('click' ,  function(){
    $.ajax({
  	  url : "http://localhost:8081/APE/checkReportAPI",
  	  method : 'post' ,
  	  data : null ,
  	  dataType : 'json' ,
  	  success : function(res){
  		  console.log(!res);
	  		if(!res){			  
	  		  	if(window.confirm("請問是否要登入?")){
	  				window.location.replace("http://localhost:8081/APE/members/Member.jsp");
	  				return;
	  			  }else{
	  				alert('非會員無法檢舉');
	  			  }
			}else{
				window.open('http://localhost:8081/APE/articleMain/report.jsp' , 'height=300px , width = 200px')
			}
  	               
  	  } ,
  	  error : function(res){

  	      console.log('error')
  
  	  }
  	})
	
	
});

//
//$('.rpt').on('click' ,  function(){
//	   $.ajax({
//		  	  url : "http://localhost:8081/APE/checkReportAPI" ,
//		  	  method : 'post' ,
//		  	  data : null ,
//		  	  dataType : 'json' ,
//		  	  success : function(res){
//		  		  console.log(!res);
//		  		if(!res){			  
//		  		  	if(window.confirm("請問是否要登入?")){
//		  				window.location.replace("http://localhost:8081/APE/members/Member.jsp");
//		  				return;
//		  			  }else{
//		  				alert('非會員無法檢舉');
//		  			  }
//				}else{
//					window.open('http://localhost:8081/APE/articleMain/report.jsp' , 'height=300px , width = 200px')
//				}
//		  		
//		  		
//		  		  
//		  	  } ,
//		  	  error : function(res){
//
//		  	      console.log('error')
//		  
//		  	  }
//		  	})
//});

});


//
//$("body").on("click",".rpt",function(){
//    window.open('http://localhost:8081/APE/main/report.jsp' , 'height=300px , width = 200px')
//});
