
$(document).ready(function(){
	
	    $.ajax({
	        method: 'post' ,
	        url : "http://localhost:8081/APE/hotTopicAPI" ,
//	        data : null ,
	        dataType: 'json',
	        success: function(res){
	        	console.log(res);
	            $('.a11').html(res.a1)
	            $('.a21').html(res.a2)
	            $('.a31').html(res.a3)
	            $('.a41').html(res.a4)
	            $('.a51').html(res.a5)
	            $('.a61').html(res.a6)
	            $('.a71').html(res.a7)
	            $('.a81').html(res.a8)
	            $('.a91').html(res.a9)
	            $('.a101').html(res.a10)
	            $('.a111').html(res.a11)
	            $('.a121').html(res.a12)
	            $('.a131').html(res.a13)
	            $('.a141').html(res.a14)
	            $('.a151').html(res.a15)
	            $('.a161').html(res.a16)
	            $('.a171').html(res.a17)
	            $('.a181').html(res.a18)
	            $('.a191').html(res.a19)
	            $('.a201').html(res.a20)
	            
	        },
	        error: function(res){
	            console.log("失誤")
	            console.log(res)
	        }
	    });  
})
function gonext(value){
	console.log('gonext')
	
	var a=value.children[3].children[0].innerHTML;
		console.log(a);
	$('#aaa').val(a);
	 $('.form').attr("action", "http://localhost:8081/APE/articleMain/view_post.jsp?"+a);
     $(".form").submit();
	}


$('.tfabtn').on('click' , function(){
	$('.btnform').submit();
})