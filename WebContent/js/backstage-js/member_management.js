$(function(){
		
    //li總筆數
    let total = $(".table-row").length;
//	   console.log(total);	   
    //a0筆數
    let a0 = $(".table-row.a0").length;
//	   console.log(a0);
    // a1筆數
    let a1 = $(".table-row.a1").length;
//	   console.log(a1);
    //a2筆數
    // let a2 = $(".table-row.a2").length;
//	   console.log(a2);
    //a3筆數


 

 let origin = $("option").val();
 let $origin =$(".table-row" + "." + origin);

 
 $origin.each(function(){
     $(this).removeClass(origin);
 });
 
 
 // option變動時執行
$("select").on('change', function(){
    
    //當option變動時  抓取目前頁面上li列表的id	   
    if($("li:visible")[1]){	   
        var $origin1 = $("li:visible");
        var origin1 =  $origin1[1].getAttribute("id");
        $origin1.each(function(){
            $(this).addClass(origin1);
        });
    }
//	   console.log($origin1);
    //抓取目前可視範圍之li的id
//	   let origin1 =  $origin1[1].getAttribute("id");
//	   console.log(origin1);
    //將可視範圍li 將上display none
//	   $origin1.each(function(){
//		   $(this).addClass(origin1);
//	   });	   
    //option變動得值
    let change = this.value;
    // console.log(change);
    
    switch(change){
        case 'a0':
            for(let i = 0 ; i < a0  ; i++ ){
//	   			console.log($(".table-row"));
                $($(".table-row")[i]).removeClass("a0");
            }
            break;
        case 'a1':
            for(let i = a0 ; i <= a0 + a1 ; i++ ){
//	   			console.log($(".table-row"));
                $($(".table-row")[i]).removeClass("a1");
            }
            break;
    }
   
})



let $tableA0 =$(".table-row");
for(let i = 0; i < a0 ; i++){
    // $($tableA0[i]).find("span");
    $($($tableA0[i]).find("span")).on('click', function(){
        // console.log("幹");
        console.log($($(this).closest("li")).find("form"))
        // console.log($($(this).closest("li")).find("form"));
        $($(this).closest("li")).find("form").submit();		   
    })
    
}

})