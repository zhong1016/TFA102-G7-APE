var mySwiper = new Swiper('.swiper-container',{
    loop:true,
    autoplay: {
      delay: 2000,
    },
    pagination :{
      el: '.swiper-pagination',
      clickable :true,
    },
    navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  })
  mySwiper.el.onmouseover = function(){
    mySwiper.autoplay.stop();
  }
  mySwiper.el.onmouseout = function(){
    mySwiper.autoplay.start();
  }