$(document).ready(function() {
    $('.service_left li').click(function() {
        $(this).find('a').toggleClass('on').parent().siblings().find('a').removeClass('on');
    })
    $('.ser_msg').click(function() {
        $('.service_left li').find('a').eq(1).toggleClass('on').parent().siblings().find('a').removeClass('on');
    })
    $('.ser_qna').click(function() {
        $('.service_left li').find('a').eq(2).toggleClass('on').parent().siblings().find('a').removeClass('on');
    })
    $('.ser_pro').click(function() {
        $('.service_left li').find('a').eq(3).toggleClass('on').parent().siblings().find('a').removeClass('on');
    })
})