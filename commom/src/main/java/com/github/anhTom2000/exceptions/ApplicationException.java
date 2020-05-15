package com.github.anhTom2000.exceptions;

/**
 * @Description : TODO  异常处理的基类
 * @Author : Weleness
 * @Date : 2020/05/14
 */
public abstract class ApplicationException extends  RuntimeException {
    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
