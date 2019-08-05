/*!
 * =====================================================
 * SUI Mobile - http://m.sui.taobao.org/
 *
 * =====================================================
 */

// jshint ignore: start
+function ($) {
    var initData = function (callback) {
        $.ajax({
            type: 'GET',
            url: '/budget/classify/get',
            success: function (result) {
                if (result.code == 200) {
                    var data = result.obj;
                    if (data) {
                        var dataRaw = [];
                        $.each(data, function (i, item) {
                            var data_item = {};
                            data_item['name'] = item['name'];
                            var subArr = [];
                            $.each(item['sub'], function (ii, sub_item) {
                                subArr.push({
                                    'name': sub_item
                                })
                            })
                            data_item['sub'] = subArr;
                            data_item['type'] = 0;
                            dataRaw.push(data_item)
                        })
                        callback(dataRaw);
                    }
                }
            }
        })
    }
    console.log("initdata", initData)
    $.smConfig.rawCitiesData = [
        {
            "name": "材料",
            "sub": [
                {
                    "name": "瓷砖"
                },
                {
                    "name": "水泥"
                },
            ],
            "type": 0
        },
        {
            "name": "人工",
            "sub": [
                {
                    "name": "电工"
                },
                {
                    "name": "水泥工"
                },
                {
                    "name": "安装工"
                },
            ],
            "type": 0
        }
    ];
}(Zepto);
// jshint ignore: end

/* jshint unused:false*/

+function ($) {
    "use strict";
    var format = function (data) {
        var result = [];
        for (var i = 0; i < data.length; i++) {
            var d = data[i];
            if (d.name === "请选择") continue;
            result.push(d.name);
        }
        if (result.length) return result;
        return [""];
    };

    var sub = function (data) {
        if (!data.sub) return [""];
        return format(data.sub);
    };

    var getCities = function (d) {
        for (var i = 0; i < raw.length; i++) {
            if (raw[i].name === d) return sub(raw[i]);
        }
        return [""];
    };

    var getDistricts = function (p, c) {
        for (var i = 0; i < raw.length; i++) {
            if (raw[i].name === p) {
                for (var j = 0; j < raw[i].sub.length; j++) {
                    if (raw[i].sub[j].name === c) {
                        return sub(raw[i].sub[j]);
                    }
                }
            }
        }
        return [""];
    };

    // var raw = [];
    var raw = $.smConfig.rawCitiesData;

    var provinces = raw.map(function (d) {
        return d.name;
    });
    var initCities = sub(raw[0]);
    var initDistricts = [""];

    var currentProvince = provinces[0];
    var currentCity = initCities[0];
    var currentDistrict = initDistricts[0];

    var t;
    var defaults = {

        cssClass: "city-picker",
        rotateEffect: false,  //为了性能

        onChange: function (picker, values, displayValues) {
            console.log(values)
            console.log(displayValues)
            var newProvince = picker.cols[0].value;
            var newCity;
            if (newProvince !== currentProvince) {
                // 如果Province变化，节流以提高reRender性能
                clearTimeout(t);

                t = setTimeout(function () {
                    var newCities = getCities(newProvince);
                    newCity = newCities[0];
                    var newDistricts = getDistricts(newProvince, newCity);
                    picker.cols[1].replaceValues(newCities);
                    picker.cols[2].replaceValues(newDistricts);
                    currentProvince = newProvince;
                    currentCity = newCity;
                    picker.updateValue();
                }, 200);
                return;
            }
            newCity = picker.cols[1].value;
            if (newCity !== currentCity) {
                picker.cols[2].replaceValues(getDistricts(newProvince, newCity));
                currentCity = newCity;
                picker.updateValue();
            }
        },

        cols: [
            {
                textAlign: 'center',
                values: provinces,
                cssClass: "col-province"
            },
            {
                textAlign: 'center',
                values: initCities,
                cssClass: "col-city"
            },
            {
                textAlign: 'center',
                values: initDistricts,
                cssClass: "col-district"
            }
        ]
    };

    $.fn.cityPicker = function (params) {
        // raw = params.row;
        console.log(params)
        return this.each(function () {
            if (!this) return;
            var p = $.extend(defaults, params);
            //计算value
            if (p.value) {
                $(this).val(p.value.join(' '));
            } else {
                var val = $(this).val();
                val && (p.value = val.split(' '));
            }

            if (p.value) {
                //p.value = val.split(" ");
                if (p.value[0]) {
                    currentProvince = p.value[0];
                    p.cols[1].values = getCities(p.value[0]);
                }
                if (p.value[1]) {
                    currentCity = p.value[1];
                    p.cols[2].values = getDistricts(p.value[0], p.value[1]);
                } else {
                    p.cols[2].values = getDistricts(p.value[0], p.cols[1].values[0]);
                }
                !p.value[2] && (p.value[2] = '');
                currentDistrict = p.value[2];
            }
            $(this).picker(p);
        });
    };

}(Zepto);
