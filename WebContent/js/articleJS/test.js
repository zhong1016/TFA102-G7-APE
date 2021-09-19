$(function(){

		
		$("tr.test").on('click',function(){
			

			$($(this).closest("tr")).find("form").submit();
			
		})
	
	
	
	


})