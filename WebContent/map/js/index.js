var firstLi = document.querySelectorAll("header ul li");
        var items = document.getElementsByClassName("item");
        var points = document.getElementsByClassName("point");
        var goPreBtn = document.getElementById("goPre");
        var goNextBtn = document.getElementById("goNext");
        var time = 0;
        var index = 0;

        var clearActive = function () {
            for (var i = 0; i < items.length; i++) {
                items[i].className = "item";
            }
            for (var i = 0; i < points.length; i++) {
                points[i].className = "point";
            }
        }
        var goIndex = function () {
            clearActive();
            items[index].className = "item active";
            points[index].className = "point active";
        }
        var goNext = function () {
            if (index < 4) {
                index++;
            } else {
                index = 0;
            }
            goIndex();
            time = 0;
        }
        var goPre = function () {
            if (index == 0) {
                index = 4;
            } else {
                index--;
            }
            goIndex();
            time = 0;
        }
        goNextBtn.addEventListener('click', function () {
            goNext();
        })
        goPreBtn.addEventListener('click', function () {
            goPre();
        })
        for (var i = 0; i < points.length; i++) {
            points[i].addEventListener('click', function () {
                var pointIndex = this.getAttribute('data-index');
                index = pointIndex;
                goIndex();
                time = 0;
            })
        }
        setInterval(function () {
            time++
            if (time == 25) {
                goNext();
                time = 0;
            }
        }, 100)

        
        for (var i = 0; i < firstLi.length; i++) {
            firstLi[i].onmouseover = function () {
                if (this.querySelector("ul")) {
                    var secUL = this.querySelector("ul");
                    var liLength = secUL.querySelectorAll('li').length;
                    secUL.style.height = 42 * liLength + 'px';
                }
            }
            firstLi[i].onmouseout = function () {
                if (this.querySelector("ul")) {
                    var secUL = this.querySelector("ul");
                    secUL.style.height = '0px';
                }
            }
        }