package com.evetime.cms.exception;

/**
 * Created by Rains
 * on 2016-06-22.
 */
public class VipException extends RuntimeException {

    public VipException(String message) {
        super(message);
    }

    public VipException(String message, Throwable cause) {
        super(message, cause);
    }
}
