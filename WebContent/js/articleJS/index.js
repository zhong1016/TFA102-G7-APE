var firstLi = document.querySelectorAll("header ul li");


        
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


