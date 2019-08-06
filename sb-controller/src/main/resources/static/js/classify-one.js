$.smConfig.rawClassifyData = (function () {
    "use strict";
    var resultData = [];
    $.ajax({
        type: 'GET',
        async: false,
        url: '/budget/classify/ft/get',
        success: function (result) {
            if (result.code == 200) {
                var data = result.obj;
                if (data) {
                    var dataRaw = [];
                    dataRaw.push("无")
                    $.each(data, function (i, item) {
                        var data_item = {};
                        dataRaw.push(item.classifyName)
                    })
                    resultData = dataRaw;
                }
            }
        }
    })
    console.log("resultData", resultData);
    return resultData
})();

+function ($) {
    "use strict";
    var raw = $.smConfig.rawClassifyData;
    var defaults = {
        cssClass: "city-picker",
        rotateEffect: false,  //为了性能
        cols: [
            {
                textAlign: 'center',
                values: raw,
                cssClass: "col-province"
            }
        ]
    };
    $.fn.classifyOnePicker = function (params) {
        return this.each(function () {
            if (!this) return;
            var p = $.extend(defaults, params);
            $(this).picker(p);
        });
    };

}(Zepto);