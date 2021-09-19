let shopcar =$(".shop")

shopcar.on("click",function(){
   
    $("#car").css("display", "block");

});

$("#shoplogo").mouseout(function () {
    $("#car").css("display", "none");
  });
$("#shoplogo").mouseover(function () {
    $("#car").css("display", "block");
  });

function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();

$(function(){
	


//點選出貨按鈕執行事件
//let shipment= $(".tdbutton").each(function(){
//	$(this).on('click',function(){
//		
//	})
//	
//})

})



