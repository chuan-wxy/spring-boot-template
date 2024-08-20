package com.chuan.springboottemplate.exception;

/**
 * @Author: chuan-wxy
 * @Date: 2024/8/16 19:30
 * @Description: 表示某种操作或请求失败触发的异常
 */
public class StatusFailException extends Exception{
    public StatusFailException() {
    }

    public StatusFailException(String message) {
        super(message);
    }

    public StatusFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatusFailException(Throwable cause) {
        super(cause);
    }

    public StatusFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}