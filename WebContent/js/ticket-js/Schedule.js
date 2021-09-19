$(document).ready(function () {
  var area = new Vue({
    el: "#area",
    data: {
      hi: "hello Vue",
      region: ["不分區", "北區", "中區", "南區"],
      dindex : -1,
      bl : false,
    },
    methods: {
      area(index) {
        switch (index) {
          case 0:
            for (let i = 0; i < schedule.exh.length; i++) {
              schedule.exh[i].bl = false;
            }
            (sctext.h1 = "ALL"), (sctext.h5 = "所有活動");
            this.dindex = index;
            break;
          case 1:
            for (let i = 0; i < schedule.exh.length; i++) {
              if (schedule.exh[i].Area != "E0") {
                schedule.exh[i].bl = true;
              } else {
                schedule.exh[i].bl = false;
              }
            }
            (sctext.h1 = "Taipei"), (sctext.h5 = "北部活動");
            this.dindex = index;
            break;
          case 2:
            for (let i = 0; i < schedule.exh.length; i++) {
              if (schedule.exh[i].Area != "E1") {
                schedule.exh[i].bl = true;
              } else {
                schedule.exh[i].bl = false;
              }
            }
            (sctext.h1 = "Taichung"), (sctext.h5 = "中部活動");
            this.dindex = index;
            break;
          case 3:
            for (let i = 0; i < schedule.exh.length; i++) {
              if (schedule.exh[i].Area != "E2") {
                schedule.exh[i].bl = true;
              } else {
                schedule.exh[i].bl = false;
              }
            }
            (sctext.h1 = "Tainansc"), (sctext.h5 = "南部活動");
            this.dindex = index;
            break;
        }
      },
      display(index){
        if (this.dindex != index) {
          return { display: this.bl };
        } else {
          return { display: !this.bl };
        }
      },
    },
  });

  var sctext = new Vue({
    el: "#sctext",
    data: {
      h1: "ALL",
      h5: "所有活動",
    },
  });

  var schedule = new Vue({
    el: "#schedule",
    data: {
      // taipeisc: true,
      // taichungsc: true,
      // tainansc: true,
      exh: [],
    },
    created: function () {
      axios
        .get("/APE/ExhServletRead", {
          params: {
            Area: "0",  
          },
        })
        .then(function (res) {
          $.each(res.data, function (i, item) {
            // console.log(item.img)
            let obj = {
              // No: item.No,
              Date: item.Date,
              Topic: item.Topic,
              Content: item.Content,
              img: item.img,
              Area: item.Area,
              bl: false,
            };
            schedule.exh.push(obj);
          });
        })
        .catch(function () {
          // alert("服務繁忙");
        });
    },
  });
});
