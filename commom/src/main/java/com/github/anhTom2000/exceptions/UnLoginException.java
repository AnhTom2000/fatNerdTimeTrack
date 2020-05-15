package com.github.anhTom2000.exceptions;

/**
 * @Description : TODO  未登录异常
 * @Author : Weleness
 * @Date : 2020/05/14
 */
public class UnLoginException extends ApplicationException{

    public UnLoginException() {
    }

    public UnLoginException(String message) {
        super(message);
    }

    public UnLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnLoginException(Throwable cause) {
        super(cause);
    }

    public UnLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
