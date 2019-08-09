+function ($) {
    $(document).on("focus", ".searchbar1 input", function (e) {
        var $input = $(e.target);
        $input.parents(".searchbar1").addClass("searchbar1-active");
        $(".search-page").addClass("search-page-active");
        var pageHidden = $(".search-box").attr("page-hidden");
        $("." + pageHidden).hide();
    });
    $(document).on("click", ".searchbar1-cancel", function (e) {
        var $btn = $(e.target);
        $btn.parents(".searchbar1").removeClass("searchbar1-active");
        $(".search-page").removeClass("search-page-active");
        var pageHidden = $(".search-box").attr("page-hidden");
        $("." + pageHidden).show();
    });
    $(document).on("blur", ".searchbar1 input", function (e) {
        var $input = $(e.target);
        // $input.parents(".searchbar1").removeClass("searchbar1-active");
        $(".search-page").addClass("search-page-active");
        // $(".search-page").removeClass("search-page-active");
    });
}(Zepto);