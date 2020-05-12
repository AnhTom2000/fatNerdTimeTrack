package com.github.anhTom2000.utils.httpcode;

/**
 * @author Weleness
 * @date 2020/05/03
 * @description TODO   统一常用状态码
 */
public enum Httpcode {
    // 正常
    OK_CODE(200),
    // 重定向
    REDIRECT_CODE(300),
    // 客户端错误
    CLIENT_ERROR_CODE(400),
    //未授权错误
    UNAUTHORIZED_CODE(401),

    //404
    NOT_FOUND_CODE(404),

    // 500
    SERVER_ERROR_CODE(500),

    ;

   private Httpcode(Integer code) {
        this.code = code;
    }

    private Integer code;

    public Integer getCode() {
        return code;
    }
}
