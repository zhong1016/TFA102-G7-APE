var section = new Vue({
    el: '#section',
    data: {
      idCard: '',
      Name: '',
      Email: '',
      ticket: true,
      qrcode: '',
    },
    methods: {
      submit() {
        if (this.Name === '' || this.idCard === '' || this.Email === '') {
          alert('請填寫完所有欄位');
        } else if (this.idCard.length < 5 || this.Name.length < 2 || this.idCard.length > 7) {
          alert('請填寫正確資料');
        } else {
          $.ajax({
            url: "/APE/ApeServletCreate", // 資料請求的網址
            type: "GET", // GET | POST | PUT | DELETE | PATCH
            data: {
              Name: this.Name,
              Email: this.Email,
              idCard: this.idCard,
              exhibitionNo: 20,
            }, // 傳送資料到指定的 url
            dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
            success: function (data) {
              alert("傳送成功");
              section.ticket = false;
              section.qrcode = data.qrcode;

              let qrcode = new QRCode("qrcode", {
                width: 200,
                height: 200,
                text: section.qrcode, // 二维码内容 也可以放url
                colorDark: "#000",
                colorLight: "#fff",
              });
            },
            error: function (xhr) {
              console.log(xhr)
            },

          });
        }
      },
      autoinput() {
        this.Name = '梁小穎';
        this.Email = 'APEtfa102team07@gmail.com';
        this.idCard = '123456';
        // console.log(section.ticket)
      }
    },
  })