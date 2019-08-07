+function ($) {
    $(document).on("focus", ".searchbar input", function (e) {
        var $input = $(e.target);
        $(".search-page").addClass("search-page-active");
        var pageHidden = $(".search-box").attr("page-hidden");
        $("." + pageHidden).hide();
    });
    $(document).on("click", ".searchbar-cancel", function (e) {
        var $btn = $(e.target);
        $btn.parents(".searchbar").removeClass("searchbar-active");
        $(".search-page").removeClass("search-page-active");
        var pageHidden = $(".search-box").attr("page-hidden");
        $("." + pageHidden).show();
    });
    $(document).on("blur", ".searchbar input", function (e) {
        var $input = $(e.target);
        // $(".search-page").addClass("search-page-active");
        // $input.parents(".searchbar").removeClass("searchbar-active");
        // $(".search-page").removeClass("search-page-active");
    });
}(Zepto);