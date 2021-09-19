var updateindex = "";
var exhibition = new Vue({
  el: "#exhibition",
  data: {
    hi: "Hello Vue",
    area: "",
    exh: [],
    image: "",
    addtopic: "",
    addcontent: "",
    bl: true,
    dindex: -1,
  },
  methods: {
    search() {
      this.dindex = -1;
      $.ajax({
        url: "/APE/ExhServletRead", // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: {
          Area: this.area,
        }, // 傳送資料到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
          console.log(data)
          exhibition.exh.splice(0, exhibition.exh.length);
          $.each(data, function (i, item) {
            console.log(item)
            let obj = {
              No: item.No,
              Date: item.Date,
              Topic: item.Topic,
              Content: item.Content,
              img: item.img,
            };
            exhibition.exh.push(obj);
          });
        },
        error: function (xhr) {
          console.log(xhr);
        },
      });
    },
    display(index) {
      if (this.dindex != index) {
        return { display: this.bl };
      } else {
        this.lean = true;
        return { display: !this.bl };
      }
    },
    update(index) {
      updateindex = index;
      this.dindex = index;
      console.log(exhibition.exh[updateindex].img);
    },
    fileimg(e) {
      const file = e.target.files.item(0);
      const reader = new FileReader();
      reader.addEventListener("load", this.updateLoaded);
      reader.readAsDataURL(file);
    },
    updateLoaded(e) {
      exhibition.exh[updateindex].img = e.target.result;
    },
    checkupdate(index) {
      $.ajax({
        url: "http://localhost:8081/APE/ExhServletUpdate", // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: {
          No: this.exh[index].No,
          Topic: this.exh[index].Topic,
          Date: this.exh[index].Date,
          Content: this.exh[index].Content,
          img: this.exh[index].img,
        }, // 傳送資料到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
          console.log(data);
          // request 成功取得回應後執行
          alert('OK!')
          window.location.reload();
        },
      });
    },
    add() {
      this.bl = !this.bl;
    },
    fileSelected(c) {
      const file = c.target.files.item(0);
      const reader = new FileReader();
      reader.addEventListener("load", this.imageLoaded);
      reader.readAsDataURL(file);
    },
    imageLoaded(c) {
      this.image = c.target.result;
    },
    check() {
        if( this.area === '' ||
            this.addtopic === '' ||
            this.addcontent === '' ||
            this.image === '' 
        ){
            alert('請填寫全部資料');
            return;
        }
      $.ajax({
        url: "/APE/ExhServletCreate", // 資料請求的網址
        type: "POST", // GET | POST | PUT | DELETE | PATCH
        data: {
          Area: this.area,
          Topic:this.addtopic,
          Content:this.addcontent,
          img:this.image,
        }, // 傳送資料到指定的 url
        dataType: "json", // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {
            console.log(data.Amber)
            alert('OK!');
            window.location.reload();
        },
        error: function (xhr) {
          console.log(xhr);
        },
      });
    },
  },
});
