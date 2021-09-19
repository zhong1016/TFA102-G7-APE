
$(function () {
    //宣告
    var preview_el = document.getElementById("preview");
    var p_file_el = document.getElementById("p_file");

    var preview_img = function (file) {
        var img_node = document.createElement("img");
        var reader = new FileReader(); // 用來讀取檔案
        reader.addEventListener("load", function () {
            // console.log(reader.result);
            let img_node = document.createElement("img");
            img_node.setAttribute("src", reader.result); // <img src="abdafaewre">
            img_node.setAttribute("class", "preview_img"); // <img src="abdafaewre" class="preview_img">
            preview_el.innerHTML = '';
            preview_el.append(img_node);
        });
        reader.readAsDataURL(file); // 讀取檔案
    };

    p_file_el.addEventListener("change", function (e) {
        if (this.files.length > 0) {
            preview_img(this.files[0]);
            $("#preview").addClass("preview_on");
        } else {
            preview_el.innerHTML = '<span class="pic_text">預覽圖</span>';
        }
    });
    $("#pagetop").addClass("hide");
    $("#pagetop").click(function (e) {
        e.preventDefault();
        $("html,body").animate(
            {
                scrollTop: 0,
            },
            600
        );
    });

    $(window).scroll(function () {
        if ($(window).scrollTop() > 300) {
            if ($("#pagetop").hasClass("hide")) {
                $("#pagetop").toggleClass("hide");
            }
        } else {
            $("#pagetop").addClass("hide");
        }
    });


    var $cart = $('#selected-rent'); //選租
    var maparray = $("#rent_available").val().split(",");
    var rowArr = $("#rent_rows").val().split(",");
    var colArr = $("#rent_columns").val().split(",");
    var reserArr = $("#reservation").val().split(",");
    var flag = 1; // 預定攤位數量
    var firstSeatLabel = 1;
    var dayCount = parseInt($("#dayCount").text());//活動總天數
    var rentPrice = parseInt($("#rentPrice").text());//攤位價格
    console.log(maparray);
    var sc = $('#seat-map').seatCharts({

        map: maparray,
        naming: {
            top: false,
            left: false,
            rows: rowArr,
            columns: colArr,
            getLabel: function (character, row, column) {
                return row + '_' + column;
            }
        },
        legend: { //定義圖例
            node: $('#legend'),

            items: [
                ['a', 'available', '可選租'],
                ['a', 'unavailable', '不出租'],
            ]
        },
        click: function () { //點擊事件

            if (this.status() == 'available' && flag > 0) { //可選座

                var idArr = (this.settings.id).toString().split("_");
                rowNum = idArr[0];
                colNum = idArr[1]
                // console.log(this);
                // console.log(this.settings['id']);
                // console.log(this.settings['label']);
                $('<li>' + this.settings['id'] + '</li>')
                    .attr('id', 'cart-item-' + this.settings.id)
                    .data('seatId', this.settings.id)
                    .appendTo($cart);
                $("#totalPrice").text("$" + dayCount * rentPrice);
                console.log(dayCount * rentPrice);
                $(".rentPrice").val(dayCount * rentPrice);
            
                flag = flag - 1;
                return 'selected';
            } else if (this.status() == 'selected') { //已選中
            	
                //刪除已預訂座位
                $('#cart-item-' + this.settings.id).remove();
                $("#totalPrice").text("");
                //可選座
                flag = flag + 1;
                return 'available';
            } else if (this.status() == 'unavailable') { //已售出
                swal("已出租", "請選擇其他攤位", "error");
                return 'unavailable';

            } else if (flag == 0) {
                swal({
                    text: "選取超過1個攤位請先取消在進行選擇",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true
                });

                return 'available';
            }
            else {
                return this.style();
            }

        }

    });
    //已出租的座位
    sc.get(reserArr).status('unavailable');
    $('#button1').click(function () {
        if (flag == 0) {
            send(sc);//下单
        } else {
            swal("", "請選擇攤位", "error");
        }
    });
    function send(sc) {

        var columnsNum = new Array;
        sc.find('selected').each(function (i) {
            columnsNum[i] = this.settings.id;

        });
        $("#selRentArr").val(columnsNum);
        // alert($("#selRentArr").val());
    }




});

