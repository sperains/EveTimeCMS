package com.evetime.cms.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-04-18.
 */
public class ResultData implements Serializable {

    private boolean success;
    private String message;
    private Object data;

    public ResultData(){

    }

    public ResultData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultData(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
