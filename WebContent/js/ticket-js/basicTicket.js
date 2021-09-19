$(document).ready(function () {
  $.ajax({
    url: "/APE/ApeServletRead", // 資料請求的網址
    type: "GET", // GET | POST | PUT | DELETE | PATCH
    dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      if(data){
       $.each(data, function(i, item){
        let obj = {
          Name: item.Name,
          idCard: item.idCard,
          Email: item.Email,
          ticketNo: item.ticketNo,
          exhibitionNo: "",
        };
        switch (item.exhibitionNo) {
          case 10:
            obj.exhibitionNo = "手作文創";
            break;
          case 20:
            obj.exhibitionNo = "異國美食";
            break;
        }
        ticket.ticket.push(obj);
        console.log(obj)
       })
      };
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });

  const ticket = new Vue({
    el: "#ticket",
    data: {
      hi: "hello Vue",
      ticket: [],
      dindex: -1,
      bl: true,
      lean:false,
    },
    methods: {
      display(index) {
        if (this.dindex != index) {
          return { display: this.bl };
        } else {
          this.lean = true;
          return { display: !this.bl };
        }
      },
      update(index) {
          this.dindex = index;
      },
      abc(){
        return this.lean;
      },
      check(index) {
        $.ajax({
          url: "/APE/ApeServletUpdate", // 資料請求的網址
          type: "GET", // GET | POST | PUT | DELETE | PATCH
          data: {
            ticketName:this.ticket[index].Name,
            ticketIdCard:this.ticket[index].idCard,
            ticketEmail:this.ticket[index].Email,
            ticketNo:this.ticket[index].ticketNo,
          }, // 傳送資料到指定的 url
          dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
          success: function (data) {
            // request 成功取得回應後執行
            alert('修改成功 !');
            location.reload(true);
          },
        });
      },
    },
  });
});
