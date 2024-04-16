package com.zifuji.cloud.base.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int code = 20000;
    private boolean success = true;
    private String message = "请求成功";
    private T result = null;
    private long timestamp = System.currentTimeMillis();

    public static <T> Result<T> setObj(T data) {
        Result<T> r = new Result<>();
        r.result = data;
        return r;
    }

    public static <T> Result<T> set20000Mes(String mes) {
        Result<T> r = new Result<>();
        r.success = false;
        r.message = mes;
        return r;
    }

    public static <T> Result<T> set30000Mes(String mes) {
        Result<T> r = new Result<>();
        r.code = 30000;
        r.success = false;
        r.message = mes;
        return r;
    }

    public static <T> Result<T> set40000Mes(String mes) {
        Result<T> r = new Result<>();
        r.code = 40000;
        r.success = false;
        r.message = mes;
        return r;
    }

    public static <T> Result<T> set50000Mes(String mes) {
        Result<T> r = new Result<>();
        r.code = 50000;
        r.success = false;
        r.message = mes;
        return r;
    }
}
