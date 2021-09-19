var firstLi = document.querySelectorAll("header ul li");
for (var i = 0; i < firstLi.length; i++) {
    firstLi[i].onmouseover = function () {
        if (this.querySelector("ul")) {
            var secUL = this.querySelector("ul");
            var liLength = secUL.querySelectorAll('li').length;
            secUL.style.height = 38 * liLength + 'px';
        }
    }
    firstLi[i].onmouseout = function () {
        if (this.querySelector("ul")) {
            var secUL = this.querySelector("ul");
            secUL.style.height = '0px';
        }
    }
}

//========================== 幻燈片特效==========================================
var myIndex = 0;
carousel();

function carousel(){
    var i;
    var x = document.getElementsByClassName("slider-banner")
    // console.log(x);
    for(i=0;i<x.length;i++){
        x[i].style.display="none";
    }
    myIndex ++;
    if(myIndex>x.length){myIndex=1}
    x[myIndex-1].style.display="block";
    setTimeout(carousel,3000);  
}

//====================購物車===============================
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

