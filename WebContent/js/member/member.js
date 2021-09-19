$('.memlist').each(function() {
    var memthis = $(this);
    var memtab = memthis.find('li.active');
    var memlink = memtab.find('a');
    var mempanel = $(memlink.attr('href'));
    memthis.on('click', '.memcontrol', function(e) {
        e.preventDefault();
        var memlink = $(this);
        var id = this.hash;
        if (id && !memlink.is('.active')) {
            mempanel.removeClass('active');
            memtab.removeClass('active');
            mempanel = $(id).addClass('active');
            memtab = memlink.parent().addClass('active');
        }
    })
});
$('.memrightul').each(function() {
    var memthis = $(this);
    var memtab = memthis.find('li.active');
    var memlink = memtab.find('a');
    var mempanel = $(memlink.attr('href'));
    memthis.on('click', '.memrightli', function(e) {
        e.preventDefault();
        var memlink = $(this);
        var id = this.hash;
        if (id && !memlink.is('.active')) {
            mempanel.removeClass('active');
            memtab.removeClass('active');
            mempanel = $(id).addClass('active');
            memtab = memlink.parent().addClass('active');
        }
    })
});
$(".Forgotpassword").on('click', function(e) {
	e.preventDefault();
	$("div.memPswdChange").fadeIn();
});
$("button.btn_modal_close").on("click", function(){
  $("div.memPswdChange").fadeOut();
});
$(".fgpsd").on('click', function(e) {
	e.preventDefault();
	$("div.copPswdChange").fadeIn();
});
$("button.btnmodalclose").on("click", function(){
  $("div.copPswdChange").fadeOut();
});