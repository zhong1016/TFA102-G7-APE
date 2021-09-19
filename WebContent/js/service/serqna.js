$("a.link_title").on("click", function(e) {
    e.preventDefault();
    $(this).closest("li").toggleClass("-on");
    $(this).closest("li").find("div.inner_block").slideToggle();
});