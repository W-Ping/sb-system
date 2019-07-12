package com.ping;

import lombok.Data;

/**
 * @author thinkpad
 * @date 2019/7/12 19:25
 * @see
 */
@Data
public class ResponseVo {
    private String message;
    private String code;
    private Object obj;

    public static ResponseVo success(Object obj) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode("success");
        responseVo.setMessage("success");
        responseVo.setObj(obj);
        return responseVo;
    }
}
