$(function (){


    $("a.buyer_click").on('click', function(){
        $("div.body_buyer_list_all").toggleClass("on");
    })
    $("a.seller_click").on('click', function(){
        $("div.body_seller_list_all").toggleClass("on");
    })


})
