//var firstLi = document.querySelectorAll("header ul li");
//console.log(firstLi.length)
//for (var i = 0; i < firstLi.length; i++) {
//    firstLi[i].onmouseover = function () {
//        if (this.querySelector("ul")) {
//            var secUL = this.querySelector("ul");
//            var liLength = secUL.querySelectorAll('li').length;
//            secUL.style.height = 42 * liLength + 'px';
//        }
//    }
//    firstLi[i].onmouseout = function () {
//        if (this.querySelector("ul")) {
//            var secUL = this.querySelector("ul");
//            secUL.style.height = '0px';
//        }
//    }
//}

//測試連結
var alleximg0 = '/APE/img/index-img/5.jpg';
// 測試文章
var article0 = '塩田千春：顫動的靈魂';


// 輪播
var container1 = new Vue({
    el:'#container1',
    data:{
        hi:'Hello Vue',
        wrapperimg:[
            '/APE/img/index-img/1.jpg',
            '/APE/img/index-img/2.jpg',
            '/APE/img/index-img/3.jpg',
            '/APE/img/index-img/4.jpg',
            '/APE/img/index-img/5.jpg',
        ],
    },
});


// 活動專區
var exhibition = new Vue({
    el:'#exhibition',
    data:{
        YO:'Hello Vue',
        alleximg:
            [
            alleximg0,
            'http://localhost:8081/APE/img/index-img/5.jpg',
            'http://localhost:8081/APE/img/index-img/5.jpg',
            'http://localhost:8081/APE/img/index-img/5.jpg',
            'http://localhost:8081/APE/img/index-img/5.jpg',
        ],
    }
})

// 討論區
var article = new Vue({
    el:'#article',
    data:{
        alltime:{
            time:[
                '2021-09-05',
                '2021-09-04',
                '2021-09-02',
                '2021-08-31',
                '2021-08-28']},
        allarticle:{
            article:[
                article0,
                '國美館推免費線上資源 教師教學免煩惱',
                '奈良美智特展',
                '新樂園《巢》煙花宇宙 獨立影音計畫展',
                '你我不住在同一星球上']},
    },
})


