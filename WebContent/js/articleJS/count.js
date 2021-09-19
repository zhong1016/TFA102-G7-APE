
$(".push").on('click' , function(){
	var url = location.href;
	var b;
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
  url : "http://localhost:8081/APE/countAPI" ,
  method : 'post' ,
  data : {name :  b} ,
  dataType : 'json' ,
  success : function(res){
      alert('成功')           
//      $('.type').html('文章類別:'+ (res.a1))//文章類別
//      $('.time').html(res.a3)//時間
//      $('.main').html("文章主題:"+(res.a2))
//      $('.main_article').html((res.a4))
//      console.log('res',res)
//      location.replace("http://localhost:8081/APE/main/view_post.jsp");
//      $('.form').attr("action", "http://localhost:8081/APE/main/view_post.jsp");.
//      $(".form").submit();

  },
  error : function(res){

      console.log('error')
      console.log(res.type1)
  },
  complete : function(){
	  alert('推薦成功');
  }
})



})


   
    
    


