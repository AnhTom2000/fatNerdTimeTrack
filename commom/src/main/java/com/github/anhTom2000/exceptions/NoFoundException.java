package com.github.anhTom2000.exceptions;

/**
 * @Description : TODO   未找到异常
 * @Author : Weleness
 * @Date : 2020/05/14
 */
public class NoFoundException extends ApplicationException {

    public NoFoundException() {
    }

    public NoFoundException(String message) {
        super(message);
    }

    public NoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundException(Throwable cause) {
        super(cause);
    }

    public NoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
